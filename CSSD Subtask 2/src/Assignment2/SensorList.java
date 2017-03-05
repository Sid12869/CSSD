/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 * An ArrayList of Sensor objects.
 * @author Andy
 */
public class SensorList extends ArrayList<Sensor> 
{
    public SensorList()
    {
        super();
    }
    
    public void addSensor(Sensor aSensor)
    {
        super.add(aSensor);
    }
    
    public void removeSensor(Sensor aSensor)
    {
        super.remove(aSensor);
    }
}
