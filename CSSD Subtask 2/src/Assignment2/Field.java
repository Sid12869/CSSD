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
    private PlotList plots = new PlotList();
    private String name;
    private FieldStation fieldStation = new FieldStation(null, null, null, null);
    
    public Field(Area area, String name)
    {
        this.area = area;
        this.name = name;
    }
    
    /*public Plot getPlotInPosition(GPSCoord location)
    {
        return plot;
    }*/
    
    public void setFieldStation(FieldStation fieldStation)
    {
        this.fieldStation = fieldStation;
    }
    
    public FieldStation getFieldStation()
    {
        return fieldStation;
    }
    
    public String toString(){
        return name;
    }
    
    public PlotList getPlots()
    {
        return plots;
    }
    
    public Area getArea()
    {
        return area;
    }
    
    public String getName()
    {
        return name;
    }
}
