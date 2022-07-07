/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.snmpserver.main;


import com.snmpserver.server.SnmpServer;
import com.snmpserver.servicemanager.ServiceManager;


/**
 *
 * @author Michael Ramez
 */
public class Server {

    public static void main(String[] args) {
        ServiceManager.initServices();
        SnmpServer.getSnmpServerInstance().run();
//        ServiceManager.destroyServices();
    }
}
