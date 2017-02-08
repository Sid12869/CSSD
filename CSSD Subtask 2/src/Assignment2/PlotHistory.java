/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.Date;

/**
 *
 * @author Andy
 */
public class PlotHistory 
{
    private Date startTime;
    private Date endTime;
    private double yieldKG;
    private Crop crop;
    
    public PlotHistory(Date startTime, Date endTime, double yieldKG, Crop crop)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.yieldKG = yieldKG;
        this.crop = crop;
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }
    
    public double getYield()
    {
        return yieldKG;
    }
    
    public Crop getCrop()
    {
        return crop;
    }
    
}
