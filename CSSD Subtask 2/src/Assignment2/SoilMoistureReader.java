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
public class SoilMoistureReader extends SensorReader
{
    //min and max values for soil moisture
    private final double maximum = 200;
    private final double minimum = 0;
    
    /**
     * Generates a random double between 0 and 200 to return as
     * a sensor data reading for soil moisture.
     * 
     * @return a random number between 0-200
     */
    @Override
    public double readSensorData()
    {
        double randomNum = minimum + (double)(Math.random() * maximum);
        return randomNum;
    }
}
