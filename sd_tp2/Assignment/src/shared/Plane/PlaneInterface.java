package shared.Plane;
import messages.PlaneMessages.PlaneMessage;
import messages.PlaneMessages.PlaneMessageException;
/**
 * @author Leandro e João
 */
public class PlaneInterface {
    private Plane plane;
    private boolean status = false;
        /**
     * PlaneInterface constructor
     * @param Plane Plane reference
     */
    public PlaneInterface(Plane plane) {
        this.plane= plane;
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
     * @return PlaneMessage reply
     * @throws  PlaneMessageException if the message contains an invalid request
     */
    public PlaneMessage process( PlaneMessage inMsg) throws  PlaneMessageException {       
        PlaneMessage outMsg = null;
        Object response = null;
        /*
        switch(inMsg.getMessageType()) {
            //case DestinationMessage.ADD_TO_REPLACEMENT_QUEUE:
              //  lounge.addToReplacementQueue(inMsg.getCustId());
                //outMsg = new LoungeMessage(LoungeMessage.SUCCESS);
               // break;
            default:
                throw new  PlaneMessageException("Invalid message type.", inMsg);
        }*/
        return outMsg;
    }
    
}
