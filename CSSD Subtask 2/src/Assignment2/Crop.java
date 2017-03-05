/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

/**
 * Holds the type of crop that a Plot currently has.
 * 
 * @author Andy
 */
public class Crop 
{
    private String cropName;
    
    public Crop(String cropName)
    {
        this.cropName = cropName;
    }
    
    public String getCropName()
    {
        return cropName;
    }
}
