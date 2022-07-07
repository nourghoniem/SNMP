/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmpserver.server;

import com.google.gson.Gson;
import com.snmpserver.actions.ActionTypes;
import com.snmpserver.actions.MailService;
import com.snmpserver.actions.TwilioService;
import com.snmpserver.database.DB_Connection;
import com.snmpserver.traps.SnmpTrapInfo;
import com.snmpserver.traps.SnmpTrapTypes;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.Priv3DES;
import org.snmp4j.security.PrivAES128;
import org.snmp4j.security.PrivAES192;
import org.snmp4j.security.PrivAES256;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

/**
 * @author
 *
 */
public class SnmpServer implements CommandResponder {

    private MultiThreadedMessageDispatcher dispatcher;
    private Snmp snmp = null;
    private Address listenAddress;
    private ThreadPool threadPool;
    private final DB_Connection dB_Connection = DB_Connection.getDatabaseInstance();
    private final TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
    private final MailService mailService = MailService.getMailServiceInstance();
    private static final SnmpServer snmpServer = new SnmpServer();
    
    
    private SnmpServer() {
    }
    
    public static SnmpServer getSnmpServerInstance(){
        return snmpServer;
    }

    public static void main(String[] args) {
        new SnmpServer().run();
    }

    public void run() {
        try {
            init();
            snmp.addCommandResponder(this);
        } catch (IOException ex) {
        }
    }

    private void init() throws UnknownHostException, IOException {
        threadPool = ThreadPool.create("Trap", 10);
        dispatcher = new MultiThreadedMessageDispatcher(threadPool,
                new MessageDispatcherImpl());

        //TRANSPORT
        listenAddress = GenericAddress.parse(System.getProperty(
                "snmp4j.listenAddress", "udp:0.0.0.0/164"));  //SET THIS
        TransportMapping<?> transport;
        if (listenAddress instanceof UdpAddress) {
            transport = new DefaultUdpTransportMapping(
                    (UdpAddress) listenAddress);
        } else {
            transport = new DefaultTcpTransportMapping(
                    (TcpAddress) listenAddress);
        }

        //V3 SECURITY
        USM usm = new USM(
                SecurityProtocols.getInstance().addDefaultProtocols(),
                new OctetString(MPv3.createLocalEngineID()), 0);

        SecurityProtocols.getInstance().addPrivacyProtocol(new PrivAES192());
        SecurityProtocols.getInstance().addPrivacyProtocol(new PrivAES256());
        SecurityProtocols.getInstance().addPrivacyProtocol(new Priv3DES());

        usm.setEngineDiscoveryEnabled(true);

        SecurityModels.getInstance().addSecurityModel(usm);

        snmp = new Snmp(dispatcher, transport);
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
        snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3(usm));

        String username = "username";         // SET THIS
        String authpassphrase = "authpassphrase";   // SET THIS
        String privacypassphrase = "privacypassphrase";   // SET THIS

        snmp.getUSM().addUser( // SET THE SECURITY PROTOCOLS HERE
                new OctetString(username),
                new UsmUser(new OctetString(username), AuthMD5.ID, new OctetString(
                        authpassphrase), PrivAES128.ID, new OctetString(privacypassphrase)));

        snmp.listen();
    }

    public void processPdu(CommandResponderEvent crEvent) {
        PDU pdu = crEvent.getPDU();
        System.out.println("");
        System.out.println("===== NEW SNMP 2/3 TRAP RECEIVED ====");
        Vector<? extends VariableBinding> varBinds = pdu.getVariableBindings();
        if (varBinds != null && !varBinds.isEmpty()) {
            Iterator<? extends VariableBinding> varIter = varBinds.iterator();
            while (varIter.hasNext()) {
                VariableBinding vb = varIter.next();
                SnmpTrapInfo snmpTrapInfo = new Gson().fromJson(vb.getVariable().toString(), SnmpTrapInfo.class);
                logTrap(snmpTrapInfo);
                handleTrap(snmpTrapInfo);
            }
        }
        System.out.println("==== TRAP END ===");
        System.out.println("");
    }

    private void logTrap(SnmpTrapInfo snmpTrapInfo) {
        System.out.println("com.snmpserver.server.SnmpServer.logTrap()");
        String sqlCommand = "insert into history values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = dB_Connection.getPreparedStatement(sqlCommand);
            preparedStatement.setInt(1, snmpTrapInfo.getNodeInfo().getNode_ID());
            preparedStatement.setInt(2, snmpTrapInfo.getTrapType());
            preparedStatement.setTimestamp(3, snmpTrapInfo.getTimestamp());
            preparedStatement.setBoolean(4, false);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(SnmpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getActionType(SnmpTrapInfo snmpTrapInfo) throws SQLException {
        String sqlCommand = "select action_id from rules where trap_id = ? and node_id = ?";
        PreparedStatement preparedStatement = dB_Connection.getPreparedStatement(sqlCommand);
        preparedStatement.setInt(1, snmpTrapInfo.getTrapType());
        preparedStatement.setInt(2, snmpTrapInfo.getNodeInfo().getNode_ID());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("action_id");
    }

    private String getAdminPhone(SnmpTrapInfo snmpTrapInfo) throws SQLException {
        String sqlCommand = "select admin.phone from admin,node where admin.id = node.admin_id and node.id = ?";
        PreparedStatement preparedStatement = dB_Connection.getPreparedStatement(sqlCommand);
        preparedStatement.setInt(1, snmpTrapInfo.getNodeInfo().getNode_ID());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("phone");
    }

    private String getAdminMail(SnmpTrapInfo snmpTrapInfo) throws SQLException {
        String sqlCommand = "select admin.email from admin,node where admin.id = node.admin_id and node.id = ?";
        PreparedStatement preparedStatement = dB_Connection.getPreparedStatement(sqlCommand);
        preparedStatement.setInt(1, snmpTrapInfo.getNodeInfo().getNode_ID());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("email");
    }
    
    private String getNodeName(SnmpTrapInfo snmpTrapInfo) throws SQLException{
        String sqlCommand = "select node.name from node where node.id = ?";
        PreparedStatement preparedStatement = dB_Connection.getPreparedStatement(sqlCommand);
        preparedStatement.setInt(1, snmpTrapInfo.getNodeInfo().getNode_ID());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("name");
    }
    
    private String getAdminMessage(SnmpTrapInfo snmpTrapInfo) throws SQLException{
        String message = "";
        switch (snmpTrapInfo.getTrapType()) {
            case SnmpTrapTypes.HD_IS_FULL:
                message =  "Hard disk is full at ";
                break;
            case SnmpTrapTypes.MEMORY_IS_FULL:
                message =  "Memory is full at ";
                break;
            case SnmpTrapTypes.NETWORK_CONNECTIVITY:
                message = "Network Connectivity Problem at ";
                break;
            }
        return message;
    }

    private void handleTrap(SnmpTrapInfo snmpTrapInfo) {
        try {
            int actionType = getActionType(snmpTrapInfo);
            switch (actionType) {
                case ActionTypes.SMS:
                    String SMSmessage = getAdminMessage(snmpTrapInfo);
                    String adminPhoneNumber = getAdminPhone(snmpTrapInfo);
                    System.out.println("sending sms to " + adminPhoneNumber);
                    twilioService.SendMessage(adminPhoneNumber, SMSmessage);
                    break;
                case ActionTypes.MAIL:
                    String Mailmessage = getAdminMessage(snmpTrapInfo);
                    String adminMail = getAdminMail(snmpTrapInfo);
                    System.out.println("sending mail to " + adminMail);
                    mailService.sendMail(adminMail, Mailmessage, getNodeName(snmpTrapInfo));
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SnmpServer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
