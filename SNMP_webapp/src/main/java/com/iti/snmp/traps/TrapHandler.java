/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.traps;

import com.iti.snmp.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
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
    
    public TrapHandler(){
        conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("database connection is null");
        }
    }
    
    public List<Trap> getTraps(Integer admin_id){
        traps = new ArrayList<Trap>();
           try {
            stmt = conn.createStatement();
            String SQL = "SELECT m.id, m.node_id, n.name, n.ip, t.description, m.timestamp_snmp FROM node as n inner join history as m ON n.id = m.node_id inner join trap as t on t.id = m.trap_id where n.admin_id = "+admin_id+" AND m.status = false;";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Integer history_id = rs.getInt("id");
                Integer node_id = rs.getInt("node_id");
                String node_name = rs.getString("name");
                String ip = rs.getString("ip");
                String trap_desc = rs.getString("description");
                String trap_timestamp = rs.getString("timestamp_snmp");
                Trap trap = new Trap(history_id, node_id, node_name, ip, trap_desc, trap_timestamp);
                traps.add(trap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

      return traps;
    }
    
    public int getTrapid(String type){
        Integer trap_id = 0;
        try {
            stmt = conn.createStatement();
            String SQL = "SELECT t.id FROM trap as t inner join history as m ON t.id = m.trap_id where t.description = '"+type+"';";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                trap_id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trap_id;
    }
    
    public void updateStatus(int id){
         try {
            pst = conn.prepareStatement("UPDATE history SET status=? where id = ?");
            pst.setBoolean(1, Boolean.TRUE);
            pst.setDouble(2, id);
            int rows = pst.executeUpdate();
            pst.close();
            System.out.print(rows);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
