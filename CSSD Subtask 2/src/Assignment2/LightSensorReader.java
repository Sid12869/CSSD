/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

/**
 *
 * @author b4028595
 */
public class LightSensorReader extends SensorReader
{
    //min and max values for light
    private final double maximum = 100000;
    private final double minimum = 0;

    /**
     * Generates a random double between 0 and 100000 to return as
     * a sensor data reading for the light sensors.
     * 
     * @return a random number between 0-100000
     */
    @Override
    public double readSensorData()
    {
        double randomNum = minimum + (double)(Math.random() * maximum);
        
        return randomNum;
    }
}
