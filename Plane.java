package ccp.pkgfinal.ver;


public class Plane extends Thread {

    double landingTime = 0.0;
    int passengerCount = 50;
    int seats = 0;
    String name;
    Runway ph;
    boolean emergency;
    String gate;
    boolean value = false;
    
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public void setValue (boolean value){
      // to check weather to land or depart
      this.value = value;
    }

    public void setGate (String gate){
        // assign a gate
        this.gate = gate;
    }


    public Plane(String name, Runway ph, boolean emergency, int seats, int passengerCount){
        this.name = name;
        this.ph = ph;
        this.emergency = emergency;
        this.seats = seats;
        this.passengerCount = passengerCount;

        if (emergency) {
          System.out.println(TEXT_RED + name+ ": Emergency! Permission to Land!" + TEXT_RESET);
        }
        else{
           System.out.println(TEXT_RED +name + TEXT_RESET + ": Permission to land!"); 
        }
        
    }

    private Plane() {}

    public Plane (Plane p){
      // for departing
      this.name = p.name;
      this.ph = p.ph;
      this.emergency = p.emergency;
      this.gate = p.gate;
      this.value = p.value;
      
      System.out.println(TEXT_RED + p.name + TEXT_RESET + ": Ready to depart !");
    }

    @Override
    public void run () {

        if (Main.windy) {
            
          if(value == true){
                System.out.println(this + ": cannot land due to windy weather! ");
          }
          else{
              System.out.println(this + ": cannot depart due to windy weather!");
          }
          
          if (this.getPriority() < 10)
          this.setPriority(7);

          checkWeather();
        }

        if (this.value) {
          ph.access_runway(this, gate); 
          
          System.out.println(this + ": refulling fuel and disembarking passengers....");
        } 
        else {
          ph.access_runway(this, gate); 
        }

    }

    private static void checkWeather(){
      //check if the weather is windy
      while (Main.windy) {
          try{
              Thread.sleep(500);
          }
          catch (InterruptedException ex){}
        }
    }
    
    @Override
    public String toString(){

        return name; //return the plane name
    }

}
