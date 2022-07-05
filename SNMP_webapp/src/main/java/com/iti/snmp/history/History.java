/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.history;

/**
 *
 * @author nour
 */
public class History {
    
    private int node_id;
    private String node_name;
    private String trap_type;
    private Boolean status;
    private String action;
    private String time_issued;
    
    public History(){}

    public History(int node_id, String node_name, String trap_type, Boolean status, String action, String time_issued) {
        this.node_id = node_id;
        this.node_name = node_name;
        this.trap_type = trap_type;
        this.status = status;
        this.action = action;
        this.time_issued = time_issued;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getTrap_type() {
        return trap_type;
    }

    public void setTrap_type(String trap_type) {
        this.trap_type = trap_type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTime_issued() {
        return time_issued;
    }

    public void setTime_issued(String time_issued) {
        this.time_issued = time_issued;
    }
    
    
    
    
    
    
}
