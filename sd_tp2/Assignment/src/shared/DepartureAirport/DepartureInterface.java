package shared.DepartureAirport;
import messages.DepartureAirportMessages.DepartureAirportMessage;
import messages.DepartureAirportMessages.DepartureAirportMessageException;
/**
 * @author Leandro e João
 */
public class DepartureInterface {
    private Departureairport Departure;
    private boolean status = false;
        /**
     * DepartureInterface constructor
     * @param Departureairport Departureairport reference
     */
    public DepartureInterface(Departureairport Departure) {
        this.Departure= Departure;
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
     * @return DepartureAirportMessage reply
     * @throws DepartureAirportMessageException if the message contains an invalid request
     */
    public DepartureAirportMessage process(DepartureAirportMessage inMsg) throws DepartureAirportMessageException {       
        DepartureAirportMessage outMsg = null;
        Object response = null;
        /*
        switch(inMsg.getMessageType()) {
            //case LoungeMessage.ADD_TO_REPLACEMENT_QUEUE:
              //  lounge.addToReplacementQueue(inMsg.getCustId());
                //outMsg = new LoungeMessage(LoungeMessage.SUCCESS);
               // break;
            default:
                throw new DepartureAirportMessageException("Invalid message type.", inMsg);
        }*/
        return outMsg;
    }
}
