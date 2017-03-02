/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy
 */
class FieldClock extends Thread 
{    
    User user = null;
    Farm farm = null;
    Field field = null;
    Plot plot = null;
    Sensor sensor = null;
    
    FieldClock() 
    {
        
    }
    
    @Override
    public void run() 
    {
        do
        {
            for (int i = 0; i < Main.getServer().getUserList().size(); i++)
            {
                user = Main.getServer().getUserList().get(i);
                for (int j = 0; j < user.getFarms().size(); j++)
                {
                    farm = user.getFarms().get(j);
                    for (int k = 0; k < farm.getFields().size(); k++)
                    {
                        field = farm.getFields().get(k);
                        for (int l = 0; l < field.getPlots().size(); l++)
                        {
                            plot = field.getPlots().get(l);
                            for (int m = 0; m < plot.getSensors().size(); m++)
                            {
                                sensor = plot.getSensors().get(m);
                                if (sensor.isEnabled() && sensor.requiresUpdate())
                                {
                                    //send update...
                                    //store random sensor data...
                                    SensorData sensorData = new SensorData(sensor.readSensorDataIfRequired());
                                    sensor.getSensorDataList().add(sensorData);
                                }
                                
                            }
                        }
                    }
                }
            }
            try 
            {
                Thread.sleep(60000);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(FieldClock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(true);
    }
}

