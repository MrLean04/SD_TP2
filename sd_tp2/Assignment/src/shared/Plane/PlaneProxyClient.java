package shared.Plane;
import communication.ChannelServer;
import messages.PlaneMessages.*;
//import messages.PlaneMessageException;
/**
 *
 * @author Leandro e João
 */
public class PlaneProxyClient extends Thread{
    private ChannelServer cs;   
    private PlaneInterface planeInterface;
    
    /**
     * Plane proxy client constructor.
     * 
     * @param cs communication channel
     * @param PlaneInterface lounge interface
     */
    public PlaneProxyClient(ChannelServer cs, PlaneInterface planeInterface) {
        this.cs = cs;
        this.planeInterface = planeInterface;
    }
    
    /**
     * Plane proxy run.
     * 
     */
    @Override
    public void run() {
        PlaneMessage in;
        PlaneMessage out = null;
        
        in = ( PlaneMessage) cs.readObject();
        
        try {
            out = planeInterface.process(in);
        }
        catch(PlaneMessageException e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMsg().toString());
            System.out.println("Failed to process request : " + in);
            System.exit(1);
        }
        if(!this.planeInterface.getStatus()) {
            cs.writeObject(out);
            cs.close();
            throw new PlaneMessageException("End message received", out);
        }
        else {
            cs.writeObject(out);
            cs.close();
        }
    }
}
