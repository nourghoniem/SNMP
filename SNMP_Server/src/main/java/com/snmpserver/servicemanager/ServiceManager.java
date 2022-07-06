/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.snmpserver.servicemanager;

import com.snmpserver.actions.MailService;
import com.snmpserver.actions.TwilioService;
import com.snmpserver.database.DB_Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Ramez
 */
public class ServiceManager {
    private static final DB_Connection dB_Connection = DB_Connection.getDatabaseInstance();
    private static final TwilioService twilioService = TwilioService.GetTwilioServiceInstance();
    private static final MailService mailService = MailService.getMailServiceInstance();
    
    public static void initServices(){
        try {
            dB_Connection.connectToDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        twilioService.InitTwilioService();
        mailService.initMailSession();        
    }
    
    public static void destroyServices(){
        try {
            dB_Connection.closeDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        twilioService.DestroyTwilioService();
    }
    
}
