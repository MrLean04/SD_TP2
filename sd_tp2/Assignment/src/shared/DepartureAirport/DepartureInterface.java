package shared.DepartureAirport;
import messages.DepartureAirportMessages.DepartureAirportMessage;
import messages.DepartureAirportMessages.DepartureAirportMessageException;
/**
 * @author Leandro e João
 */
public class DepartureInterface {
    private Departureairport Departure;
    private boolean status = true;
        /**
     * DepartureInterface constructor
     * @param Departure Departureairport reference
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
        
        switch(inMsg.getMessageType()) {
            case DepartureAirportMessage.PARK_AT_TRANSFER:
                Departure.parkAtTransfer();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.READY_FOR_BOARDING:
                Departure.readyForBoarding();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.WAIT_FOR_BOARDING:
                Departure.WaitForBoarding();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.LAST:
                Departure.last();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;

            case DepartureAirportMessage.PREPARE_PASS_BOARDING:
                Departure.preparePassBoarding();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
                
            case DepartureAirportMessage.CHECK_AND_WAIT:
                response=Departure.checkAndWait();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS,(boolean) response);
                break;
            
            case DepartureAirportMessage.PLANE_READY_TO_TAKE_OFF:
                response = Departure.planeReadyToTakeoff();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS, (boolean) response);
                break;
                
            case DepartureAirportMessage.EVERYONE_STOPS:
                Departure.everyoneStops();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.WAIT_FOR_NEXT_FLIGHT_H:
                Departure.waitForNextFlightH();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
                
            case DepartureAirportMessage.HOSTESS_JOB_DONE:
                response = Departure.hostessJobDone();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS, (boolean) response);
                break;
            
            case DepartureAirportMessage.TRAVEL_TO_AIRPORT:
                Departure.travelToAirport();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.WAIT_IN_QUEUE_FLIGHT:
                response = Departure.waitinQueueFlight();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS, (boolean) response);
                break;
            
            case DepartureAirportMessage.WAIT_IN_QUEUE:
                Departure.waitInQueue(inMsg.getId());
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.SHOW_DOCUMENTS:
                response = Departure.showDocuments(inMsg.getId());
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS, (boolean) response);
                break;
            
            case DepartureAirportMessage.WAIT_FOR_NEXT_FLIGHT_P:
                Departure.waitForNextFlightP(inMsg.getId());
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.GET_NUMBER_F:
                response = Departure.getNumberF();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS, (int) response);
                break;
                
            case DepartureAirportMessage.END:
                System.out.println("Closing Departure server!");
                this.status = false;
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
                
            default:
                throw new DepartureAirportMessageException("Invalid message type.", inMsg);
        }
        return outMsg;
    }
}
