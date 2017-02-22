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
public class GPSCoord 
{
    private double lat = 0;
    private double lng = 0;
    
    public GPSCoord(double lat, double lng)
    {
        this.lat = lat;
        this.lng = lng;
    }
    
    public String toString(){
        return Double.toString(lat) +  ", " + Double.toString(lng);
    }
}
