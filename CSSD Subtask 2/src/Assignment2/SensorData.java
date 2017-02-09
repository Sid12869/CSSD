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
public class SensorData 
{
    //private GPSCoordList sensorLoc; //not needed. use Sensor instead:
    private Sensor sensor;
    private Date dataTime;
    private int sensorId;
    private double rawData;
    private SensorType sensorType;
    
    public SensorData(Sensor sensor, Date dataTime, double rawData, SensorType sensorType)
    {
        this.sensor = sensor;
        this.dataTime = dataTime;
        this.rawData = rawData;
        this.sensorType = sensorType;
    }
    
    public int getDataRepresentationType()
    {
        return 0;
    }
}
