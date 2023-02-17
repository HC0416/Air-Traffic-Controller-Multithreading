package ccp.pkgfinal.ver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Weather extends Thread {
    
    public static volatile boolean flag = false;
    
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RESET = "\u001B[0m";
    

    @Override
    public void run(){
        
        while(!this.flag){
            
            try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(this.flag){
                break;
            }
            
            Main.windy = true;
            System.out.println(TEXT_GREEN + "Weather is windy for the next 5 seconds. Therefore, no new plane can be assigned "
                    + "any gate." + TEXT_RESET);

            try {
                Thread.sleep(5000); //let it windy for 5 seconds
            } catch (InterruptedException ex) {
                Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Main.windy = false;
            System.out.println(TEXT_GREEN + "Now the weather is fine." + TEXT_RESET);
        }
    }
    
}
