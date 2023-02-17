package ccp.pkgfinal.ver;

import java.util.Random;

public class ATC extends Thread {
    
    private Plane p;
    private Random rand;
    
    public static final String TEXT_BLUE = "\u001B[36m";
    public static final String TEXT_RESET = "\u001B[0m";    

    public ATC(Plane p, Random rand){
        this.p = p;
        this.rand = rand;

    }


    public void landPlane () {
        //allowing the plane to land
        
        long startTime = System.nanoTime(); //returns the current value of the system timer

    String x;
      if (Main.gates.isEmpty()){
        System.out.println(TEXT_BLUE + "ATC :" +  " Please wait for a moment "+ p + "! The gates are full!" + TEXT_RESET);
      }

      while (Main.gates.isEmpty()){
          try{
              Thread.sleep(500);
          }
          catch (InterruptedException ex){}
      }

        x = Main.gates.poll(); //remove the first gate

        while ( !(Main.emergencyPlanes.isEmpty() || Main.emergencyPlanes.contains(p)) )
        {

          try {
            Thread.sleep(500);
          } catch(InterruptedException ex) {}
        }


        System.out.println(TEXT_BLUE + "ATC :" +TEXT_RESET + " Permission Granted for " + p + "! You have assigned to "+ x);

        p.setGate(x);
        p.setValue(true);

        p.setPriority(this.getPriority());
        

        p.start();
        
        try{
            p.join();
            if (Main.emergencyPlanes.contains(p)){
              Main.emergencyPlanes.remove(p);
            }
        }
        catch(InterruptedException ex){}

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        
        
        duration = duration - 3000;
        System.out.printf(p + " has waited for %.3f ms before landing\n", duration/1e6);


//        System.out.printf("The " + p + " wait for %.3f ms before landing\n", duration/1e6-3000 );
//        System.out.printf("Landing " + p + " took %.3f ms to land%n", duration/1e6);

        Main.addToList(duration);

        Passenger ps = new Passenger(p, x, false); //to disembark the passenger
        ps.start();

        try{
          ps.join();
        }
        catch (InterruptedException ex) {}
        

        /*************************************************************/

        Passenger ps2 = new Passenger(p, x, true); //to board the passenger
        ps2.start();

        try{
          ps2.join();
        }
        catch (InterruptedException ex) {}
        
        p = new Plane(p);
        
        System.out.println(TEXT_BLUE + "ATC :" +TEXT_RESET + " Permission Granted for taking off");

        
        p.setValue(false);

        p.start();
        
        try{
            p.join();
        }
        catch(InterruptedException ex){}

    }

    @Override
    public void run () {
        landPlane();
    }
}
