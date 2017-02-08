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
    private GPSCoord sensorLoc;
    private Date dataTime;
    private int sensorId;
    private double rawData;
    private SensorType sensorType;
    
    public int getDataRepresentationType()
    {
        return 0;
    }
}
