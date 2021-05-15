package shared.DestinationAirport;
import communication.ChannelServer;
import static communication.ChannelPorts.PORT_DESTINATION;
import shared.DestinationAirport.*;
/**
 * 
 * @author Leandro e João
 */
public class DestinationServer {
    /**
     * Server running the DestinationAirport.
     * @param args -
     */    
    public static void main(String[] args) {
        
        Destinationairport destinationAirport = new Destinationairport();
        DestinationInterface destinationInterface = new DestinationInterface (destinationAirport);
        ChannelServer listeningSocket = new ChannelServer(PORT_DESTINATION);
        ChannelServer communicationSocket;
        DestinationProxyClient proxyClient;
        
        listeningSocket.start();
        
        System.out.println("DestinationAirport server up!");
        
        while(true) {
            try {
                communicationSocket = listeningSocket.accept();
                proxyClient = new DestinationProxyClient(communicationSocket, destinationInterface);
                
                Thread.UncaughtExceptionHandler h = (Thread t, Throwable ex) -> {
                    System.out.println("DestinationAirport server down!");
                    System.exit(0);
                };
                        
                proxyClient.setUncaughtExceptionHandler(h);
                proxyClient.start();
            }
            catch(Exception ex) {
                break;
            }
        }       
        System.out.println("DestinationAirport server down!");
    }
}
