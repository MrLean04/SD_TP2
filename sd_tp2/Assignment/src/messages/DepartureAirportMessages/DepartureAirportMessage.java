package messages.DepartureAirportMessages;

import java.io.Serializable;

/**
 *
 * @author Leandro e João
 */

public class DepartureAirportMessage implements Serializable{

    private int msg = -1;
    
    /**
     * END message.
     */
    public static final int END = 0;

    /**
     * PARK_AT_TRANSFER message.
     */
    public static final int PARK_AT_TRANSFER = 1;

    /**
     * READY_FOR_BOARDING message
     */
    public static final int READY_FOR_BOARDING = 2;

    /**
     * WAIT_FOR_BOARDING message
     */
    public static final int WAIT_FOR_BOARDING = 3;

    /**
     * LASTF message
     */
    public static final int LAST = 4;

    /**
     * PREPARE_PASS_BOARDING message
     */
    public static final int PREPARE_PASS_BOARDING = 5;

    /**
     * QUEUE_NOT_EMPTY message
     */
    //public static final int QUEUE_NOT_EMPTY = 6;

    /**
     * CHECK_AND_WAIT message
     */
    public static final int CHECK_AND_WAIT = 7;

    /**
     * PLANE_READY_TO_TAKE_OFF message
     */
    public static final int  PLANE_READY_TO_TAKE_OFF = 8;

    /**
     * EVERYONE_STOPS message
     */
    public static final int  EVERYONE_STOPS = 9;

    /**
     * WAIT_FOR_NEXT_FLIGHT_H message
     */
    public static final int  WAIT_FOR_NEXT_FLIGHT_H = 10;

    /**
     * HOSTESS_JOB_DONE message
     */
    public static final int  HOSTESS_JOB_DONE = 11;

    /**
     * TRAVEL_TO_AIRPORT message
     */
    public static final int  TRAVEL_TO_AIRPORT = 12;

    /**
     * WAIT_IN_QUEUE_FLIGHT message
     */
    public static final int  WAIT_IN_QUEUE_FLIGHT = 13;

    /**
     * WAIT_IN_QUEUE message
     */
    public static final int  WAIT_IN_QUEUE = 14;
    
    /**
     * SHOW_DOCUMENTS message
     */
    public static final int  SHOW_DOCUMENTS = 15;

    /**
     * WAIT_FOR_NEXT_FLIGHT_P message
     */
    public static final int  WAIT_FOR_NEXT_FLIGHT_P = 16;

    /**
     * GET_NUMBER_F message
     */
    public static final int  GET_NUMBER_F = 17;

    /**
     * SUCCESS message.
     */
    public static final int SUCCESS = 100;


    private int id;
    private boolean boolTemp;
    /**
     * Constructor of LoungeMessage.
     * @param msg type of message received 
     */
    public DepartureAirportMessage(int msg) {
        this.msg = msg;
    }

    public DepartureAirportMessage(int msg, int id) {
        this.msg = msg;
        this.id=id;
    }

    public DepartureAirportMessage(int msg, boolean boolTemp) {
        this.msg = msg;
        this.boolTemp= boolTemp;
    }

    public int getId(){
        return this.id;
    }

    public boolean getBoolResponse(){
        return this.boolTemp;
    }

    public int getMessageType(){
        return this.msg;
    }
    
}
