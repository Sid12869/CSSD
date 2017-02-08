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
public class Field implements Serializable
{
    private Area area;
    private PlotList plots;
    private String name;
    
    public Field()
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
