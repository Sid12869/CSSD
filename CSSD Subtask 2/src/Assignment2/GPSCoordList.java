/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 * An ArrayList of GPSCoord objects. This is stored in an Area, which is used
 * to draw an area of a Farm/Field/Plot.
 * 
 * @author Andy
 */
public class GPSCoordList extends ArrayList<GPSCoord>
{
    public GPSCoordList(){
        super();
    }
    
    public void addGPSCoords(GPSCoord gpsCoords){
        super.add(gpsCoords);
    }
}
