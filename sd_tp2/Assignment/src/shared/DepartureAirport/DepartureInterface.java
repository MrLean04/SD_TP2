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
            System.out.println("Pre Park");
                Departure.parkAtTransfer();
                System.out.println("Park");
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.READY_FOR_BOARDING:
                System.out.println("Pre Ready for boarding");
                Departure.readyForBoarding();
                System.out.println("Ready for boarding");
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
                System.out.println("Pre Prepare Pass");
                Departure.preparePassBoarding();
                System.out.println("Prepare Pass");
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
                
            case DepartureAirportMessage.CHECK_AND_WAIT:
                System.out.println("Pre Check Wait");
                response=Departure.checkAndWait();
                System.out.println("Check Wait");
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
                System.out.println("Pre Travel to airport");
                Departure.travelToAirport();
                System.out.println("Travel to airport");
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.WAIT_IN_QUEUE_FLIGHT:
                response = Departure.waitinQueueFlight();
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS, (boolean) response);
                break;
            
            case DepartureAirportMessage.WAIT_IN_QUEUE:
                System.out.println("Wait in QUEUE " + inMsg.getId());
                Departure.waitInQueue(inMsg.getId());
                System.out.println(inMsg.getId());
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS);
                break;
            
            case DepartureAirportMessage.SHOW_DOCUMENTS:
                response = Departure.showDocuments(inMsg.getId());
                System.out.println(inMsg.getId());
                outMsg = new DepartureAirportMessage(DepartureAirportMessage.SUCCESS, (boolean) response);
                break;
            
            case DepartureAirportMessage.WAIT_FOR_NEXT_FLIGHT_P:
                Departure.waitForNextFlightP(inMsg.getId());
                System.out.println(inMsg.getId());
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
