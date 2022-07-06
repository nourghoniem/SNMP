/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.nodes;

import com.iti.snmp.database.DatabaseManagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Salma
 */
public class NodeHandler {

    private static final DatabaseManagement db = new DatabaseManagement();
    private static final Connection conn = db.getConn();
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement pst;

    public static List<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = new Node();
        try {

            stmt = conn.createStatement();
            String SQL = "select n.\"name\",n.ip,n.status,n.description,a.email from node n , public.\"admin\" a where a.id=n.admin_id   ";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                node.setName(rs.getString("name"));
                node.setStatus(rs.getString("status"));
                node.setNodeIp(rs.getString("ip"));
                node.setDescription(rs.getString("description"));
                node.setAdminName(rs.getString("email"));
                nodes.add(node);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nodes;
    }

    public static List<Node> getNodesByAdminId(int adminId) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        try {

            stmt = conn.createStatement();
            String SQL = "select n.\"name\",n.ip,n.status,n.description,a.email from node n , public.\"admin\" a where a.id=n.admin_id and a.id=" + adminId + ";";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Node node = new Node();
                node.setName(rs.getString("name"));
                node.setStatus(rs.getString("status"));
                node.setNodeIp(rs.getString("ip"));
                node.setDescription(rs.getString("description"));
                node.setAdminName(rs.getString("email"));
                nodes.add(node);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nodes;
    }
}
