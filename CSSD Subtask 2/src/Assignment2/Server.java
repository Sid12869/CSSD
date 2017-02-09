/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

/**
 *
 * @author Andy
 */
public class Server 
{
    private FieldStationList registeredFieldStations;
    private SensorList sensors;
    private FarmList farms; //changed from 'DataManagement'.
    private UserList users = new UserList(); //changed from 'DataManagement'.
    
    public Server()
    {
        //constructor not defined
    }
    
    public void registerUser(User user)
    {
        users.add(user);
    }
    
    public UserList getUserList()
    {
        return users;
    }
    
    public void registerFieldStation(GPSCoordList location, String phoneNo, String setupKey)
    {
        FieldStation station = new FieldStation(location, phoneNo, setupKey);
        registeredFieldStations.add(station);
    }
    
    public void registerSensors(GPSCoordList location, boolean enabled, long frequency, FieldStation station)
    {
        Sensor sensor = new Sensor(location, enabled, frequency, station);
        sensors.add(sensor);
    }
    
    public void linkFieldStationToUser(String secretKey)
    {
        //function not defined
    }
    
    public void syncDataFromStation(SensorDataList sensorDatas)
    {
        //function not defined
    }
    
    public void sendUpdateSensorMessage(boolean enabled, long frequency)
    {
        //function not defined
        //update a sensor
    }
}
