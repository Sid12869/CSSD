/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.Serializable;

/**
 *
 * @author Sid
 */
public class Accounts implements Serializable {

    private String username;
    private String password;
    
    public Accounts(String newUsername, String newPassword)
    {
       username =  newUsername;
       password = newPassword;
    }
    
    String getUsername()
    {
        return username;
    }
    
    String getPassword()
    {
        return password;
    }
    
}
