/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Assignment2.AccessLevel;
import Assignment2.Sensor;
import Assignment2.SensorList;
import Assignment2.User;
import Assignment2.UserList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Andy
 */
public class UnitTests 
{
    private static User[] users;
    private static Sensor[] sensors;
    
    UserList userList = new UserList();
    SensorList sensorList = new SensorList();
    User user;
            
    public UnitTests() 
    {}
    
    @BeforeClass
    public static void setUpClass() 
    {
        users = new User[3];
       
        users[0] = new User("Group Q", "password", AccessLevel.FARMER);
        users[1] = new User("Sid", "password", AccessLevel.FOOD_PROCESSOR);
        users[2] = new User("Andy", "password", AccessLevel.FOOD_PROCESSOR);        
        
//        GPSCoord location1 = new GPSCoord(53.378480, -1.429769);
//        GPSCoord location2 = new GPSCoord(53.378051, -1.429769);
//        GPSCoord location3 = new GPSCoord(53.378096, -1.428911);
//        
//        GPSCoordList location = new GPSCoordList();
//        location.add(location1);
//        location.add(location2);
//        location.add(location3);
//        
//        Area area = new Area(location); 
//        
//        Farm farm1 = new Farm(area, "Sheffield Farm");
//        Farm farm2 = new Farm(area, "Rotherham Farm");
//        
//        Field field1 = new Field(area, "North Field");
//        Field field2 = new Field(area, "South Field");
//                
//        Crop crop = new Crop("Corn");
//        Plot plot1 = new Plot("North Plot", area, PlotState.PLANTED, crop);
//        Plot plot2 = new Plot("South Plot", area, PlotState.SPROUTED, crop);
//        
//        Sensor sensor1 = new Sensor(location1, true, 10, SensorType.AIR_TEMPERATURE);
//        Sensor sensor2 = new Sensor(location3, true, 10, SensorType.ACIDITY);
//        
//        plot1.getSensors().add(sensor1);
//        plot1.getSensors().add(sensor2);
//        plot2.getSensors().add(sensor1);
//        plot2.getSensors().add(sensor2);
//        
//        field1.getPlots().add(plot1);
//        field1.getPlots().add(plot2);
//        field2.getPlots().add(plot1);
//        field2.getPlots().add(plot2);
//        
//        farm1.getFields().add(field1); //add field to farm
//        farm1.getFields().add(field2); //add field to farm
//        
//        farm2.getFields().add(field2); //add field to farm
        
//        user1.addFarm(farm1);
//        user1.addFarm(farm2);
//        
//        user2.addFarm(farm1);
//        user3.addFarm(farm2);
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        
    }
    
    @Before
    public void setUp() 
    {
        userList = new UserList();
        for(int i = 0; i < users.length; ++i) {
            userList.addAccount(users[i]);
        }
    }
    
    @After
    public void tearDown() 
    {
        
    }

    @Test
    public void testFindUserByCredentials()
    {
        user = userList.authenticate("andy", "password");
        assert(user.getUsername().equals("Andy"));
    }
    
    @Test
    public void theComplexTest()
    {
        //set up sensors
        //create reading
        //return data n stuff
    }
}
