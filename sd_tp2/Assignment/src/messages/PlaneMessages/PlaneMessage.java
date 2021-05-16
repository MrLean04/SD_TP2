package messages.PlaneMessages;

import java.io.Serializable;

/**
 *
 * @author Leandro e João
 */

public class PlaneMessage implements Serializable{
    
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
     * Constructor of LoungeMessage.
     * @param msg type of message received 
     */
    public PlaneMessage(int msg) {
        this.msg = msg;
    }

    public PlaneMessage(int msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    public int getMessageType(){
        return this.msg;
    }

    public int getId(){
        return this.id;
    }

    public boolean getBoolResponse(){
        return this.boolTemp;
    }
    
}
