package messages.DestinationAirportMessages;

public class DestinationAirportMessageException extends RuntimeException {
    private DestinationAirportMessage msg;

    /**
     * Instantiation of Destination message exception.
     * @param error text with error
     * @param msg message with exception
     */ 
    public DestinationAirportMessageException(String error, DestinationAirportMessage msg) {
        super(error);
        this.msg = msg;
    }

    /**
     * Obtaining the Destination message with error.
     * @return message
     */
    public DestinationAirportMessage getMsg() {
        return msg;
    }
    
}
