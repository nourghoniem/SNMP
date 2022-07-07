/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmpclient.traps;

import com.snmpclient.node.NodeInfo;
import java.sql.Timestamp;

public class SnmpTrapInfo {

    private Integer TrapType;
    private NodeInfo nodeInfo;
    private Timestamp timestamp;

    /**
     * @return the TrapType
     */
    public Integer getTrapType() {
        return TrapType;
    }

    /**
     * @param TrapType the TrapType to set
     */
    public void setTrapType(Integer TrapType) {
        this.TrapType = TrapType;
    }

    /**
     * @return the issueInfo
     */
    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    /**
     * @param issueInfo the issueInfo to set
     */
    public void setNodeInfo(NodeInfo issueInfo) {
        this.nodeInfo = issueInfo;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
