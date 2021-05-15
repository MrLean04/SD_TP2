package messages.RepoMessages;
import java.io.Serializable;

/**
 *
 * @author Leandro e João
 */

public class RepoMessage implements Serializable{      
    private int msg = -1;
    
    /**
     * END message.
     */
    public static final int END = 0;

    /**
     * SUCCESS message.
     */
    public static final int SUCCESS = 100;

    /**
     * Constructor of LoungeMessage.
     * @param msg type of message received 
     */
    public RepoMessage(int msg) {
        this.msg = msg;
    }
    public int getMessageType(){
        return this.msg;
    }
    
}
