package shared.DestinationAirport;

import entities.Pilot.States.*;
import shared.Repo.Airlift;
import entities.Passenger.States.*;
import entities.Hostess.States.*;
import entities.Pilot.Interfaces.*;
import entities.Passenger.Interfaces.*;
import entities.Hostess.Interfaces.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Leandro and Jo�o
 */
public class Destinationairport implements PilotDSA, PassengerDSA, HostessDSA {

	private final Queue<Integer> inDestinationAirport = new LinkedList<>();
    private final Queue<Integer> inDebording = new LinkedList<>();
    private boolean l=false;
    private int count=0;
    private Airlift airlift;
    /**
     *Destinationairport's Constructor
     *@param airlift repository
     */
    public Destinationairport(){// 
        //this.airlift=airlift;
    }
     //Pilot
    /**
     *Pilot's method. When all passengers have been transported to the destination airport 
     *the pilot will announce his final arrival.
     *@param state current state of the pilot
     *@return a boolean representing if all passengers have been transported
     */
    @Override
    public synchronized boolean AnnounceArrival(PilotState state) {
        //TO-DO
        airlift.DestinationairportUpdate(state);
        //airlift.reportArrived();
        if(!l) {   
            System.out.println("contador:" + " " +count);
            while (count!=5) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }    		
    		//System.out.println("aqui2");
        return false;
        }  
		if(inDestinationAirport.size() == 21) return true;
        return false;     
    }
    /**
     *Pilot's method. Pilot will wait for all the passengers to get on the destination airport so he can fly back. 
     *@return a boolean representing if it's time to fly back
     */
    @Override
    public synchronized boolean goBack() {
        //TO-DO
        if( inDebording.size() == 0) {
            //airlift.reportreturning();
            return true;
        }
        return false;
    }
    /**
     *Pilot's method. Pilot checks if it's the last flight.
     *return a boolean representing if it the next flight is the last. 
     */
    @Override
    public synchronized boolean lastF() {
        //TO-DO
        if(inDestinationAirport.size() == 20) {
            l=true;
            notifyAll();
            return true;
        }
        return false;
    }
    /**
     *Pilot's method. Pilot adds a passengers to the destination airport queues.
     *@param id id of the passenger
     *@param state current state of the passenger
     */
    @Override
    public synchronized void atAirport(int id,PassengerState state) {
        //TO-DO
        inDestinationAirport.add(id);
        airlift.DestinationairportUpdate(id, state);
        airlift.DestinationairportUpdate(inDestinationAirport);
        inDebording.remove(id);
        
    }
    
    //Passenger
    /**Passenger's method. Passenger removes himself from the inDeboarding queue.
     * @param id id of the passenger
     */
    @Override
    public synchronized void Deboarding(int id) {
        //TO-DO
        inDebording.add(id);
        count++;
       // System.out.println(" mais um " +count);
        notifyAll();
    }
    
    public synchronized void zeroCount() {
        count=0;
    }
}
    


