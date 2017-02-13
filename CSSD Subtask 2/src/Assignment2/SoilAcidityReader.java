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
public class SoilAcidityReader extends SensorReader
{
    private final SensorType sensorType = SensorType.ACIDITY;
    private final double maximum = 14;
    private final double minimum = 0;
    
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
