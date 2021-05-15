package shared.Plane;
import communication.ChannelServer;
import static communication.ChannelPorts.PORT_PLANE;;
/**
 * 
 * @author Leandro e João
 */
public class PlaneServer {
    /**
     * Server running the DestinationAirport.
     * @param args -
     */    
    public static void main(String[] args) {
        
        Plane plane = new Plane();
        PlaneInterface planeInterface = new PlaneInterface (plane);
        ChannelServer listeningSocket = new ChannelServer(PORT_PLANE);
        ChannelServer communicationSocket;
        PlaneProxyClient proxyClient;
        
        listeningSocket.start();
        
        System.out.println(" Plane server up!");
        
        while(true) {
            try {
                communicationSocket = listeningSocket.accept();
                proxyClient = new  PlaneProxyClient(communicationSocket,  planeInterface);
                
                Thread.UncaughtExceptionHandler h = (Thread t, Throwable ex) -> {
                    System.out.println(" Plane server down!");
                    System.exit(0);
                };
                        
                proxyClient.setUncaughtExceptionHandler(h);
                proxyClient.start();
            }
            catch(Exception ex) {
                break;
            }
        }       
        System.out.println(" Plane server down!");
    }
    
}
