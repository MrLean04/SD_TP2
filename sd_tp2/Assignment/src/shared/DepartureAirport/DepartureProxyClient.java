package shared.DepartureAirport;
import communication.ChannelServer;
import messages.DepartureAirportMessages.DepartureAirportMessage;
import messages.DepartureAirportMessages.DepartureAirportMessageException;
/**
 *
 * @author Leandro e João
 */

public class DepartureProxyClient extends Thread {   
    private ChannelServer cs;   
    private DepartureInterface departureInterface;
    
    /**
     * DepartureAirport proxy client constructor.
     * 
     * @param cs communication channel
     * @param departureInferface Departure interface
     */
    public DepartureProxyClient(ChannelServer cs, DepartureInterface departureInterface) {
        this.cs = cs;
        this.departureInterface = departureInterface;
    }
    
    @Override
    public void run() {
        DepartureAirportMessage in;
        DepartureAirportMessage out = null;
        
        in = (DepartureAirportMessage) cs.readObject();
        
        try {
            out = departureInterface.process(in);
        }
        catch(DepartureAirportMessageException e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMsg().toString());
            System.out.println("Failed to process request : " + in);
            System.exit(1);
        }
        if(!this.departureInterface.getStatus()) {
            cs.writeObject(out);
            cs.close();
            throw new DepartureAirportMessageException("End message received", out);
        }
        else {
            cs.writeObject(out);
            cs.close();
        }
    }
    
}
