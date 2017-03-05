/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.Calendar;
import java.util.Date;

/**
 * Holds all the information of a Sensor reading within a Calendar object.
 * 
 * @author Andy
 */
public class SensorData 
{
    private Calendar dataTime = Calendar.getInstance();
    private double rawData;
    
    public SensorData(double rawData)
    {
        this.rawData = rawData;
    }
    
    @Override
    public String toString()
    {
        return dataTime.getTime() + " - " + rawData;
    }
    
    public Date getTime()
    {
        return dataTime.getTime();
    }

    public double getDataRepresentationType()
    {
        return rawData;
    } 
}
