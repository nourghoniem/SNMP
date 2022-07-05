/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registerHandling;

/**
 *
 * @author Salma
 */
public class ErrMsgs {

    private String email;
    private String phone;
    private String password;
    private String userName;

    public ErrMsgs() {
    }

    public ErrMsgs(String email, String phone, String password, String userName) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
