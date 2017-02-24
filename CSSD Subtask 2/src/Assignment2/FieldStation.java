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
public class FieldStation implements Serializable
{
    //private SensorList sensors = new SensorList(); //moved to Plot.java
    private GPSCoord location;
    //private MobileNetwork mobileNetwork; //not necessary?
    //private UserList users; //not necessary?
    private String uniquePhoneNumber;
    private String setupSecretKey;
    private SensorDataList sensorData;
    private Date lastSyncTime;
    private FieldClock fieldClock;
    
    public FieldStation(GPSCoord location, String uniquePhoneNumber, String setupSecretKey)
    {
        this.location = location;
        this.uniquePhoneNumber = uniquePhoneNumber;
        this.setupSecretKey = setupSecretKey;
    }
    
    public GPSCoord getGPSCoord()
    {
        return location;
    }
    
    public String getPhoneNumber()
    {
        return uniquePhoneNumber;
    }
    
    public String getUniqueCode()
    {
        return setupSecretKey;
    }
    
    public void registerStationWithServer()
    {
        //function not defined
    }
    
    public void detectSensors()
    {
        //function not defined
    }
    
//    public Sensor findSensor(GPSCoordList location)
//    {
//        return aSensorFromSensorList;
//    }
    
    public void sendDataToServer()
    {
        //function not defined
    }
    
    public void addDataToSyncList(SensorData sensorData)
    {
        //function not defined
    }
}
