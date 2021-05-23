package entities.Pilot;

//import java.lang.ref.Cleaner;
import java.util.*;

import entities.Pilot.States.PilotState;
import communication.ChannelClient;
import static communication.ChannelPorts.*;
import messages.DepartureAirportMessages.*;
import messages.DestinationAirportMessages.*;
import messages.PlaneMessages.*;
import messages.RepoMessages.*;

/**
 *
 * @author Leandro e Jo�o
 */

public class Pilot extends Thread {

	private PilotState state;
	private boolean happypilot = false;
	private ChannelClient cc_Departure;
	private ChannelClient cc_Plane;
	private ChannelClient cc_Destination;
	private ChannelClient cc_Repo;

	/**
	 * Pilot's constructor.
	 *
	 */
	public Pilot() {
		this.cc_Departure = new ChannelClient(NAME_PORT_DEPARTURE, PORT_DEPARTURE);
		this.cc_Plane = new ChannelClient(NAME_PORT_PLANE, PORT_PLANE);
		this.cc_Destination = new ChannelClient(NAME_PORT_DESTINATION, PORT_DESTINATION);
		this.cc_Repo = new ChannelClient(NAME_PORT_REPO, PORT_REPO);
		//
	}

	/**
	 * Open Communication Channel method
	 *
	 * @param cc   Channel Client
	 * @param name Client Name
	 */
	private void openChannel(ChannelClient cc, String name) {
		while (!cc.open()) {
			System.out.println(name + " not open.");
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private void zeroCount() {
		DestinationAirportMessage response;
		openChannel(cc_Destination, "Pilot" + ": Destination Airport ZeroCount");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.ZERO_COUNT));
		response = (DestinationAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void parkAtTransfer() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.PARK_AT_TRANSFER));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void readyForBoarding() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.READY_FOR_BOARDING));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void WaitForBoarding() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.WAIT_FOR_BOARDING));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private boolean WaitForAllInBoard() {
		PlaneMessage response;
		openChannel(cc_Plane, "Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.WAIT_FOR_ALL_IN_BOARD));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
		return response.getBoolResponse();
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void upd() {
		PlaneMessage response;
		openChannel(cc_Plane, "Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.UPD));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void atDestinationPoint() {
		PlaneMessage response;
		openChannel(cc_Plane, "Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.AT_DESTINATION_POINT));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void everyoneStops() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.EVERYONE_STOPS));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean AnnounceArrival() {
		DestinationAirportMessage response;
		openChannel(cc_Destination, "Pilot" + ": Destination Airport Announce Arrival");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.ANNOUNCE_ARRIVAL));
		response = (DestinationAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
		return response.getBoolResponse();
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean goBack() {
		DestinationAirportMessage response;
		openChannel(cc_Destination, "Pilot" + ": Destination Airport go Back");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.GO_BACK));
		response = (DestinationAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
		return response.getBoolResponse();
	}

	/**
	 * Pilot's method. Send Message to Destination Interface
	 *
	 */
	private boolean lastF() {
		DestinationAirportMessage response;
		openChannel(cc_Destination, "Pilot" + ": Destination Airport lastF");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.LASTF));
		response = (DestinationAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
		return response.getBoolResponse();
	}

	/**
	 * Pilot's method. Send Message to Departure Interface
	 *
	 */
	private void last() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Pilot" + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.LAST));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	/**
	 * Pilot's method. Send Message to Plane Interface
	 *
	 */
	private void lFly() {
		PlaneMessage response;
		openChannel(cc_Plane, "Pilot" + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.LFLIGHT));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
	}

	/**
	 * Pilot's method. Send Message to End Program
	 *
	 */
	private void endProgram() {
		openChannel(cc_Departure, "Departure");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.END));
		cc_Departure.readObject();
		cc_Departure.close();

		openChannel(cc_Destination, "Destination");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.END));
		cc_Destination.readObject();
		cc_Destination.close();

		openChannel(cc_Plane, "Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.END));
		cc_Plane.readObject();
		cc_Plane.close();

		openChannel(cc_Repo, "Repository");
		cc_Repo.writeObject(new RepoMessage(RepoMessage.END));
		cc_Repo.readObject();
		cc_Repo.close();
	}

	@Override
	public void run() {
		this.setPilotState(PilotState.AT_TRANSFER_GATE);
		while (!happypilot) {
			switch (this.state) {
				case AT_TRANSFER_GATE:
					System.out.println("AT_TRANSFER_GATE ");
					zeroCount();
					parkAtTransfer();
					setPilotState(PilotState.READY_FOR_BOARDING);
					break;

				case READY_FOR_BOARDING:
					System.out.println(" READY_FOR_BOARDING ");
					readyForBoarding();
					WaitForBoarding();
					setPilotState(PilotState.WAIT_FOR_BOARDING);
					break;

				case WAIT_FOR_BOARDING:
					System.out.println(" WAIT_FOR_BOARDING ");
					if (WaitForAllInBoard()) {
						setPilotState(PilotState.FLYING_FORWARD);
					}
					break;

				case FLYING_FORWARD:
					System.out.println("FLYING_FORWARD ");
					upd();
					setPilotState(PilotState.DEBOARDING);
					break;

				case DEBOARDING:
					System.out.println("DEBOARDING ");
					atDestinationPoint();
					everyoneStops();
					setPilotState(PilotState.FLYING_BACK);
					break;

				case FLYING_BACK:
					System.out.println("FLYING BACK");
					
					if (AnnounceArrival()) {
						System.out.println("End program");
						endProgram();
						happypilot = true;
						// System.out.println("morri");
					}

					else {
						if (goBack()) {
							if (lastF()) {
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
