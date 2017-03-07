/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Assignment2.AccessLevel;
import Assignment2.Area;
import Assignment2.Farm;
import Assignment2.FarmList;
import Assignment2.Field;
import Assignment2.FieldList;
import Assignment2.FieldStation;
import Assignment2.GPSCoord;
import Assignment2.GPSCoordList;
import Assignment2.Sensor;
import Assignment2.SensorData;
import Assignment2.SensorList;
import Assignment2.SensorType;
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
    private static Farm[] farms;
    private static Field[] fields;
    private static Sensor sensor;
    private static FieldStation fieldStation;
    
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
        
        //GPSCoord and Area classes set up for sensor and farm classes
        GPSCoord location1 = new GPSCoord(53.378480, -1.429769);
        GPSCoordList location = new GPSCoordList();
        location.add(location1);
        Area area = new Area(location); 
        
        sensor = new Sensor(location1, true, 0, SensorType.AIR_TEMPERATURE);
        
        farms = new Farm[2];
        farms[0] = new Farm(area, "Sheffield Farm");
        farms[1] = new Farm(area, "Rotherham Farm");
        
        fields = new Field[2];
        fields[0] = new Field(area, "North Field");
        fields[1] = new Field(area, "South Field");
        
        fieldStation = new FieldStation(null, null, null);
    }
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() 
    {
        userList = new UserList();
        for(int i = 0; i < users.length; ++i) {
            userList.addAccount(users[i]);
        }
        
        sensorList = new SensorList();
        sensorList.add(sensor);
        
        farms[0].getFields().add(fields[0]);
        farms[0].getFields().add(fields[1]);
        
        users[0].addFarm(farms[0]);
        users[0].addFarm(farms[1]);
    }
    
    @After
    public void tearDown() {}
    
    /*The username passed to the authenticate function is not case sensitive,
    however, if the name stored does have mixed case, then that is what will
    be retrieved. This is shown in the equals() function, which will check
    case sensitivity of a string. In this case, "andy" would be incorrect.*/
    @Test
    public void testFindUserByCredentials()
    {
        user = userList.authenticate("andy", "password");
        assert(user.getUsername().equals("Andy"));
    }
    
    /*As the sensor is for air temperature, the reading should not be below 0
    or above 50 (these are our pre-set figures, which could be changed)*/
    @Test
    public void testSensorData()
    {
        SensorData sensorData = new SensorData(sensor.readSensorDataIfRequired());
        sensor.getSensorDataList().add(sensorData);
        assert(sensorData.getDataRepresentationType() > 0 && sensorData.getDataRepresentationType() < 50);
    }
    
    /*The list of farms is added to the first user. Once we get the list of
    farms from the user object, we can directly use the getFieldsByFarmName()
    function to find the list of fields inside the farm object that is returned
    from the specified name parameter passed. 
    In this case we are finding the second field in the farm object, which
    should be the "South Field" field object.*/
    @Test
    public void testFarmByName()
    {
        FarmList theFarms;
        FieldList theFields;
        Field aField;
        theFarms = users[0].getFarms();
        theFields = theFarms.getFieldsByFarmName("Sheffield Farm");
        aField = theFields.get(1);
        assert(aField.getName().equals("South Field"));
    }
    
    /*Field stations are set up as null when the object is created within a 
    field, so this test checks that once the field station is updated the
    GPSCoord value is not null. The other values should not be null either.*/
    @Test
    public void testFieldStationUpdate()
    {
        String uniquePhoneNumber = "0987654321";
        String setupSecretKey = "123";
        GPSCoord aLocation = new GPSCoord(53.378480, -1.429769);
        fieldStation.updateStation(aLocation, uniquePhoneNumber, setupSecretKey);
        assert(fieldStation.getGPSCoord() != null);
    }
    
}
