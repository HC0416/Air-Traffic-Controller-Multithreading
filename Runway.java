package ccp.pkgfinal.ver;

public class Runway {
    
    public synchronized void access_runway(Plane p, String gate){
        
        System.out.println(p + " is accessing the runway...");
        
        if(p.value == true)
        {
            land(p, gate);
        }
        else{
            
            depart(p, gate);
        }
    }
    
    public static void land(Plane p, String gate){
        //land a plane
        
        try{
            Thread.sleep(3000);
        } catch(InterruptedException ex){}

            System.out.println(p.toString() + " has landed and stopped at " + gate);
    }

    public static void depart (Plane p, String gate) {
      //depart a plane
      
      try{
            Thread.sleep(3000);
      } catch(InterruptedException ex){}

      System.out.println(p + " takeoff!");

      Main.gates.add(gate);
    }
}
