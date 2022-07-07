/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.resolved;

import com.iti.snmp.database.DatabaseConnection;
import com.iti.snmp.history.History;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nour
 */
public class ResolvedHandler {
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement pst;
    private ArrayList<History> history;
    private ArrayList<Resolved> resolved;
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public ResolvedHandler() {
        conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("database connection is null");
        }
    }
    
    public void addResolved(Resolved resolved){
        
         try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            pst = conn.prepareStatement("INSERT INTO resolved (history_id, issued_at) VALUES(?,?)");
            pst.setInt(1, resolved.getHistory_id());
            pst.setTimestamp(2, timestamp);
            int rows = pst.executeUpdate();
            pst.close();
            System.out.print(rows);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
        public List<Resolved> getResolved(Integer admin_id) {
        resolved = new ArrayList<Resolved>();
        try {
            stmt = conn.createStatement();

            String SQL = "SELECT r.id, m.trap_id, n.name, r.issued_at, s.description FROM resolved as r inner join history as m on r.history_id = m.id inner join node as n on n.id = m.node_id inner join trap as s on s.id = m.trap_id where n.admin_id = " + admin_id + ";";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Integer resolved_id = rs.getInt("id");
                Integer trap_id = rs.getInt("trap_id");
                String node_name = rs.getString("name");
                Timestamp issued_at = rs.getTimestamp("issued_at");
                String trap_desc = rs.getString("description");
             
                Resolved resolve = new Resolved(resolved_id,  node_name, trap_id, trap_desc, issued_at);
                resolved.add(resolve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resolved;
    }
        
    public void removeFromResolved(Integer history_id){
        try {
       
            pst = conn.prepareStatement("DELETE FROM resolved where history_id = ?");
            pst.setInt(1, history_id);
            int rows = pst.executeUpdate();
            pst.close();
            System.out.print(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    
    
    
    
    
    
    
}
