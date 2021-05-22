
package shared.DestinationAirport;
import messages.DestinationAirportMessages.DestinationAirportMessage;
import messages.DestinationAirportMessages.DestinationAirportMessageException;

/**
 * @author Leandro e João
 */
public class DestinationInterface {
    private Destinationairport destination;
    private boolean status = true;
        /**
     * DestinationInterface constructor
     * @param destination Destinationairport reference
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
        
        switch(inMsg.getMessageType()) {
            
            case DestinationAirportMessage.ANNOUNCE_ARRIVAL:
                response = destination.AnnounceArrival();
                outMsg = new DestinationAirportMessage(DestinationAirportMessage.SUCCESS, (boolean) response);
                break;

            case DestinationAirportMessage.GO_BACK:
                response = destination.goBack();
                outMsg = new DestinationAirportMessage(DestinationAirportMessage.SUCCESS, (boolean) response);
                break;

            case DestinationAirportMessage.AT_AIRPORT:
                destination.atAirport(inMsg.getId());
                outMsg = new DestinationAirportMessage(DestinationAirportMessage.SUCCESS);
                break;

            case DestinationAirportMessage.DEBOARDING:
                destination.Deboarding(inMsg.getId());
                outMsg = new DestinationAirportMessage(DestinationAirportMessage.SUCCESS);
                break;

            case DestinationAirportMessage.ZERO_COUNT:
                destination.zeroCount();
                outMsg = new DestinationAirportMessage(DestinationAirportMessage.SUCCESS);
                break;
            
            case DestinationAirportMessage.LASTF:
                response = destination.lastF();
                outMsg = new DestinationAirportMessage(DestinationAirportMessage.SUCCESS, (boolean) response);
                break;
                
            case DestinationAirportMessage.END:
                System.out.println("Closing Destination server!");
                this.status = false;
                outMsg = new DestinationAirportMessage(DestinationAirportMessage.SUCCESS);
                break;
                
                

            default:
                throw new DestinationAirportMessageException("Invalid message type.", inMsg);
        }
        return outMsg;
    }
}