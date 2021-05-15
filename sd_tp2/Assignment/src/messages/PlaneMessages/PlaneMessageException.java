package messages.PlaneMessages;

public class PlaneMessageException extends RuntimeException {
    
    private PlaneMessage msg;

    /**
     * Instantiation of Lounge message exception.
     * @param error text with error
     * @param msg message with exception
     */ 
    public PlaneMessageException(String error, PlaneMessage msg) {
        super(error);
        this.msg = msg;
    }

    /**
     * Obtaining the Lounge message with error.
     * @return message
     */
    public PlaneMessage getMsg() {
        return msg;
    }
    
}
