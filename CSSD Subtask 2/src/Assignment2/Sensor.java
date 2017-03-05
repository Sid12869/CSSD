/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Each Sensor has a single GPSCoord location, and can be set to enabled or
 * disabled through a boolean, which the user can change. It has a frequency,
 * which is the amount of time between each time a reading should be taken.
 * It does this by creating a time when an object is created, and the FieldClock
 * thread checks if that time + the frequency is past the current time, and will
 * update if required. The time will then be set to the time + frequency,
 * so the thread can check for the next update time. 
 * Each Sensor is a different type, defined in SensorType enumeration class. 
 * Sensors have a list of SensorData which is used to generate graphs. 
 * 
 * @author Andy
 */
public class Sensor implements Serializable
{
    private GPSCoord location;
    private boolean enabled;
    private long frequency;
    private Calendar lastCheck = Calendar.getInstance();
    private SensorType sensorType;
    private SensorDataList sensorData = new SensorDataList();
    
    public Sensor(GPSCoord location, boolean enabled, long frequency, SensorType sensorType)
    {
        this.location = location;
        this.enabled = enabled;
        this.frequency = frequency;
        this.sensorType = sensorType;
    }
    
    public void updateSensorConfig(boolean enabled, long frequency, GPSCoord location)
    {
        this.enabled = enabled;
        this.frequency = frequency;
        this.location = location;
    }
    
    @Override
    public String toString()
    {
        if (enabled)
        {
            return sensorType.name() + ", " + location.toString() + " - Enabled";
        }
        else
        {
            return sensorType.name() + ", " + location.toString() + " - Disabled";
        }
    }
    
    public GPSCoord getCoord()
    {
        return location;
    }
    
    public long getFrequency()
    {
        return frequency;
    }
    
    public SensorType getSensorType()
    {
        return sensorType;
    }
    
    public SensorDataList getSensorDataList()
    {
        return sensorData;
    }
    
    public boolean requiresUpdate()
    {
        Calendar date = Calendar.getInstance(); //get current date/time
        Calendar nextUpdate = Calendar.getInstance(lastCheck.getTimeZone()); //get copy of lastCheck
        
        nextUpdate.setTime(lastCheck.getTime()); //get copy of lastCheck
        nextUpdate.add(Calendar.SECOND, (int) frequency); //add frequency to lastCheck, would normally add as minute (not second), but for demo purposes
        if (date.after(nextUpdate)) 
        {
            //if nextUpdate has passed the current time then return true
            lastCheck = nextUpdate;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    public double readSensorDataIfRequired()
    {
        double data = 0.0;
        if (sensorType == SensorType.AIR_TEMPERATURE)
        {
            AirTempReader airTempReader = new AirTempReader();
            data = airTempReader.readSensorData();
        }
        else if (sensorType == SensorType.ACIDITY)
        {
            SoilAcidityReader soilAcidityReader = new SoilAcidityReader();
            data = soilAcidityReader.readSensorData();
        }
        else if (sensorType == SensorType.PRESSURE)
        {
            PressureReader pressureReader = new PressureReader();
            data = pressureReader.readSensorData();
        }
        else if (sensorType == SensorType.LIGHT_SENSOR)
        {
            LightSensorReader lightSensorReader = new LightSensorReader();
            data = lightSensorReader.readSensorData();
        }
        else if (sensorType == SensorType.SOIL_MOISTURE)
        {
            SoilAcidityReader soilMoistureReader = new SoilAcidityReader();
            data = soilMoistureReader.readSensorData();
        }
        else if (sensorType == SensorType.SOIL_TEMPERATURE)
        {
            SoilTempReader soilTempReader = new SoilTempReader();
            data = soilTempReader.readSensorData();
        }
        return data;
    }
}
