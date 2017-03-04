/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Andy
 */
public class SensorData 
{
    //private GPSCoordList sensorLoc; //not needed. use Sensor instead
    //private Sensor sensor; //not needed. 
    private Calendar dataTime = Calendar.getInstance();
    //private int sensorId; //unneccessary identifier
    private double rawData;
    //private SensorType sensorType; //not needed. sensor will already know its type
    
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
