package entities.Pilot;
import java.lang.ref.Cleaner;
import java.util.*;

import entities.Pilot.States.PilotState;
import communication.ChannelClient;
import static communication.ChannelPorts.*;
import messages.DepartureAirportMessages.*;
import messages.DestinationAirportMessages.*;
import messages.PlaneMessages.*;
/**
*
* @author Leandro e Jo�o
*/

public class Pilot extends Thread{

    private PilotState state;	
	private boolean happypilot=false;
	private ChannelClient cc_Departure;
	private ChannelClient cc_Plane;
	private ChannelClient cc_Destination;
    

	/**
	 * Pilot's constructor.
	 *
	 */  
	public Pilot() {
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

	private void zeroCount(){
		DestinationAirportMessage response;
		openChannel(cc_Destination,"Pilot" + ": Destination Airport");
		cc_Destination.writeObject(new DepartureAirportMessage(DestinationAirportMessage.ZERO_COUNT));
		response = (DestinaitonAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
	}

	private void  parkAtTransfer(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.PARK_AT_TRANSFER));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	private void  readyForBoarding(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.READY_FOR_BOARDING));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	private void  WaitForBoarding(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.WAIT_FOR_BOARDING));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	private boolean WaitForAllInBoard(){
		PlaneMessage response;
		openChannel(cc_Plane,"Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.WAIR_FOR_ALL_IN_BOARD));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
		return response.getBoolResponse(); 
	}

	private void upd(){
		PlaneMessage response;
		openChannel(cc_Plane,"Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.UPD));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
	}

	private void atDestinationPoint(){
		PlaneMessage response;
		openChannel(cc_Plane,"Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.AT_DESTINATION_POINT));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
	}

	private void everyoneStops(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.EVERYONE_STOPS));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	private boolean AnnounceArrival(){
		DestinationAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Destination Airport");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.ANNOUNCE_ARRIVAL));
		response = (DepartureAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
		return response.getBoolResponse();
	}

	private boolean goBack(){
		DestinationAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Destination Airport");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.GO_BACK));
		response = (DepartureAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
		return response.getBoolResponse();
	}

	private boolean lastF(){
		DestinationAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Destination Airport");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.LASTF));
		response = (DepartureAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
		return response.getBoolResponse();
	}

	private void last(){
		DepartureAirportMessage response;
		openChannel(cc_Departure,"Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.LAST));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	private void lFly(){
		PlaneMessage response;
		openChannel(cc_Plane,"Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.LFLIGHT));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
	}


	




	@Override
	public void run() {
		this.setPilotState(PilotState.AT_TRANSFER_GATE);
		while (!happypilot) {
			switch (this.state) {
				case  AT_TRANSFER_GATE:
					System.out.println("AT_TRANSFER_GATE ");
					zeroCount();
					parkAtTransfer();	
					setPilotState(PilotState.READY_FOR_BOARDING);
					break;

				case  READY_FOR_BOARDING:
					System.out.println(" READY_FOR_BOARDING ");	
					readyForBoarding();
					WaitForBoarding();	
					setPilotState(PilotState.WAIT_FOR_BOARDING );
					break;
				
				case  WAIT_FOR_BOARDING:
					System.out.println(" WAIT_FOR_BOARDING ");
					if(WaitForAllInBoard(state)) {
						setPilotState(PilotState.FLYING_FORWARD );
					}
					break;
					
				case  FLYING_FORWARD:
					System.out.println("FLYING_FORWARD ");
					upd();
					setPilotState(PilotState.DEBOARDING );
					break;
				
				case  DEBOARDING:
					System.out.println("DEBOARDING ");
					atDestinationPoint();
					everyoneStops();
					setPilotState(PilotState.FLYING_BACK );
					break;
				
				case FLYING_BACK:
					System.out.println("FLYING BACK");	
					if(AnnounceArrival(state)){
						happypilot = true;
						//System.out.println("morri");
					}
					
					else{
						if(goBack()) {
							if(lastF()) {
								last();
								System.out.println("last f");
								lFly();
							}
						
							
						setPilotState(PilotState.AT_TRANSFER_GATE);
						}
					}
					
					break;
			}
		}
	}


    /**
	 * Pilot's method. Change state of pilot and report status to log.
	 *
	 * @param state state of Pilot
	 */
	private void setPilotState(PilotState state) {
		if (state == this.state) {
			return;
		}
		this.state = state;
	}

	/**
	 * Pilot's method. Retrieves pilot's state.
	 *
	 * @return pilot's state
	 */
	private PilotState getPilotState() {
		return this.state;
	}

}
