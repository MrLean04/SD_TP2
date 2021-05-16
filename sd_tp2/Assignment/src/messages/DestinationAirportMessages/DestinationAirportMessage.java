package messages.DestinationAirportMessages;

import java.io.Serializable;

/**
 *
 * @author Leandro e João
 */

public class DestinationAirportMessage implements Serializable{

    private int msg = -1;
    
    /**
     * END message.
     */
    public static final int END = 0;

    /**
     * ANNOUNCE_ARRIVAL message.
     */
    public static final int ANNOUNCE_ARRIVAL = 1;

    /**
     * GO_BACK message.
     */
    public static final int GO_BACK = 2;

    /**
     * AT_AIRPORT message.
     */
    public static final int AT_AIRPORT = 3;

    /**
     * DEBOARDING message.
     */
    public static final int DEBOARDING = 4;

    /**
     * ZERO_COUNT message.
     */
    public static final int ZERO_COUNT = 5;

    /**
     * LASTF message.
     */
    public static final int LASTF = 6;
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
    public DestinationAirportMessage(int msg) {
        this.msg = msg;
    }
    public DestinationAirportMessage(int msg, int id) {
        this.msg = msg;
        this.id=id;
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
