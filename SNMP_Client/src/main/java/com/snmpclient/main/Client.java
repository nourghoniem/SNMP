/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.snmpclient.main;

import com.snmpclient.node.NodeInfo;
import com.snmpclient.traps.SnmpTrapInfo;
import com.snmpclient.traps.SnmpTrapTypes;
import com.snmpclient.traps.TrapSender;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Michael Ramez
 */
public class Client {

    public static void main(String[] args) {
        SnmpTrapInfo snmpTrapInfo = new SnmpTrapInfo();
        snmpTrapInfo.setTrapType(SnmpTrapTypes.NETWORK_CONNECTIVITY);
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNode_IP("1.2.3.4");
        nodeInfo.setNode_ID(6);
        snmpTrapInfo.setNodeInfo(nodeInfo);
        snmpTrapInfo.setTimestamp(new Timestamp(new Date().getTime()));
        TrapSender.sendSnmpV2Trap("0.5.6.5.6.74.4.6.4.2", snmpTrapInfo);
    }
}
