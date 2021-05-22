package shared.Repo;
import communication.ChannelServer;
import messages.RepoMessages.*;
/**
 *
 * @author Leandro e João
 */
public class RepoProxyClient extends Thread{
    private ChannelServer cs;   
    private RepoInterface repoInterface;
    
    /**
     * Repo proxy client constructor.
     * 
     * @param cs communication channel
     * @param repoInterface lounge interface
     */
    public RepoProxyClient(ChannelServer cs, RepoInterface repoInterface) {
        this.cs = cs;
        this.repoInterface = repoInterface;
    }
    
    @Override
    public void run() {
        RepoMessage in=null;
        RepoMessage out = null;
        
        in = ( RepoMessage) cs.readObject();
        
        try {
            out = repoInterface.process(in);
        }
        catch(RepoMessageException e) {
            System.out.println("Thread " + getName() + ": " + e.getMessage() + "!");
            System.out.println(e.getMsg().toString());
            System.out.println("Failed to process request : " + in);
            System.exit(1);
        }
        if(!this.repoInterface.getStatus()) {
            cs.writeObject(out);
            cs.close();
            throw new RepoMessageException("End message received", out);
        }
        else {
            cs.writeObject(out);
            cs.close();
        }
    }
}
