/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.Serializable;

/**
 *
 * @author Andy
 */
public class Farm implements Serializable 
{
    private Area area;
    private FieldList FieldList;
    private int id; //not necessary because not a database?
    private String name;
    
    public Farm()
    {
        
    }
    
    /*public Plot getPlotInPosition(GPSCoord location)
    {
        return plot;
    }*/
    
    public Area getArea()
    {
        return area;
    }
}
