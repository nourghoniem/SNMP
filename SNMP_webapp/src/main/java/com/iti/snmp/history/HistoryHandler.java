/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.history;

import com.iti.snmp.database.DatabaseConnection;
import com.iti.snmp.resolved.ResolvedHandler;
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
public class HistoryHandler {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pst;
    private ArrayList<History> history;

    public HistoryHandler() {

        conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("database connection is null");
        }

    }

    public String getActionById(Integer action_id) {
        String action_desc = "";
        try {
            stmt = conn.createStatement();
            String SQL = "SELECT description FROM action where id = " + action_id + ";";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                action_desc = rs.getString("description");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return action_desc;
    }

    public List<History> getHistory(Integer admin_id, String st) {
        history = new ArrayList<History>();
        try {
            stmt = conn.createStatement();

            String SQL = "SELECT m.id, m.node_id, n.name, t.description, m.status, r.action_id, m.timestamp_snmp FROM node as n inner join history as m ON n.id = m.node_id inner join trap as t on t.id = m.trap_id inner join rules AS r ON m.trap_id = r.trap_id AND m.node_id = r.node_id where n.admin_id = " + admin_id + " AND m.status = true;";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Integer history_id = rs.getInt("id");
                Integer node_id = rs.getInt("node_id");
                String node_name = rs.getString("name");
                String trap_desc = rs.getString("description");
                Boolean status = rs.getBoolean("status");
                Integer action_id = rs.getInt("action_id");
                String trap_timestamp = rs.getString("timestamp_snmp");
                String action_desc = getActionById(action_id);

                History one = new History(history_id, node_id, node_name, trap_desc, status, action_desc, trap_timestamp);
                history.add(one);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }

    
    public void removeFromHistory(Integer history_id){
        try {
       
            ResolvedHandler resolvedHandler = new ResolvedHandler();
            resolvedHandler.removeFromResolved(history_id);
            pst = conn.prepareStatement("DELETE FROM history where id = ?");
            pst.setInt(1, history_id);
            int rows = pst.executeUpdate();
            pst.close();
            System.out.print(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
