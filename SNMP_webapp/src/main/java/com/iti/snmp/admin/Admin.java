/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.snmp.admin;

/**
 *
 * @author Salma
 */
public class Admin {

    private int AdminId;
    private String Name;
    private String Email;
    private String Password;
    private String Phone;

    public Admin(int AdminId, String Name, String Email, String Password, String Phone) {
        this.AdminId = AdminId;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
    }

    public Admin(String Name, String Email, String Password, String Phone) {
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
    }

    public Admin() {

        this.AdminId = 0;
        this.Name = null;
        this.Email = null;
        this.Password = null;
        this.Phone = null;

    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

}
