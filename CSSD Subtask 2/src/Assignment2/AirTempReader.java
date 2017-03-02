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
    //private final SensorType sensorType = SensorType.AIR_TEMPERATURE;
    private final double maximum = 40;
    private final double minimum = 4;
    
    /**
     *
     * @return
     */
    @Override
    public double readSensorData()
    {
        double randomNum = minimum + (double)(Math.random() * maximum);
        return randomNum;
    }
}
