/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sets up a thread which loops every 5 seconds, and finds each sensor through
 * 5x for loops. The sensor is checked to see if it needs updating if it is
 * enabled, and if so, takes a reading and creates a SensorData and adds it
 * to SensorDataList for that Sensor.
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
            //For each user in the server's UserList:
            for (int i = 0; i < Main.getServer().getUserList().size(); i++)
            {
                user = Main.getServer().getUserList().get(i);
                //For each farm in the user's FarmList:
                for (int j = 0; j < user.getFarms().size(); j++)
                {
                    farm = user.getFarms().get(j);
                    //For each field in the farm's FieldList:
                    for (int k = 0; k < farm.getFields().size(); k++)
                    {
                        field = farm.getFields().get(k);
                        //For each plot in the field's PlotList:
                        for (int l = 0; l < field.getPlots().size(); l++)
                        {
                            plot = field.getPlots().get(l);
                            //For each sensor in the plot's SensorList:
                            for (int m = 0; m < plot.getSensors().size(); m++)
                            {
                                sensor = plot.getSensors().get(m);
                                if (sensor.isEnabled() && sensor.requiresUpdate()) //if enabled and requires an update
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
                Thread.sleep(5000); //set to 5 seconds, realistically would be 60 seconds, but for demo purposes.
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(FieldClock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(true);
    }
}

