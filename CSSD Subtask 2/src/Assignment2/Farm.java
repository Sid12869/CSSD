/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.Serializable;

/**
 * Holds a FieldList; all the Field objects within a Farm. Each Farm has an
 * Area to show the location of the Farm, as well as a name.
 * 
 * @author Andy
 */
public class Farm implements Serializable 
{
    private Area area;
    private FieldList fields = new FieldList();
    private String name;
    
    public Farm(Area area, String name)
    {
        this.area = area;
        this.name = name;
    }
    
    public String toString(){
        return name;
    }
    
    public FieldList getFields()
    {
        return fields;
    }
    
    public Area getArea()
    {
        return area;
    }
    
    public String getName()
    {
        return name;
    }
    
    //public Plot getPlotInPosition(GPSCoord location){return plot;} //undefined function, not needed for implementation.
}
