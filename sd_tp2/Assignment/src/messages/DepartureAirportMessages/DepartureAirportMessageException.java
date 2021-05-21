package messages.DepartureAirportMessages;

public class DepartureAirportMessageException extends RuntimeException {
    private DepartureAirportMessage msg;

    /**
     * Instantiation of Departure message exception.
     * @param error text with error
     * @param msg message with exception
     */ 
    public DepartureAirportMessageException(String error, DepartureAirportMessage msg) {
        super(error);
        this.msg = msg;
    }

    /**
     * Obtaining the departure message with error.
     * @return message
     */
    public DepartureAirportMessage getMsg() {
        return msg;
    }
    
}
