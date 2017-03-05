/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 * An ArrayList of Plot objects.
 * 
 * @author Andy
 */
public class PlotList extends ArrayList<Plot>
{
    public PlotList()
    {
        super();
    }
    
    public void addPlot(Plot plot)
    {
        super.add(plot);
    }
    
    public void removePlot(Plot plot)
    {
        super.remove(plot);
    }
}
