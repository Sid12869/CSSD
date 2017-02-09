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
public class Core 
{
    /*
    Server should stay the same as long as the program is running
    Server can be accessed from any GUI class. 
    This is why it is public and static. 
    */
    public static Server server = new Server();
    //public static User myAccount;
    
    //set up some basic stuff
    public Core()
    {
        User account1 = new User("Group Q", "password", AccessLevel.FOOD_PROCESSOR);
        User account2 = new User("Sid", "password", AccessLevel.FOOD_PROCESSOR);
        User account3 = new User("Andy", "password", AccessLevel.FARMER);
        server.registerUser(account1);
        server.registerUser(account2);
        server.registerUser(account3);
        
        //testing GPS/AREAS/FARMS
        GPSCoord location1 = new GPSCoord(53.378480, -1.429769);
        GPSCoord location2 = new GPSCoord(53.378051, -1.429769);
        GPSCoord location3 = new GPSCoord(53.378096, -1.428911);
        
        GPSCoordList location = new GPSCoordList();
        location.add(location1);
        location.add(location2);
        location.add(location3);
        
        Area area = new Area(location); 
        
        Farm farm = new Farm(area, "Sheffield Farm");
        
        account1.addFarm(farm);
        account2.addFarm(farm);
        account3.addFarm(farm);
    }
    
    public static void login(User user)
    {
        //myAccount = user;
    }
        
     /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        new Core(); //set up core class
        
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
            }
        });
    }
}
