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
public class Account implements Serializable {

    private String username;
    private String password;
    
    public Account(String newUsername, String newPassword)
    {
       username =  newUsername;
       password = newPassword;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
}
