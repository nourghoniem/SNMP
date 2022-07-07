/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmpclient.traps;

import com.google.gson.Gson;
import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author elsabagh
 */
public class TrapSender {

    public TrapSender() {
    }

    /**
     * This methods sends the V2 trap to the Localhost in port 163
     * @param oid
     * @param AlarmMessage
     */
    public static void sendSnmpV2Trap(String oid, SnmpTrapInfo AlarmMessage) {
        String AlarmMessageJSON = new Gson().toJson(AlarmMessage);
        try {
            //Create Transport Mapping
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();

            //Create Target
            CommunityTarget comtarget = new CommunityTarget();
            comtarget.setCommunity(new OctetString("public"));
            comtarget.setVersion(SnmpConstants.version2c);
            comtarget.setAddress(new UdpAddress("127.0.0.1/164"));
            comtarget.setRetries(2);
            comtarget.setTimeout(5000);

            //Create PDU for V2
            PDU pdu = new PDU();
            
            // need to specify the system up time
            pdu.add(new VariableBinding(new OID(oid), new OctetString(AlarmMessageJSON)));
//            pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(oid)));
            //Add Payload
            // variable binding for Enterprise Specific objects, Severity (should be defined in MIB file)

            pdu.setType(PDU.NOTIFICATION);
            //Send the PDU
            Snmp snmp = new Snmp(transport);
            System.out.println("Sending SNMP_V2 Trap to 127.0.0.1/164, Alarm Message = " + AlarmMessageJSON);
            snmp.send(pdu, comtarget);
            snmp.close();
        } catch (IOException e) {
            System.out.println("Error in Sending SNMP_V2 Trap  to 127.0.0.1/164" + e.getMessage());
        }
    }
}
