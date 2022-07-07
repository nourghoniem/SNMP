/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.snmpserver.actions;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Michael Ramez
 */
public class MailService {

    // Sender's email ID needs to be mentioned
    private final String from = "michaelramez90@gmail.com";

    // Assuming you are sending email from through gmails smtp
    private final String host = "smtp.gmail.com";

    private final String password = "oszlflpdkujcjokj";

    private Session session;
    
    private static final MailService mailService = new MailService();
    
    private MailService(){        
    }

    public static MailService getMailServiceInstance(){
        return mailService;
    }
    
    public void initMailSession() {
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, password);
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        System.out.println("com.snmpserver.actions.MailService.initMailSession()");
    }

    public void sendMail(String to, String message, String nodeName) {
        try {
            // Create a default MimeMessage object.
            MimeMessage mimeMessage = new MimeMessage(session);

            // Set From: header field of the header.
            mimeMessage.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            mimeMessage.setSubject(nodeName + "failure, action needed");

            // Now set the actual message
            mimeMessage.setText(message + nodeName);

            System.out.println("sending...");
            // Send message
            Transport.send(mimeMessage);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
