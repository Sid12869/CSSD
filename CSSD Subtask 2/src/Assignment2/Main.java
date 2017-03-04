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
public class Main 
{
    /*
    Server should stay the same as long as the program is running
    Server can be accessed from any GUI class. 
    This is why it is public and static. 
    */
    private static Server server = new Server();
    //public static User myAccount;
    
    //set up some basic stuff
    public Main()
    {
        User account1 = new User("Group Q", "password", AccessLevel.FARMER);
        User account2 = new User("Sid", "password", AccessLevel.FOOD_PROCESSOR);
        User account3 = new User("Andy", "password", AccessLevel.FOOD_PROCESSOR);
        server.registerUser(account1);
        server.registerUser(account2);
        server.registerUser(account3);
        
        //set up data
        GPSCoord location1 = new GPSCoord(53.378480, -1.429769);
        GPSCoord location2 = new GPSCoord(53.378051, -1.429769);
        GPSCoord location3 = new GPSCoord(53.378096, -1.428911);
        
        GPSCoordList location = new GPSCoordList();
        location.add(location1);
        location.add(location2);
        location.add(location3);
        
        Area area = new Area(location); 
        
        Farm farm1 = new Farm(area, "Sheffield Farm");
        Farm farm2 = new Farm(area, "Rotherham Farm");
        
        Field field1 = new Field(area, "North Field");
        Field field2 = new Field(area, "South Field");
        
        
        FieldStation fieldStation1 = new FieldStation(location1, "07485767341", "123");
        
        field1.setFieldStation(fieldStation1);
        field2.setFieldStation(fieldStation1);
        
        Crop crop = new Crop("Corn");
        Plot plot1 = new Plot("North Plot", area, PlotState.PLANTED, crop);
        Plot plot2 = new Plot("South Plot", area, PlotState.SPROUTED, crop);
        
        Sensor sensor1 = new Sensor(location1, true, 10, SensorType.AIR_TEMPERATURE);
        Sensor sensor2 = new Sensor(location3, true, 10, SensorType.ACIDITY);
        
        plot1.getSensors().add(sensor1);
        plot1.getSensors().add(sensor2);
        plot2.getSensors().add(sensor1);
        plot2.getSensors().add(sensor2);
        
        field1.getPlots().add(plot1);
        field1.getPlots().add(plot2);
        field2.getPlots().add(plot1);
        field2.getPlots().add(plot2);
        
        farm1.getFields().add(field1); //add field to farm
        farm1.getFields().add(field2); //add field to farm
        
        farm2.getFields().add(field2); //add field to farm
        
        account1.addFarm(farm1);
        account1.addFarm(farm2);
        
        account2.addFarm(farm1);
        account3.addFarm(farm2);
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        new Main(); //set up core class
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
                FieldClock clock = new FieldClock();
                clock.start();
            }
        });
    }
    
    public static Server getServer()
    {
        return server;
    }
}