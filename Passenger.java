package ccp.pkgfinal.ver;

import java.util.Random;

public class Passenger extends Thread {

    static Random rand = new Random();

    Plane p;
    String gate;
    boolean boarding;
    
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RESET = "\u001B[0m";
    
    public Passenger(Plane p, String gate, boolean boarding) {
        this.p = p;
        this.gate = gate;
        this.boarding = boarding;
    }

    @Override
    public void run () {
      if (!this.boarding) {
        this.disembark();
      }
      else {
        this.board();
      }
    }

    private void board() {
      //to board the plane

      int count = rand.nextInt(50);

      try{

          p.passengerCount += count;
          Main.incrementboardedPCount(count);

          Thread.sleep(1000);
          System.out.println(TEXT_YELLOW + count + " passenger(s) are boarding to " + p + " now." + TEXT_RESET);
      }
      catch (InterruptedException ex){}
    }

    private void disembark () {
        //to disembark the plane
       
        Main.incrementPCount(p.passengerCount);
        p.passengerCount = 0;
        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException ex){}
    }
}
