/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Andy
 */
public class Sensor implements Serializable
{
    private GPSCoordList location;
    private boolean enabled;
    private long frequency;
    private Date lastCheck;
    private SensorType sensorType;
    private SensorReader sensorReader;
    private FieldStation fieldStation;
    
    public Sensor(GPSCoordList location, boolean enabled, long frequency, FieldStation fieldStation)
    {
        this.location = location;
        this.enabled = enabled;
        this.frequency = frequency;
        this.fieldStation = fieldStation;
    }
    
    public void updateSensorConfig(boolean enabled, long frequency)
    {
        this.enabled = enabled;
        this.frequency = frequency;
    }
    
    public boolean requiresUpdate()
    {
        return false; //check time to see if update is required?
    }
    
    public void setReader(SensorReader sensorReader)
    {
        this.sensorReader = sensorReader;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    public void readSensorDataIfRequired()
    {
        //function not defined
    }
}
