/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.nodes;

/**
 *
 * @author Salma
 */
public class Node {

    private String nodeIp;
    private String name;
    private String description;
    private String adminName;
    private String status;

    public Node(String nodeIp, String name, String description, String adminName, String status) {
        this.nodeIp = nodeIp;
        this.name = name;
        this.description = description;
        this.adminName = adminName;
        this.status = status;
    }

    public Node() {
        this.nodeIp = null;
        this.name = null;
        this.description = null;
        this.adminName = null;
        this.status = null;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

}
