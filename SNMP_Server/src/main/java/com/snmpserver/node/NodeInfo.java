/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snmpserver.node;


public class NodeInfo {

    private Integer Node_ID;
    private String Node_IP;


    /**
     * @return the Node_Name
     */
    public Integer getNode_ID() {
        return Node_ID;
    }

    public void setNode_ID(Integer Node_ID) {
        this.Node_ID = Node_ID;
    }

    /**
     * @return the Node_IP
     */
    public String getNode_IP() {
        return Node_IP;
    }

    /**
     * @param Node_IP the Node_IP to set
     */
    public void setNode_IP(String Node_IP) {
        this.Node_IP = Node_IP;
    }

}
