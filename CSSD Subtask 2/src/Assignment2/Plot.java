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
public class Plot 
{
    private Area area;
    private SensorDataList sensorData;
    private PlotState plotState;
    private Crop currentCrop;
    private PlotHistoryList history;
    private String name;
    private SensorList sensors = new SensorList();
    
    public Plot(String name, Area area, PlotState plotState, Crop currentCrop)
    {
        this.name = name;
        this.area = area;
        this.plotState = plotState;
        this.currentCrop = currentCrop;
    }
    
    public String toString()
    {
        return name;
    }
    
    public SensorList getSensors()
    {
        return sensors;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void newHarvest()
    {
        //function not defined
    }
    
    public void plotHarvested()
    {
        //function not defined
    }
}
