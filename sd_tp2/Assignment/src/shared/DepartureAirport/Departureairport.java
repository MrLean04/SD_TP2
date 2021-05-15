package shared.DepartureAirport;

import entities.Pilot.States.*;
import shared.Repo.*;
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
public class Departureairport implements PilotDA, PassengerDA, HostessDA {
	private final int max = 10;
	private final int min = 5;
	int nPassengerPlane = 0;
	private int nextPassenger = -1;
	private boolean pilotInpark = false;
	private boolean pilotready = false;
	private boolean inaArraivalAir = false;
	private boolean readyfly = false;
	private boolean readyForCheckDoc = false;
	private boolean PilotReadyFly = false;
	private boolean PassengerReadyBoarding = false;
	private final HashMap<Integer, Boolean> documentCheck = new HashMap<>();
	private final Queue<Integer> inQueue = new LinkedList<>();
	private final Queue<Integer> inPlane = new LinkedList<>();
	private final Queue<Integer> inqueueforboarding = new LinkedList<>();
	private final Queue<Integer> documentsQueue = new LinkedList<>();
	private int numberF=0;
	private boolean lastF = false;
	private Airlift airlift;
	
	/**
	 *Departureairport's Constructor
	 *@param airlift repository
	 */
	public Departureairport() {
		//
		//this.airlift=airlift;
	}

	// Pilot
	/**
	 *Pilot's method. Pilot arrives at transfer gate
	 *@param PilotState current state of the pilot
	 */
	@Override
	public synchronized void parkAtTransfer( PilotState PilotState) {
		pilotInpark = true;
		numberF++;
		airlift.DepartureairportUpdate(numberF,PilotState);
		notifyAll();
	}

	/**
	 *Pilot's method. Pilot is ready for boarding
	 *@param PilotState current state of the pilot
	 */
	@Override
	public synchronized void readyForBoarding( PilotState PilotState) {
		pilotready = true;
		airlift.DepartureairportUpdate(numberF,PilotState);
		//airlift.reportBoarding();
		//pilotInpark = false;
	}

	/**
	 *Pilot's method. Pilot waits for all passengers to board.
	 *@param PilotState current state of the pilot
	 */
	@Override
	public synchronized void WaitForBoarding( PilotState PilotState) {
		airlift.DepartureairportUpdate(numberF,PilotState);
		while (!PilotReadyFly) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		
		
		//pilotInpark=false;
	}
	/**
	 * Pilot's method. Pilot sets the last flight flag to true.  
	 */
	@Override
	public synchronized void last() {
		lastF = true;
	}

	// Hostess
	
	/**
		 * Hostess's method. Hostess checks if pilot is at the transfer gate 
		 * and sets the checking documents flag to true.
		 */
	@Override
	public synchronized void preparePassBoarding() {
		if (pilotInpark) {
			readyForCheckDoc = true;
		}

	}

	/**
	 * Hostess's method. Retrieves if the airport queue is not empty.
	 * 
	 *@return a boolean representing if the queue is not empty
	 */
	@Override
	public synchronized boolean queueNotEmpty( HostessState state) {
		airlift.DepartureairportUpdate(state);
		if (inQueue.size() != 0)
			return true;
		return false;
	}

	/**
	 *Hostess's method. Hostess checks the documents of a passenger.
	 *@param state current state of the hostess
	 *@return a boolean representing if a passenger was checked
	 */
	@Override
	public synchronized boolean checkAndWait(HostessState state) {
		// TO-DO

		while (nextPassenger != -1) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		if (inQueue.size() == 0 || !readyForCheckDoc) {
			readyForCheckDoc = false;
			return false;			
		}
		System.out.println(inQueue.toString());
		nextPassenger = inQueue.poll();
		//airlift.reportCheck(nextPassenger);
		airlift.DepartureairportUpdate(inQueue);
		// System.out.println(nextPassenger + " entrei na queue");
		documentCheck.put(nextPassenger, true);
		inPlane.add(nextPassenger);
		airlift.DepartureairportUpdate(state);
		nPassengerPlane++;
		notifyAll();
		System.out.println(documentCheck);
		return true;

	}

	/**
	 * Hostess's method. Hostess checks if the plane is ready to fly and if so updates some variables.
	 * @return a boolean representing if the plane is ready to fly
	 */
	@Override
	public synchronized boolean planeReadyToTakeoff() {
		if ((inPlane.size() >= 5 && inPlane.size() <= 10 ) || lastF  ){
			readyForCheckDoc = false;
			PilotReadyFly = true;
			PassengerReadyBoarding = true;
			pilotInpark = false;
			inPlane.clear();
			System.out.println(numberF);
			/**if(!lastF) {
				airlift.reportDeparted();
			}
			else {
				airlift.reportLDeparted();
			}*/
			
			notifyAll();
			return true;
		}
		return false;
	}

	/**
	 *Hostess's method. Hostess stops passengers from boarding the plane
	 * 
	 */
	@Override
	public synchronized void everyoneStops() {
		PilotReadyFly = false;
		PassengerReadyBoarding = false;
	}
	
	/**
	 *Hostess's method. Hostess waits for the plane to come back from its flight.
	 *@param state current state of the hostess 
	 */
	@Override
	public synchronized void waitForNextFlightH(HostessState state) {
		// TO-DO
		airlift.DepartureairportUpdate(state);
		notifyAll();
		while (!pilotInpark) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		
	}
	

	/**
	 *Hostess's method. Hostess checks if there's more passengers to check.
	 *@param state current state of the hostess
	 *@return a boolean representing if the hostess job is done
	 */
	@Override
	public synchronized boolean hostessJobDone( HostessState state) {
		airlift.DepartureairportUpdate(state);
		if (inQueue.size() == 0) {
			return true;
			
		}
		return false;
	}

	// Passenger
	/**
	 *
	 * 
	 */
	@Override
	public synchronized void travelToAirport() {
		// TO-DO
		try
		{ 
			wait ( (long) (1+ 200* Math.random() ));
		}
		catch (InterruptedException s) {}
		;
	}

	/**
	 * Passenger's method. Passenger waits until it's his time to board the plane.
	 * @return a boolean representing if the passenger can board the plane
	 */
	@Override
	public synchronized boolean waitinQueueFlight() {
		// TO-DO

		while (!PassengerReadyBoarding) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}

		return true;
	}

	/**
	 * Passenger's method. Passenger enters the airport queue and waits until it's his turn.
	 * @param id  id of the passenger 
	 * @param state current state of the passenger
	 */
	@Override
	public synchronized void waitInQueue(int id, PassengerState state) {
		inQueue.add(id);
		airlift.DepartureairportUpdate(id,state);
		airlift.DepartureairportUpdate(inQueue);
		//documentCheck.put(id, false);
		//System.out.println(id+ "");
		//notifyAll();
		while (nextPassenger != id ) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		nextPassenger = -1;
		notifyAll();
	}

	/**
	 * Passenger's method. Checks if the passenger has been moved to the documentsQueue.
	 * @param id id of the passenger 
	 * @return a boolean representing if the passenger exists in the documentsQueue
	 */
	@Override
	public synchronized boolean showDocuments(int id) {
		// TO-DO
		return documentCheck.get(id)!= null;
	}
	/**
	 * Passenger's method. Unused
	 * @param id id of the passenger 
	 * 
	 */
	@Override
	public synchronized void waitForNextFlightP(int id) {
		// TO-DO
		if (inQueue.size() != 0) {
			notifyAll();
			while (!pilotInpark && !showDocuments(id)) {
				try {
					wait();
				} catch (InterruptedException e) {

				}
			}
		}
	}
	/**
	 * Number of the flight.
	 * @return a integer representing the number of the current flight
	 */
	public synchronized int getNumberF() {
		return  numberF;
	}
}
