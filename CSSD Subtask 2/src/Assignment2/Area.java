/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

/**
 * Holds a GPSCoordList for objects including Farm, Field and Plot
 * 
 * @author Andy
 */
public class Area 
{
    private GPSCoordList gpsPoints = new GPSCoordList();
    
    public Area(GPSCoordList gpsPoints)
    {
        this.gpsPoints = gpsPoints;
    }
    
    public GPSCoordList getCoordList()
    {
        return gpsPoints;
    }
}
