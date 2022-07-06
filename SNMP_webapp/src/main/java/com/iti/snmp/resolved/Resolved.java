/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.resolved;

import java.sql.Timestamp;

/**
 *
 * @author nour
 */
public class Resolved {
    private int resolved_id;
    private String node_name;
    private int trap_id;
    private String trap_desc;
    private int history_id;
    private Timestamp issued_at;
    
    public Resolved(){}

    public Resolved(int history_id) {
        this.history_id = history_id;
    }
    
    public Resolved(int resolved_id, int history_id, Timestamp issued_at) {
        this.resolved_id = resolved_id;
        this.history_id = history_id;
        this.issued_at = issued_at;
    }

    public Resolved(int resolved_id, String node_name, int trap_id, String trap_desc, Timestamp issued_at) {
        this.resolved_id = resolved_id;
        this.node_name = node_name;
        this.trap_id = trap_id;
        this.trap_desc = trap_desc;
        this.issued_at = issued_at;
    }
    
    

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getTrap_id() {
        return trap_id;
    }

    public void setTrap_id(int trap_id) {
        this.trap_id = trap_id;
    }

    public String getTrap_desc() {
        return trap_desc;
    }

    public void setTrap_desc(String trap_desc) {
        this.trap_desc = trap_desc;
    }

    public int getResolved_id() {
        return resolved_id;
    }

    public void setResolved_id(int resolved_id) {
        this.resolved_id = resolved_id;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public Timestamp getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(Timestamp issued_at) {
        this.issued_at = issued_at;
    }
    
    
    
}
