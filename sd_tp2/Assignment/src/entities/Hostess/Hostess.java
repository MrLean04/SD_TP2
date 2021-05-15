package entities.Hostess;
import java.lang.ref.Cleaner;
import java.util.*;

import entities.Hostess.States.HostessState;
import communication.ChannelClient;
import static communication.ChannelPorts.*;
import messages.DepartureAirportMessages.*;
import messages.DestinationAirportMessages.*;
import messages.PlaneMessages.*;

/**
*
* @author Leandro e Jo�o
*/
public class Hostess extends Thread{

    private HostessState state;
	boolean happyhostess=false;
	private ChannelClient cc_Departure;
	private ChannelClient cc_Plane;
	private ChannelClient cc_Destination;
    

	/**
	 * Hostess's constructor.
	 *
	 */	 
	public Hostess() {
		this.cc_Departure = new ChannelClient(NAME_PORT_DEPARTURE, PORT_DEPARTURE);
		this.cc_Plane = new ChannelClient(NAME_PORT_PLANE, PORT_PLANE);
		this.cc_Destination = new ChannelClient(NAME_PORT_DESTINATION, PORT_DESTINATION);
		//
	}

	private void openChannel(ChannelClient cc, String name){
		while(!cc.open()){
			System.out.println(name + "not open.");
			try{
				Thread.sleep(1000);
			}catch(Exception ex){}
		}
	}

	private void preparePassBoarding(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Hostess" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.PREPARE_PASS_BOARDING));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	private boolean checkAndWait(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Hostess" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.CHECK_AND_WAIT));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
		return response.getBoolResponse();
	}

	private boolean planeReadyToTakeoff(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Hostess" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.PLANE_READY_TO_TAKE_OFF));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
		return response.getBoolResponse();
	}

	private boolean hostessJobDone(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Hostess" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.HOSTESS_JOB_DONE));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
		return response.getBoolResponse();
	}

	private void waitForNextFlightH(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Hostess" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.WAIT_FOR_NEXT_FLIGHT_H));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}



	@Override
	public void run() {
		this.setHostessState(HostessState.WAIT_FOR_PASSENGER);
		while (!happyhostess) {
			switch (this.state) {				
				case  WAIT_FOR_PASSENGER:
					System.out.println("WAIT_FOR_PASSENGER");					
					//if(queueNotEmpty()){
						preparePassBoarding();
						setHostessState(HostessState.CHECK_PASSENGER );
					//}
					break;
				
				case  CHECK_PASSENGER:
					System.out.println("CHECK_PASSENGER");
					if(!checkAndWait())
						setHostessState(HostessState.WAIT_FOR_PASSENGER );
					if (planeReadyToTakeoff()){					
						setHostessState(HostessState.READY_TO_FLY );
					}
					break;
					
				case   READY_TO_FLY:
					System.out.println("READY_TO_FLY");		
					
					if(!hostessJobDone()){
						setHostessState(HostessState.WAIT_FOR_NEXT_FLIGHT );
					} else happyhostess = true;
					
					break;
					
				case  WAIT_FOR_NEXT_FLIGHT:
					System.out.println("WAIT_FOR_NEXT_FLIGHT");
					waitForNextFlightH();
					setHostessState(HostessState.WAIT_FOR_PASSENGER);
					break;
			}
		}
	}


    /**
	 * Hostess's method. Change state of hostess and report status to log.
	 *
	 * @param state state of hostess
	 */
	private void setHostessState(HostessState state) {
		if (state == this.state) {
			return;
		}
		this.state = state;
	}

	/**
	 * Hostess's method. Retrieves hostess's state.
	 *
	 * @return hostess's state
	 */
	private HostessState getHostessState() {
		return this.state;
	}

}

