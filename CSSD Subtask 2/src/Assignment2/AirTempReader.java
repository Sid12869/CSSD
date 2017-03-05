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
public class AirTempReader extends SensorReader
{
    //min and max values for air temp
    private final double maximum = 40;
    private final double minimum = 4;
    
    /**
     * Generates a random double between 4 and 40 to return as
     * a sensor data reading for air temperature.
     * 
     * @return a random number between 4-40
     */
    @Override
    public double readSensorData()
    {
        double randomNum = minimum + (double)(Math.random() * maximum);
        return randomNum;
    }
}
