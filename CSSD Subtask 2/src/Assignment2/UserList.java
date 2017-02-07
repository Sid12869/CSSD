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
    
    public void removeAccount(User aAccount){
        super.remove(aAccount);
    }
    
    public User authentication(String username, String password)
    {
        for(int i = 0; i < this.size(); i++)
        {
            if (this.get(i).getUsername().equals(username))
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
