/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 * An ArrayList of Farm objects. 
 * 
 * @author Andy
 */
public class FarmList extends ArrayList<Farm> 
{
    private Area area;
    
    public FarmList()
    {
        super();
    }
    
    public void addFarm(Farm aFarm)
    {
        super.add(aFarm);
    }
    
    public void removeFarm(Farm aFarm)
    {
        super.remove(aFarm);
    }
    
    public FieldList getFieldsByFarmName(String farmName)
    {
        for(int i = 0; i < this.size(); i++)
        {
            if (this.get(i).getName().equals(farmName))
            {
                return this.get(i).getFields();
            }
        }
        return null;
    }
    
    //public Area findAreaByCoords(){} not shown in class diagram. needed to return coords in area?
}
