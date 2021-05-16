package shared.Plane;
import communication.ChannelClient;
import static communication.ChannelPorts.NAME_GENERAL_REPOSITORY;
import static communication.ChannelPorts.PORT_GENERAL_REPOSITORY;
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

    private ChannelClient cc_repository;
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
        this.cc_repository = new ChannelClient(NAME_GENERAL_REPOSITORY, PORT_GENERAL_REPOSITORY);
    }

    //Pilot
	/**
	 *Pilot's method. Pilot waits for all passengers to be in the plane so he can be ready to fly.
	 *@param PilotState current state of the pilot
	 *@return a boolean representing if the wait is done
	 */
	@Override
    public synchronized boolean WaitForAllInBoard() {
        setPilotState2Update(PilotState.WAIT_FOR_BOARDING);
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
    public  synchronized void upd() {
		setPilotState2Update(PilotState.FLYING_FORWARD);
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
    public synchronized boolean BoardThePlane(int id) {
        inPlane.add(id);
        setPassengerStatesUpdate(id, PassengerState.IN_FLIGHT);
        if(!lastF) {
            inPlaneUpdate(inPlane.size());
        }
        else {
            temp.add(0);
            inPlaneUpdate(temp.size());
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
    public synchronized void atDestinationPoint() {
        setPilotState2Update(PilotState.DEBOARDING);
        //TO-DO
        inPlane.clear();
        inPlaneUpdate(inPlane.size());
        //readyFly=false;
    }

    private synchronized void setPassengerStatesUpdate(int id, PassengerState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_PASSENGER_STATE, id ,state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

	private synchronized void setPilotState2Update(PilotState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_PILOT2_STATE,state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }
    
    private synchronized void setPilotStateUpdate( int numberF, PilotState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_PILOT_STATE, numberF, state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }
    
    private synchronized void setHostessStateUpdate(HostessState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_HOSTESS_STATE, state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }
    
    private synchronized void inQueueUpdate( int size) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.IN_QUEUE_UPDATE, size));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

	private synchronized void inPlaneUpdate( int size) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.IN_PLANE_UPDATE, size));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

	private synchronized void atDestinationUpdate( int size) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.AT_DESTINATION_UPDATE, size));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }
    private synchronized void reportBoarding() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_BOARDING));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

	private synchronized void reportCheck(int id) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_CHECK), id);
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

	private synchronized void reportDeparted() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_DEPARTED));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

	private synchronized void reportLDeparted() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_L_DEPARTED));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

	private synchronized void reportArrived() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_ARRIVED));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

	private synchronized void reportreturning() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_RETURNING));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close(); 
    }

    
    private void startCommunication(ChannelClient cc) {
        while(!cc.open()) {
            try {
                Thread.sleep(1000);
            }
            catch(Exception e) {
                
            }
        }
    }
}



