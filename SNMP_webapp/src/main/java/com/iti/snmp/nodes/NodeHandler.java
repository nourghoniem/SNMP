/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.nodes;

import com.iti.snmp.admin.HandlingAdmin;
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

        try {

            stmt = conn.createStatement();
            String SQL = "select n.\"name\",n.ip,n.status,n.description,a.email from node n , public.\"admin\" a where a.id=n.admin_id   ";
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

    public static boolean searchIp(String ip) throws SQLException {
        PreparedStatement stmt;
        stmt = conn.prepareStatement("select ip from node where ip =?");
        stmt.setString(1, ip);
        ResultSet res = stmt.executeQuery();
        return res.next();

    }

    public static boolean searchName(String name) throws SQLException {
        PreparedStatement stmt;
        stmt = conn.prepareStatement("select ip from node where ip =?");
        stmt.setString(1, name);
        ResultSet res = stmt.executeQuery();
        return res.next();

    }

    public static Node getNodeByIp(String ip) {

        Node node = new Node();
        try {

            stmt = conn.createStatement();
            String SQL = "select * from node where ip=" + ip + ";";
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

        return node;
    }

//    public static String deleteNode(String ip) {
//        try {
//            stmt = conn.createStatement();
//            String SQL = "DELETE FROM node WHERE ip = '" + ip + "';";
//            stmt.executeUpdate(SQL);
//
//        } catch (SQLException e) {
//            return ("deleting node" + e.getMessage());
//        }
//        return "ok";
    // }
    public static String addNode(String name, String desc, String email, String ip) throws SQLException {
        try {

            String registerQuery = "INSERT INTO node (\"name\", ip, description, admin_id, status) \n"
                    + "	VALUES (?, ?, ?, ?, 'green');";

            PreparedStatement insertStatement = conn.prepareStatement(registerQuery);
            insertStatement.setString(1, name);
            insertStatement.setString(2, ip);
            insertStatement.setInt(4, HandlingAdmin.getAdminId(email));
            insertStatement.setString(3, desc);

            int isInsert = insertStatement.executeUpdate();
            if (isInsert > 0) {
                return ("new Admin added");

            } else {
                return ("error in registartion");

            }
        } catch (SQLException e) {
            return (e.getMessage());
        }

    }
}
