package shared.Plane;
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
public class Plane implements PilotP, PassengerP, HostessP {

	boolean readyFly=false;
	boolean lastF=false;
	private final Queue<Integer> inPlane = new LinkedList<>();
	private final Queue<Integer> temp = new LinkedList<>();
	private Airlift airlift;
	/**
	 * Plane's Constructor
	 * @param airlift repository
	 */
	public Plane(){
        //
        //this.airlift=airlift;
    }

    //Pilot
	/**
	 *Pilot's method. Pilot waits for all passengers to be in the plane so he can be ready to fly.
	 *@param PilotState current state of the pilot
	 *@return a boolean representing if the wait is done
	 */
	@Override
    public synchronized boolean WaitForAllInBoard(PilotState PilotState) {
        airlift.PlaneUpdate(PilotState);
        while(inPlane.size() < 5) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        readyFly=true;
		notifyAll();
		return true;
    }
	/**
	  * Airlift's method. 
	  * @param PilotState current state of the pilot
	  */
	@Override
    public  synchronized void upd(PilotState PilotState) {
		airlift.PlaneUpdate(PilotState);
	}
	/**
	  *Pilot's method. Pilot sets some variables for his last flight.
	  */
    @Override
    public synchronized void lFly() {
        //TO-DO
    	inPlane.add(0); //empty seat
    	inPlane.add(0); //empty seat
    	inPlane.add(0); //empty seat
    	inPlane.add(0); //empty seat
        lastF=true;
        notifyAll();
    }
    
    //Pilot
    public synchronized void FlyToDestinationPoint() {
        //TO-DO
    }

     //Pilot
    public synchronized void FlyToDeparturePoint() {
        //TO-DO
    }
     //Passenger
    /**
     * Passenger's method. Passenger boards the plane (enters the inPlane queue).
     * @param id id of the passenger
     * @param state current state of the passenger
     * @return a boolean representing if the passenger is done boarding
     */
    public synchronized boolean BoardThePlane(int id,PassengerState state) {
        inPlane.add(id);
        airlift.PlaneUpdate(id, state);
        if(!lastF) {
            airlift.PlaneUpdate(inPlane);
        }
        else {
            temp.add(0);
            airlift.PlaneUpdate(temp);
        }
        System.out.println("inplane:"+ inPlane);
        notifyAll();
        while (!readyFly) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        return true;

    }
     //Passenger 
    public synchronized void WaitingForEndOfFlight() {
    //TO-DO
    try
		{ 
			wait ( (long) (1+ 200* Math.random() ));
		}
		catch (InterruptedException s) {}
		;
    }
    
    /**
     *Pilot's method. Pilot clears the queues in the plane for the next flight.
     *@param state current state of the pilot
     */
    @Override
    public synchronized void atDestinationPoint(PilotState state) {
        airlift.DestinationairportUpdate(state);
        //TO-DO
        inPlane.clear();
        airlift.PlaneUpdate(inPlane);
        //readyFly=false;
    }
}



