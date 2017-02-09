/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 *
 * @author Andy
 */
public class FieldStationList extends ArrayList<FieldStation> 
{
    public FieldStationList()
    {
        super();
    }
    
    public void addFieldStation(FieldStation aFieldStation)
    {
        super.add(aFieldStation);
    }
    
    public void removeFieldStation(FieldStation aFieldStation)
    {
        super.remove(aFieldStation);
    }
}
