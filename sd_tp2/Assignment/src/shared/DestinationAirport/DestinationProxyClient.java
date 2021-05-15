package shared.DestinationAirport;
import communication.ChannelServer;
import messages.DestinationAirportMessages.DestinationAirportMessage;
import messages.DestinationAirportMessages.DestinationAirportMessageException;
/**
 *
 * @author Leandro e João
 */

public class DestinationProxyClient extends Thread{
    private ChannelServer cs;   
    private DestinationInterface destinationInterface;
    
    /**
     * DestinationAirport proxy client constructor.
     * 
     * @param cs communication channel
     * @param loungeInterface Destination interface
     */
    public DestinationProxyClient(ChannelServer cs, DestinationInterface destinationInterface) {
        this.cs = cs;
        this.destinationInterface = destinationInterface;
    }
    
    @Override
    public void run() {
        DestinationAirportMessage in;
        DestinationAirportMessage out = null;
        
        in = ( DestinationAirportMessage) cs.readObject();
        
        try {
            out = destinationInterface.process(in);
        }
        catch(DestinationAirportMessageException e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMsg().toString());
            System.out.println("Failed to process request : " + in);
            System.exit(1);
        }
        if(!this.destinationInterface.getStatus()) {
            cs.writeObject(out);
            cs.close();
            throw new DestinationAirportMessageException("End message received", out);
        }
        else {
            cs.writeObject(out);
            cs.close();
        }
    }
    
}
