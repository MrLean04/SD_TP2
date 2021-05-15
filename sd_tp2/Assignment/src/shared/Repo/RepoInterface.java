package shared.Repo;
import messages.RepoMessages.*;
/**
 * @author Leandro e João
 */
public class RepoInterface {
    private Airlift airlift;
    private boolean status = false;
        /**
     * RepoInterface constructor
     * @param Airlift Airlift reference
     */
    public RepoInterface(Airlift airlift) {
        this.airlift= airlift;
    }
    /**
     * Checks if the server is running.
     * @return boolean to determine server status.
     */
    public boolean getStatus() {
        return this.status;
    }
    /**
     * Method that processes the message received and executes the operation
     * associated to that message.
     * 
     * @param inMsg message that contains a given request
     * @return RepoMessage reply
     * @throws  RepoMessageException if the message contains an invalid request
     */
    public RepoMessage process( RepoMessage inMsg) throws  RepoMessageException {       
        RepoMessage outMsg = null;
        Object response = null;
        /*
        switch(inMsg.getMessageType()) {
            //case DestinationMessage.ADD_TO_REPLACEMENT_QUEUE:
              //  lounge.addToReplacementQueue(inMsg.getCustId());
                //outMsg = new LoungeMessage(LoungeMessage.SUCCESS);
               // break;
            default:
                throw new  RepoMessageException("Invalid message type.", inMsg);
        }*/
        return outMsg;
    }
}
