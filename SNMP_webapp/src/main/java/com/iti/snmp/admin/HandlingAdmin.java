/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.admin;

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
public class HandlingAdmin {

    private static final DatabaseManagement db = new DatabaseManagement();
    private static Connection conn = db.getConn();
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement pst;

    public static void RegisterAdmin(String username, String email, String password, String phoneNumber) throws SQLException {
        try {

            String registerQuery = "INSERT INTO admin (name ,email "
                    + ",password ,phone)\n"
                    + " VALUES (? ,? ,? ,?)";

            PreparedStatement insertStatement = conn.prepareStatement(registerQuery);
            insertStatement.setString(1, username);
            insertStatement.setString(2, email);
            insertStatement.setString(3, password);
            insertStatement.setString(4, phoneNumber);

            int isInsert = insertStatement.executeUpdate();
            if (isInsert > 0) {
                System.out.println("new Admin added");
            } else {
                System.out.println("error in registartion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static boolean searchEmail(String email) throws SQLException {
        PreparedStatement stmt;
        stmt = conn.prepareStatement("select email from admin where email =?");
        stmt.setString(1, email);
        ResultSet res = stmt.executeQuery();
        return res.next();

    }

    public static boolean searchPhone(String phone) throws SQLException {
        PreparedStatement stmt;
        stmt = conn.prepareStatement("select phone from admin where phone =?");
        stmt.setString(1, phone);
        ResultSet res = stmt.executeQuery();
        return res.next();

    }

    public static Admin checkLogin(Admin admin) throws SQLException {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM admin where email=? and password=?");
            stmt.setString(1, admin.getEmail());
            stmt.setString(2, admin.getPassword());
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                admin.setAdminId(res.getInt(1));
                admin.setName(res.getString(2));

                return admin;
            }
        } catch (SQLException ex) {
            System.err.println("error : " + ex);
        }
        admin.setAdminId(-1);
        return admin;
    }

    public static List<Admin> getAdmins() {
        ArrayList<Admin> admins = new ArrayList<Admin>();

        try {

            stmt = conn.createStatement();
            String SQL = "SELECT * from admin;";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setEmail(rs.getString("email"));

                admin.setPhone(rs.getString("phone"));
                admin.setName(rs.getString("name"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }

}
