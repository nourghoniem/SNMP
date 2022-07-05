/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.traps;

/**
 *
 * @author nour
 */
public class Trap {
    
    private String node_name;
    private String ip;
    private String trap_type;
    private String time_issued;
    
    public Trap(){}

    public Trap(String node_name, String ip, String trap_type, String time_issued) {
        this.node_name = node_name;
        this.ip = ip;
        this.trap_type = trap_type;
        this.time_issued = time_issued;
      
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTrap_type() {
        return trap_type;
    }

    public void setTrap_type(String trap_type) {
        this.trap_type = trap_type;
    }

    public String getTime_issued() {
        return time_issued;
    }

    public void setTime_issued(String time_issued) {
        this.time_issued = time_issued;
    }

    
}
