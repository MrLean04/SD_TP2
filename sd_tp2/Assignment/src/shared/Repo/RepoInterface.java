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
        
        switch(inMsg.getMessageType()) {

            case RepoMessage.SET_PASSENGER_STATE:
                airlift.setPassengerStatesUpdate(inMsg.getId(), inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.SET_PILOT2_STATE:
                airlift.setPilotState2Update(inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.SET_PILOT_STATE:
                airlift.setPilotStateUpdate(inMsg.getId(),inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.SET_HOSTESS_STATE:
                airlift.setHostessStateUpdate(inMsg.getState());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.IN_QUEUE_UPDATE:
                airlift.inQueueUpdate(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.IN_PLANE_UPDATE:
                airlift.inPlaneUpdate(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.AT_DESTINATION_UPDATE:
                airlift.atDestinationUpdate(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;
            
            case RepoMessage.REPORT_BOARDING:
                airlift.reportBoarding();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_CHECK:
                airlift.reportCheck(inMsg.getId());
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_DEPARTED:
                airlift.reportDeparted();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_L_DEPARTED:
                airlift.reportLDeparted();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_ARRIVED:
                airlift.reportArrived();
                outMsg = new RepoMessage(RepoMessage.SUCCESS);
                break;

            case RepoMessage.REPORT_RETURNING:
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
