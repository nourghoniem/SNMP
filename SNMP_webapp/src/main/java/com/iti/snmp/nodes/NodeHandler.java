/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.nodes;

import com.iti.snmp.database.DatabaseConnection;
import com.iti.snmp.traps.Trap;
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

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pst;
    private ArrayList<Trap> traps;

    public NodeHandler() {
        conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("database connection is null");
        }
    }

    public List<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = new Node();
        try {

            stmt = conn.createStatement();
            String SQL = "select n.\"name\",n.ip,n.status,n.description,a.email from node n , public.\"admin\" a where a.id=n.admin_id  ";
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

    public List<Node> getNodesByAdminId(int adminId) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = new Node();
        try {

            stmt = conn.createStatement();
            String SQL = "select n.\"name\",n.ip,n.status,n.description,a.email from node n , public.\"admin\" a where a.id=n.admin_id and a.id=" + adminId + ";";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                node.setName(rs.getString("name"));
                node.setStatus(rs.getString("status"));
                node.setNodeIp(rs.getString("ip"));
                node.setDescription(rs.getString("description"));
                node.setAdminName(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nodes;
    }
}
