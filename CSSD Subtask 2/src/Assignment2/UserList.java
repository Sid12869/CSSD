/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 *
 * @author Sid
 */
public class UserList extends ArrayList<User> {
    
    public UserList(){
        super();
    }
    
    public void addAccount(User aAccount){
        super.add(aAccount);
    }
    
    public User authenticate(String username, String password)
    {
        for(int i = 0; i < this.size(); i++)
        {
            if (this.get(i).getUsername().equalsIgnoreCase(username)) //ignore case sensitivity
            {
                if(this.get(i).getPassword().equals(password))
                {    
                    return this.get(i);
                }
            }
        }
        return null;
    }
}
