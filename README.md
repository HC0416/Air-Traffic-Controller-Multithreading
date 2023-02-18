# Air-Traffic-Controller-Multithreading

This project had been implemented using multithreading concept to simulate an air traffic control with Java language.

## Simulation Details:<br />
1. There will be 10 planes trying to land at the airport within every 0 to 3 seconds.

2. There will be only 4 gates at the airport for the planes to land on. The planes will need have the permission from Air Traffic Controller (ATC) to access the runway and land at the gate. **Only one plane can access the runway at one time.**

3. If all gates are full, the planes will need to wait until permission given from ATC.

4. Once an aircraft obtains permission to land, it should land on the runway, coast to the assigned gate, dock to the gate, allow passengers to disembark, refill supplies and fuel, receive new passengers, undock, coast to the assigned runway and take-off. <br/>

5. The Air Traffic Controller (ATC) will mainly has 3 tasks which are:<br/>
   i) Ensure that the aircraft does not collide with another aircraft or any other obstacle. <br/>
   ii) Ensure that the approaching aircraft is inserted smoothly into the traffic around the airport, with a minimum of disruption to the flight paths of other aircraft <br/>
   iii) Guide the aircraft onto a runway, with a minimum of disruption to the rest of the traffic around the airport <br/><br/>

6. Special cases, such as aircraft experiencing mechanical malfunctions, medical crises, or fuel shortages, and changing weather conditions will be implemented. <br/>

7. At the end of the simulation, when all other processes have terminated cleanly, the ATC will do some sanity checks of the airport and print out some statistics on the run. 

## Output<br />
<img src="https://user-images.githubusercontent.com/82216057/219592380-2255619a-9b04-4b0f-ac1c-182db5ca7103.jpg" width="700" height="800">

Each of the threats are running concurrently and doing its own jobs. The planes requets to access the gateway and land on a gate. After the ATC checks there is empty gates, the ATC will allow the plane to access the gateway one at a time. The plane will then disembark the passengers and start filling the fuel and new passengers start to abroad. After that, it will once request the ATC to access the runway and then take off from the gate.

<img src="https://user-images.githubusercontent.com/82216057/219597497-3a5410d5-da77-4632-94ca-b11e9f8a7eb6.png" width="500" height="250">

After all of the planes had departed from the gateway, the ATC will then display statistics information.
