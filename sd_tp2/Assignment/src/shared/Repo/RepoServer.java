package shared.Repo;
import communication.ChannelServer;
import static communication.ChannelPorts.PORT_REPO;
/**
 * 
 * @author Leandro e João
 */
public class RepoServer {
    /**
     * Server running the Repo.
     * @param args -
     */    
    public static void main(String[] args) {
        
        Airlift airlift = new Airlift();
        RepoInterface repoInterface = new RepoInterface (airlift);
        ChannelServer listeningSocket = new ChannelServer(PORT_REPO);
        ChannelServer communicationSocket;
        RepoProxyClient proxyClient;
        
        listeningSocket.start();
        
        System.out.println(" Repo server up!");
        
        while(true) {
            try {
                communicationSocket = listeningSocket.accept();
                proxyClient = new  RepoProxyClient(communicationSocket, repoInterface);
                
                Thread.UncaughtExceptionHandler h = (Thread t, Throwable ex) -> {
                    System.out.println(" Repo server down!");
                    System.exit(0);
                };
                        
                proxyClient.setUncaughtExceptionHandler(h);
                proxyClient.start();
            }
            catch(Exception ex) {
                break;
            }
        }       
        System.out.println(" Repo server down!");
    }
}
