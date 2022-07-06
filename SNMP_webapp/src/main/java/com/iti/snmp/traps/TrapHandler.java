/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.traps;

import com.iti.snmp.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nour
 */
public class TrapHandler {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pst;
    private ArrayList<Trap> traps;

    public TrapHandler() {
        conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("database connection is null");
        }
    }

    public List<Trap> getTraps() {
        traps = new ArrayList<Trap>();
        try {
            stmt = conn.createStatement();
            String SQL = "SELECT m.node_id, n.name, n.ip, t.description, m.timestamp_snmp FROM node as n inner join history as m ON n.id = m.node_id inner join trap as t on t.id = m.trap_id where  status = 'f';";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Integer node_id = rs.getInt("node_id");
                String node_name = rs.getString("name");
                String ip = rs.getString("ip");
                String trap_desc = rs.getString("description");
                String trap_timestamp = rs.getString("timestamp_snmp");
                Trap trap = new Trap(node_id, node_name, ip, trap_desc, trap_timestamp);
                traps.add(trap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return traps;
    }

    public int getTrapid(String type) {
        Integer trap_id = 0;
        try {
            stmt = conn.createStatement();
            String SQL = "SELECT t.id FROM trap as t inner join history as m ON t.id = m.trap_id where t.description = '" + type + "';";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                trap_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trap_id;
    }

    public void updateStatus(int id, String trap_type) {
        try {
            Integer trap_id = getTrapid(trap_type);
            pst = conn.prepareStatement("UPDATE history SET status=? where node_id = ? AND trap_id = ?");
            pst.setBoolean(1, Boolean.TRUE);
            pst.setDouble(2, id);
            pst.setInt(3, trap_id);
            int rows = pst.executeUpdate();
            pst.close();
            System.out.print(rows);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<Trap> getTrapsByAdminId(int adminId) {
        traps = new ArrayList<Trap>();
        try {
            stmt = conn.createStatement();
            String SQL = "SELECT m.node_id, n.name, n.ip, t.description, m.timestamp_snmp FROM node as n inner join history as m ON n.id = m.node_id inner join trap as t on t.id = m.trap_id where n.admin_id =" + adminId + " AND m.status = 'f';";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Integer node_id = rs.getInt("node_id");
                String node_name = rs.getString("name");
                String ip = rs.getString("ip");
                String trap_desc = rs.getString("description");
                String trap_timestamp = rs.getString("timestamp_snmp");
                Trap trap = new Trap(node_id, node_name, ip, trap_desc, trap_timestamp);
                traps.add(trap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return traps;
    }
}
