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
public class User implements Serializable {
    
    private String username;
    private String password;
    private FarmList farms; //list of the farms the user has access to
    private AccessLevel accessLevel;
    
    public User(String username, String password, AccessLevel accessLevel)
    {
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        farms = new FarmList();
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public AccessLevel getAccessLevel()
    {
        return accessLevel;
    }
    
    public void addFarm(Farm aFarm)
    {
        farms.addFarm(aFarm);
    }
    
    public void removeFarm(Farm aFarm)
    {
        farms.removeFarm(aFarm);
    }
}
