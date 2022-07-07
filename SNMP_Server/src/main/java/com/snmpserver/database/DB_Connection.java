/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.snmpserver.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author asmaaMohamed
 */
public class DB_Connection {

    private final String url = "jdbc:postgresql://bic2b6bup02t0oo5kmbg-postgresql.services.clever-cloud.com:5432/bic2b6bup02t0oo5kmbg";
    private final String userName = "uoovw5apdysjdbrvhmnq";
    private final String pass = "tl0ABe0nGHni0MWZIFb2";
    private Connection connection;

    private static final DB_Connection databaseInstance = new DB_Connection();

    private DB_Connection() {
    }

    public static DB_Connection getDatabaseInstance() {
        return databaseInstance;
    }

    public PreparedStatement getPreparedStatement(String sqlCommand) throws SQLException {
        return connection.prepareStatement(sqlCommand);
    }

    public void connectToDB() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, userName, pass);
        System.out.println("Connection is made successfully");

    }

    public void closeDBConnection() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws SQLException {

    }

}
