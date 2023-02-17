package ccp.pkgfinal.ver;

import java.util.Random;
import java.util.*;

public class Main {

  private static int pCount;
  private static int boardedPCount; 
  public static volatile boolean windy = false;
  private static volatile long minTime = Long.MAX_VALUE; //to calculate the minTime
  private static volatile long maxTime = -1; //to calculate the maxTime
  
  public static volatile Deque<String> gates = new ArrayDeque<>(4); //to store the gate
  private static List<Long> times = new ArrayList<Long>(); //to count times
  public static volatile ArrayList<Plane> emergencyPlanes = new ArrayList<>(); //to store emergency plane


  public static void incrementboardedPCount(int n) {
    // to count number of passenger boarded
    boardedPCount += n;
  }
  public static void incrementPCount(int n) {
    // to count number of passenger disembark
    pCount += n;
  }

  public static void addToList(long time) {
    //to find the min and max time
    times.add(time);
    if (time < minTime) {
      minTime = time;
    }
    if (time > maxTime) {
      maxTime = time;
    }
  }

  public static void main(String[] args) {

    pCount = 0;
    boardedPCount = 0;
    Runway ph = new Runway();

    List<Plane> list = new ArrayList<Plane>(); 

    List<ATC> atcs = new ArrayList<ATC>();

    gates.add("Gate 1");
    gates.add("Gate 2");
    gates.add("Gate 3");
    gates.add("Gate 4");

    ATC atc;
    Random rand = new Random();

    Weather w = new Weather();
    w.start();

    int emergency = 0; //to count emergency planes
    for (int i = 1; i <= 10; i++) {

      int d = rand.nextInt(3000); //allowing the plane to land within 3 sec randomly

      try {
        Thread.sleep(d);
      } catch (InterruptedException ex) {}

      int seatingCapaciy = 50;
      int passengers = rand.nextInt(seatingCapaciy);

      Plane p = new Plane("Plane_" + i, ph, d < 1000, seatingCapaciy, passengers);

      
      atc = new ATC(p, rand);

      boolean isEmergency = false;
      if (d < 1000) {
        //if generate within 1 seconds
        atc.setPriority(Thread.MAX_PRIORITY);

        isEmergency = true;

        emergency++;
      }
      else {
        p.setPriority(2);
      }
      
      if (isEmergency) {
        
        emergencyPlanes.add(0, p);
      }
      
      atc.start();
      atcs.add(atc);
      list.add(p);

    }

    try {
      for (ATC a : atcs) {
        a.join();
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }

    w.flag = true;

    printStatistics(emergency);
  }

  private static void printStatistics(int emergency) {
    
    if(gates.contains("Gate 1") && gates.contains("Gate 2") && gates.contains("Gate 3") && gates.contains("Gate 4")){
        System.out.println("\nALL OF THE BAYS ARE NOW EMPTY!");
    }
    else{
        System.out.println("\nSOME OF THE BAYS ARE NOT EMPTY YET!");
    }
      
    System.out.println();
    double avg = calculateAverage();
    System.out.println("-----------------------STATISTICS-----------------------");
    System.out.println("Total Number of Planes Landed            : " + times.size());
    System.out.println("Number of Planes Had emergency           : " + emergency);
    System.out.println("Minimum time of waiting for the plane    : " + minTime / 1e6 + " ms.");
    System.out.println("Maximum time of waiting for the plane    : " + maxTime / 1e6 + " ms.");
    System.out.println("Average time of waiting and landing time : " + avg + " ms.");
    System.out.println("Number of Passengers Departed            : " + pCount);
    System.out.println("Number of Passengers Boarded             : " + boardedPCount);
    System.out.println("--------------------------------------------------------");
    
  }

  private static double calculateAverage() {
    // calculate the average
    double sum = 0;
    int size = times.size();
    for (long t : times) {
      sum += t / 1e6;
    }

    return sum / size;
  }
}
