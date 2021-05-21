package messages.RepoMessages;

import java.io.Serializable;

/**
 *
 * @author Leandro e João
 */

public class RepoMessage implements Serializable {
    private int msg = -1;

    /**
     * END message.
     */
    public static final int END = 0;

    /**
     * SET_PASSENGER_STATE message.
     */
    public static final int SET_PASSENGER_STATE = 1;

    /**
     * SET_PILOT2_STATE message.
     */
    public static final int SET_PILOT2_STATE = 2;

    /**
     * SET_PILOT_STATE message.
     */
    public static final int SET_PILOT_STATE = 3;

    /**
     * SET_HOSTESS_STATE message.
     */
    public static final int SET_HOSTESS_STATE = 4;

    /**
     * IN_QUEUE_UPDATE message.
     */
    public static final int IN_QUEUE_UPDATE = 5;

    /**
     * AT_DESTINATION_UPDATE message.
     */
    public static final int AT_DESTINATION_UPDATE = 6;

    /**
     * IN_PLANE_UPDATE message.
     */
    public static final int IN_PLANE_UPDATE = 7;

    /**
     * REPORT_BOARDING message.
     */
    public static final int REPORT_BOARDING = 8;

    /**
     * REPORT_CHECK message.
     */
    public static final int REPORT_CHECK = 9;

    /**
     * REPORT_DEPARTED message.
     */
    public static final int REPORT_DEPARTED = 10;

    /**
     * REPORT_L_DEPARTED message.
     */
    public static final int REPORT_L_DEPARTED = 11;

    /**
     * REPORT_ARRIVED message.
     */
    public static final int REPORT_ARRIVED = 12;

    /**
     * REPORT_RETURNING message.
     */
    public static final int REPORT_RETURNING = 13;

    /**
     * SUCCESS message.
     */
    public static final int SUCCESS = 100;

    private int id;
    private boolean boolTemp;
    private String state;

    /**
     * Constructor of RepoMessage.
     * 
     * @param msg type of message received
     */
    public RepoMessage(int msg) {
        this.msg = msg;
    }

    /**
     * Constructor of RepoMessage.
     * 
     * @param msg type of message received
     * @param id  passenger id received
     */
    public RepoMessage(int msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    /**
     * Constructor of RepoMessage.
     * 
     * @param msg   type of message received
     * @param state String represent the State received
     */
    public RepoMessage(int msg, String state) {
        this.msg = msg;
        this.state = state;
    }

    /**
     * Constructor of RepoMessage.
     * 
     * @param msg   type of message received
     * @param int   passenger id received
     * @param state String represent the State received
     */
    public RepoMessage(int msg, int id, String state) {
        this.msg = msg;
        this.id = id;

    }

    /**
     * 
     * @return a int representing the type of message
     */
    public int getMessageType() {
        return this.msg;
    }

    /**
     * 
     * @return a int representing the Passenger id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 
     * @return a String representing the state
     */
    public String getState() {
        return this.state;
    }

    /**
     * 
     * @return a boolean representing the boolean response from Departure region
     */
    public boolean getBoolResponse() {
        return this.boolTemp;
    }

}
