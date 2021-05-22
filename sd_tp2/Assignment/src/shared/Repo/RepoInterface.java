package shared.Repo;
import messages.RepoMessages.*;
/**
 * @author Leandro e João
 */
public class RepoInterface {
    private Airlift airlift;
    private boolean status = true;
        /**
     * RepoInterface constructor
     * @param airlift Airlift reference
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
        
        switch(inMsg.getMessageType()) {

            case RepoMessage.SET_PASSENGER_STATE:
                System.out.println("Set Passenger");
                System.out.println(inMsg.getNumberF() + " "+ inMsg.getState());
                airlift.setPassengerStatesUpdate(inMsg.getId(), inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.SET_PILOT2_STATE:
            System.out.println("Set Pilot 2");
                airlift.setPilotState2Update(inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.SET_PILOT_STATE:
                System.out.println("Set Pilot");
                airlift.setPilotStateUpdate(inMsg.getNumberF(),inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.SET_HOSTESS_STATE:
            System.out.println("Set Hostess");
                airlift.setHostessStateUpdate(inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.IN_QUEUE_UPDATE:
                System.out.println("In Queue Update");
                airlift.inQueueUpdate(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.IN_PLANE_UPDATE:
                System.out.println("In Plane Update");
                airlift.inPlaneUpdate(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.AT_DESTINATION_UPDATE:
                System.out.println("At Destination Update");
                airlift.atDestinationUpdate(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;
            
            case RepoMessage.REPORT_BOARDING:
                System.out.println("Report Boarding");
                airlift.reportBoarding();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_CHECK:
                System.out.println("Report Check");
                airlift.reportCheck(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_DEPARTED:
                System.out.println("Report Departed");
                airlift.reportDeparted();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_L_DEPARTED:
                System.out.println("Report L Departed");
                airlift.reportLDeparted();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_ARRIVED:
                System.out.println("Report Arrived");
                airlift.reportArrived();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_RETURNING:
                System.out.println("Report returning");
                airlift.reportreturning();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.END:
                System.out.println("Closing Repo server!");
                this.status = false;
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;
                
            default:
                throw new  RepoMessageException("Invalid message type.", inMsg);
        }
        return outMsg;
    }
}
