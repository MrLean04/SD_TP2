package entities.Passenger;

//import java.lang.ref.Cleaner;
import java.util.*;

import entities.Passenger.States.PassengerState;
import communication.ChannelClient;
import static communication.ChannelPorts.*;
import messages.DepartureAirportMessages.*;
import messages.DestinationAirportMessages.*;
import messages.PlaneMessages.*;

/**
 *
 * @author Leandro e João
 */
public class Passenger extends Thread {

	private PassengerState state;
	private final int id;
	private boolean happyPassenger = false;
	private ChannelClient cc_Departure;
	private ChannelClient cc_Plane;
	private ChannelClient cc_Destination;

	/**
	 * Passenger's constructor.
	 *
	 * @param id id of the passenger
	 */
	public Passenger(int id) {
		this.id = id;
		this.cc_Departure = new ChannelClient(NAME_PORT_DEPARTURE, PORT_DEPARTURE);
		this.cc_Plane = new ChannelClient(NAME_PORT_PLANE, PORT_PLANE);
		this.cc_Destination = new ChannelClient(NAME_PORT_DESTINATION, PORT_DESTINATION);
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
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private void travelToAirport() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Passenger" + this.id + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.TRAVEL_TO_AIRPORT, this.id));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private void waitInQueue() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Passenger" + this.id + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.WAIT_IN_QUEUE, this.id));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private boolean showDocuments() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Passenger" + this.id + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.SHOW_DOCUMENTS, this.id));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
		return response.getBoolResponse();
	}

	/**
	 * Passenger's method. Send Message to Departure Interface
	 *
	 */
	private boolean waitinQueueFlight() {
		DepartureAirportMessage response;
		openChannel(cc_Departure, "Passenger" + this.id + ": Departure Airport");
		cc_Departure.writeObject(new DepartureAirportMessage(DepartureAirportMessage.WAIT_IN_QUEUE_FLIGHT, this.id));
		response = (DepartureAirportMessage) cc_Departure.readObject();
		cc_Departure.close();
		return response.getBoolResponse();
	}

	/**
	 * Passenger's method. Send Message to Plane Interface
	 *
	 */
	private boolean BoardThePlane() {
		PlaneMessage response;
		openChannel(cc_Plane, "Passenger" + this.id + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.BOARD_THE_PLANE, this.id));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
		return response.getBoolResponse();
	}

	/**
	 * Passenger's method. Send Message to Destination Interface
	 *
	 */
	private void Deboarding() {
		DestinationAirportMessage response;
		openChannel(cc_Destination, "Passenger" + this.id + ": Destination Airport  Deboarding");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.DEBOARDING, this.id));
		response = (DestinationAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
	}

	/**
	 * Passenger's method. Send Message to Plane Interface
	 *
	 */
	private void WaitingForEndOfFlight() {
		PlaneMessage response;
		openChannel(cc_Plane, "Passenger" + this.id + ": Plane");
		cc_Plane.writeObject(new PlaneMessage(PlaneMessage.WAITING_FOR_END_OF_FLIGHT, this.id));
		response = (PlaneMessage) cc_Plane.readObject();
		cc_Plane.close();
	}

	/**
	 * Passenger's method. Send Message to Destination Interface
	 *
	 */
	private void atAirport() {
		DestinationAirportMessage response;
		openChannel(cc_Destination, "Passenger" + this.id + ": Destination Airport  at Airport");
		cc_Destination.writeObject(new DestinationAirportMessage(DestinationAirportMessage.AT_AIRPORT, this.id));
		response = (DestinationAirportMessage) cc_Destination.readObject();
		cc_Destination.close();
	}

	@Override
	public void run() {
		this.setPassengerState(PassengerState.GOING_TO_AIRPORT);
		while (!this.happyPassenger) {
			switch (this.state) {
				case GOING_TO_AIRPORT:
					System.out.println("GOING_TO_AIRPORT " + id);
					travelToAirport();
					setPassengerState(PassengerState.IN_QUEUE);
					break;

				case IN_QUEUE:
					System.out.println("IN_QUEUE " + id);
					waitInQueue();
					if (showDocuments()) {
						if (waitinQueueFlight()) {
							setPassengerState(PassengerState.IN_FLIGHT);
						}
					}

					break;

				case IN_FLIGHT:
					System.out.println("IN_FLIGHT " + id);
					if (BoardThePlane()) {
						WaitingForEndOfFlight();
						Deboarding();
						setPassengerState(PassengerState.AT_DESTINATION);

					}
					break;

				case AT_DESTINATION:
					System.out.println("AT_DESTINATION " + id);
					//setPassengerState(PassengerState.GOING_TO_AIRPORT);
					atAirport();
					// Deboarding();
					this.happyPassenger = true;
					break;
			}
		}
	}

	/**
	 * Passenger's method. Change state of passenger and report status to log.
	 *
	 * @param state state of passenger
	 */
	private void setPassengerState(PassengerState state) {
		if (state == this.state) {
			return;
		}
		this.state = state;
	}

	/**
	 * Passenger's method. Retrieves passenger's state.
	 *
	 * @return passenger's state
	 */
	private PassengerState getPassengersState() {
		return this.state;
	}

	/**
	 * Passenger's method. Retrieves passenger's id.
	 *
	 * @return passenger's id
	 */
	private int getPassengerId() {
		return this.id;
	}

}
