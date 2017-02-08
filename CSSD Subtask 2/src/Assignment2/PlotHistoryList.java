/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 *
 * @author Andy
 */
public class PlotHistoryList extends ArrayList<PlotHistory>
{
    public PlotHistoryList()
    {
        super();
    }
    
    public void addHistory(PlotHistory history)
    {
        super.add(history);
    }
    
    public void removeHistory(PlotHistory history)
    {
        super.remove(history);
    }
}
