/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

/**
 * Abstract class with 1 abstract method which is overridden by each sensor
 * reading class.
 * 
 * @author Andy
 */
public abstract class SensorReader 
{
    public abstract double readSensorData();
}
