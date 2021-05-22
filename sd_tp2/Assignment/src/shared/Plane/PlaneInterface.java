package shared.Plane;
import messages.PlaneMessages.PlaneMessage;
import messages.PlaneMessages.PlaneMessageException;
/**
 * @author Leandro e João
 */
public class PlaneInterface {
    private Plane plane;
    private boolean status = true;
        /**
     * PlaneInterface constructor
     * @param plane Plane reference
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
        
        switch(inMsg.getMessageType()) {
            
            case PlaneMessage.WAIT_FOR_ALL_IN_BOARD:
                response = plane.WaitForAllInBoard();
                outMsg = new PlaneMessage(PlaneMessage.SUCCESS, (boolean) response);
                break;

            case PlaneMessage.UPD:
                plane.upd();
                outMsg = new PlaneMessage(PlaneMessage.SUCCESS);
                break;

            case PlaneMessage.LFLIGHT:
                plane.lFly();
                outMsg = new PlaneMessage(PlaneMessage.SUCCESS);
                break;

            case PlaneMessage.BOARD_THE_PLANE:
                response = plane.BoardThePlane(inMsg.getId());
                outMsg = new PlaneMessage(PlaneMessage.SUCCESS, (boolean) response);
                break;

            case PlaneMessage.WAITING_FOR_END_OF_FLIGHT:
                plane.WaitingForEndOfFlight();
                outMsg = new PlaneMessage(PlaneMessage.SUCCESS);
                break;

            case PlaneMessage.AT_DESTINATION_POINT:
                plane.atDestinationPoint();
                outMsg = new PlaneMessage(PlaneMessage.SUCCESS);
                break;
                
            case PlaneMessage.END:
                System.out.println("Closing Plane server!");
                this.status = false;
                outMsg = new PlaneMessage(PlaneMessage.SUCCESS);
                break;  
                
            default:
                throw new  PlaneMessageException("Invalid message type.", inMsg);
        }
        return outMsg;
    }
    
}
