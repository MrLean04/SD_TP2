package shared.DepartureAirport;
import communication.ChannelServer;
import static communication.ChannelPorts.PORT_DEPARTURE;
import shared.DepartureAirport.*;
/**
 * 
 * @author Leandro e João
 */

public class DepartureServer {
    /**
     * Server running the DepartureAirport.
     * @param args -
     */    
    public static void main(String[] args) {
        
        Departureairport departureAirport = new Departureairport();
        DepartureInterface departureInterface = new DepartureInterface(departureAirport);
        ChannelServer listeningSocket = new ChannelServer(PORT_DEPARTURE);
        ChannelServer communicationSocket;
        DepartureProxyClient proxyClient;
        
        listeningSocket.start();
        
        System.out.println("DepartureAirport server up!");
        
        while(true) {
            try {
                communicationSocket = listeningSocket.accept();
                proxyClient = new DepartureProxyClient(communicationSocket, departureInterface);
                
                Thread.UncaughtExceptionHandler h = (Thread t, Throwable ex) -> {
                    System.out.println("DepartureAirport server down!");
                    System.exit(0);
                };
                        
                proxyClient.setUncaughtExceptionHandler(h);
                proxyClient.start();
            }
            catch(Exception ex) {
                break;
            }
        }       
        System.out.println("DepartureAirport server down!");
    }
}
