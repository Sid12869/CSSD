/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.Serializable;

/**
 * Holds account information for each user. Each account has an AccessLevel,
 * which limits their access to the system dependant on their account type.
 * Accounts also have a FarmList, each Farm has a FieldList, which has a 
 * PlotList, which has a SensorList. In turn, all data is connected to the
 * user account. 
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
        farms.add(aFarm);
    }
    
    public void removeFarm(Farm aFarm)
    {
        farms.add(aFarm);
    }
    
    public FarmList getFarms()
    {
        return farms;
    }
}
