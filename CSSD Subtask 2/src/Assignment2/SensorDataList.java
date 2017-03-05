/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 * An ArrayList of SensorData objects. 
 * @author Andy
 */
public class SensorDataList extends ArrayList<SensorData>
{
    public SensorDataList()
    {
        super();
    }
    
    public void addSensorData(SensorData sensorData)
    {
        super.add(sensorData);
    }
    
    public void removeSensorData(SensorData sensorData)
    {
        super.remove(sensorData);
    }
}
