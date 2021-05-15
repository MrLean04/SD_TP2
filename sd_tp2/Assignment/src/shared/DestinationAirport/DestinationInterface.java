
package shared.DestinationAirport;
import messages.DestinationAirportMessages.DestinationAirportMessage;
import messages.DestinationAirportMessages.DestinationAirportMessageException;

/**
 * @author Leandro e João
 */
public class DestinationInterface {
    private Destinationairport destination;
    private boolean status = false;
        /**
     * DestinationInterface constructor
     * @param Destinationairport Destinationairport reference
     */
    public DestinationInterface(Destinationairport destination) {
        this.destination= destination;
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
     * @return DestinationAirportMessage reply
     * @throws DestinationAirportMessageException if the message contains an invalid request
     */
    public DestinationAirportMessage process(DestinationAirportMessage inMsg) throws DestinationAirportMessageException {       
        DestinationAirportMessage outMsg = null;
        Object response = null;
        /*
        switch(inMsg.getMessageType()) {
            //case DestinationMessage.ADD_TO_REPLACEMENT_QUEUE:
              //  lounge.addToReplacementQueue(inMsg.getCustId());
                //outMsg = new LoungeMessage(LoungeMessage.SUCCESS);
               // break;
            default:
                throw new DestinationAirportMessageException("Invalid message type.", inMsg);
        }*/
        return outMsg;
    }
}