package messages.PlaneMessages;

import java.io.Serializable;

/**
 *
 * @author Leandro e João
 */

public class PlaneMessage implements Serializable {

    private int msg = -1;

    /**
     * END message.
     */
    public static final int END = 0;

    /**
     * WAIR_FOR_ALL_IN_BOARD message.
     */
    public static final int WAIT_FOR_ALL_IN_BOARD = 1;

    /**
     * UPD message.
     */
    public static final int UPD = 2;

    /**
     * LFLIGHT message.
     */
    public static final int LFLIGHT = 3;

    /**
     * BOARD_THE_PLANE message.
     */
    public static final int BOARD_THE_PLANE = 4;

    /**
     * WAITING_FOR_END_OF_FLIGHT message.
     */
    public static final int WAITING_FOR_END_OF_FLIGHT = 5;

    /**
     * WAITING_FOR_END_OF_FLIGHT message.
     */
    public static final int AT_DESTINATION_POINT = 6;

    /**
     * SUCCESS message.
     */
    public static final int SUCCESS = 100;

    private int id;
    private boolean boolTemp;

    /**
     * Constructor of PlaneMessage.
     * 
     * @param msg type of message received
     */
    public PlaneMessage(int msg) {
        this.msg = msg;
    }

    /**
     * Constructor of PlaneMessage.
     * 
     * @param msg type of message received
     * @param id  passenger id received
     */
    public PlaneMessage(int msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    /**
     * Constructor of PlaneMessage.
     * 
     * @param msg      type of message received
     * @param boolTemp Boolean response received
     */
    public PlaneMessage(int msg, boolean boolTemp) {
        this.msg = msg;
        this.boolTemp = boolTemp;
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
     * @return a boolean representing the boolean response from Departure region
     */
    public boolean getBoolResponse() {
        return this.boolTemp;
    }

}
