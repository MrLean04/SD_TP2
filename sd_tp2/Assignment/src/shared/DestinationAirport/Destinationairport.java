package shared.DestinationAirport;

import communication.ChannelClient;
import static communication.ChannelPorts.NAME_PORT_REPO;
import static communication.ChannelPorts.PORT_REPO;
import entities.Pilot.States.*;
import shared.Repo.Airlift;
import entities.Passenger.States.*;
import entities.Hostess.States.*;
import entities.Pilot.Interfaces.*;
import entities.Passenger.Interfaces.*;
import entities.Hostess.Interfaces.*;
import messages.RepoMessages.*;
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

    private ChannelClient cc_repository;

    private final Queue<Integer> inDestinationAirport = new LinkedList<>();
    private final Queue<Integer> inDebording = new LinkedList<>();
    private boolean l = false;
    private int count = 0;
    private Airlift airlift;

    /**
     * Destinationairport's Constructor
     * 
     * @param airlift repository
     */
    public Destinationairport() {//
        // this.airlift=airlift;
        this.cc_repository = new ChannelClient(NAME_PORT_REPO, PORT_REPO);
    }
    // Pilot

    /**
     * Pilot's method. When all passengers have been transported to the destination
     * airport the pilot will announce his final arrival.
     * 
     * @return a boolean representing if all passengers have been transported
     */
    @Override
    public synchronized boolean AnnounceArrival() {
        // TO-DO
        setPilotState2Update(PilotState.FLYING_BACK);
        reportArrived();
        if (!l) {
            System.out.println("contador:" + " " + count);
            while (count != 5) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            // System.out.println("aqui2");
            return false;
        }
        if (inDestinationAirport.size() == 21)
            return true;
        return false;
    }

    /**
     * Pilot's method. Pilot will wait for all the passengers to get on the
     * destination airport so he can fly back.
     * 
     * @return a boolean representing if it's time to fly back
     */
    @Override
    public synchronized boolean goBack() {
        // TO-DO
        if (inDebording.size() == 0) {
            reportreturning();
            return true;
        }
        return false;
    }

    /**
     * Pilot's method. Pilot checks if it's the last flight. return a boolean
     * representing if it the next flight is the last.
     */
    @Override
    public synchronized boolean lastF() {
        // TO-DO
        if (inDestinationAirport.size() == 20) {
            l = true;
            notifyAll();
            return true;
        }
        return false;
    }

    /**
     * Pilot's method. Pilot adds a passengers to the destination airport queues.
     * 
     * @param id id of the passenger
     */
    @Override
    public synchronized void atAirport(int id) {
        // TO-DO
        inDestinationAirport.add(id);
        setPassengerStatesUpdate(id, PassengerState.AT_DESTINATION);
        atDestinationUpdate(inDestinationAirport.size());
        inDebording.remove(id);

    }

    // Passenger

    /**
     * Passenger's method. Passenger removes himself from the inDeboarding queue.
     * 
     * @param id id of the passenger
     */
    @Override
    public synchronized void Deboarding(int id) {
        // TO-DO
        inDebording.add(id);
        count++;
        // System.out.println(" mais um " +count);
        notifyAll();
    }

    /**
     * Pilot's method. Used to put the variable with the value null.
     */
    public synchronized void zeroCount() {
        count = 0;
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPassengerStatesUpdate(int id, PassengerState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_PASSENGER_STATE, state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPilotState2Update(PilotState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_PILOT2_STATE, state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setPilotStateUpdate(int numberF, PilotState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_PILOT_STATE, numberF, state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void setHostessStateUpdate(HostessState state) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.SET_HOSTESS_STATE, state.toString()));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void inQueueUpdate(int size) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.IN_QUEUE_UPDATE, size));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void inPlaneUpdate(int size) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.IN_PLANE_UPDATE, size));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void atDestinationUpdate(int size) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.AT_DESTINATION_UPDATE, size));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportBoarding() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_BOARDING));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportCheck(int id) {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_CHECK, id));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportDeparted() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_DEPARTED));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportLDeparted() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_L_DEPARTED));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportArrived() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_ARRIVED));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Send Message to Repo Interface
     *
     */
    private synchronized void reportreturning() {
        RepoMessage response;
        startCommunication(cc_repository);
        cc_repository.writeObject(new RepoMessage(RepoMessage.REPORT_RETURNING));
        response = (RepoMessage) cc_repository.readObject();
        cc_repository.close();
    }

    /**
     * Departure's method. Start Communication
     *
     */
    private void startCommunication(ChannelClient cc) {
        while (!cc.open()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
    }
}
