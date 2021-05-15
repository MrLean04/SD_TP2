package communication;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Server side communication channel creation.
 * 
 * @author André Oliveira
 * @author João Coelho
 */

public class ChannelServer {

    /**
     * Listening socket.
     */
    private ServerSocket listening_socket = null;
    
    /**
     * Communication socket.
     */
    private Socket communication_socket = null;
    
    /**
     * Number of the listening port of the server.
     */
    private int server_port;

    /**
     * Input stream of the communication channel.
     */
    private ObjectInputStream input_stream = null;
    
    /**
     * Output stream of the communication channel.
     */
    private ObjectOutputStream output_stream = null;

    /**
     * Instantiation of a communication channel.
     * @param portNumb number of the listening port of the server.
     */
    public ChannelServer(int portNumb){
        server_port = portNumb;
    }

    /**
     * Instantiation of a communication channel.
     * @param portNumb number of the listening port of the server.
     * @param lSocket listening socket of the server
     */
    public ChannelServer(int portNumb, ServerSocket lSocket){
        server_port = portNumb;
        listening_socket = lSocket;
    }

    /**
     * Establishes the listening socket.
     */
    public void start (){
        try{ 
            listening_socket = new ServerSocket (server_port);
        }
        catch (BindException e){
            System.out.println(Thread.currentThread ().getName () + " - could not link listening socket to port: " + server_port + "!");
            e.printStackTrace ();
            System.exit (1);
        }
        catch (IOException e){
            System.out.println(Thread.currentThread ().getName () + " - unkown error ocurred while linking listening socket to port: " + server_port + "!");
            e.printStackTrace ();
            System.exit (1);
        }
    }

    /**
     * Closes the service and the listening socket.
     */
    public void end (){
        try{ 
            listening_socket.close ();
        }
        catch (IOException e){
            System.out.println(Thread.currentThread ().getName () + " - não foi possível fechar o socket de escuta!");
            e.printStackTrace ();
            System.exit (1);
        }
    }  

    /**
     * Listening process. Creates a communication channel for a pending request.
     * Opens input and output streams.
     * @param a -
     * @return communication channel
     */
    public ChannelServer accept (boolean... a){
        
        ChannelServer scon;

        scon = new ChannelServer(server_port, listening_socket);
        
        try{ 
            scon.communication_socket = listening_socket.accept();
        }
        catch (SocketException e){ 
            return scon;
        }
        catch (IOException e)
        { 
            System.out.println(Thread.currentThread ().getName () + " - could not open a communication channel for a pending request!");
            e.printStackTrace ();
            System.exit (1);
        }

        try{ 
            scon.input_stream = new ObjectInputStream (scon.communication_socket.getInputStream ());
        }
        catch (IOException e){ 
            System.out.println(Thread.currentThread ().getName () + " - could not open socket input channel!");
            e.printStackTrace ();
            System.exit (1);
        }

        try
        { 
            scon.output_stream = new ObjectOutputStream (scon.communication_socket.getOutputStream ());
        }
        catch (IOException e){
            System.out.println(Thread.currentThread ().getName () + " - could not open socket output channel");
            e.printStackTrace ();
            System.exit (1);
        }

        return scon;
    }

    /**
     * Closes the communication channel, socket and input and output streams.
     */
    public void close (){
        try{ 
            input_stream.close();
        }
        catch (IOException e){
            System.out.println(Thread.currentThread ().getName () + " - could not close socket input channel!");
            e.printStackTrace ();
            System.exit (1);
        }

        try{ 
            output_stream.close();
        }
        catch (IOException e){ 
            System.out.println(Thread.currentThread ().getName () + " - could not close socket output channel!");
            e.printStackTrace ();
            System.exit (1);
        }

        try{ 
            communication_socket.close();
        }
        catch (IOException e){
            System.out.println(Thread.currentThread ().getName () + " - could not close communication channel!");
            e.printStackTrace ();
            System.exit (1);
        }
    } 

    /**
     * Reading of a communication channel object.
     * @return object read.
     */
    public Object readObject(){

        Object fromClient = null;

        try{ 
            fromClient = input_stream.readObject ();
        }
        catch (InvalidClassException e){
            System.out.println(Thread.currentThread ().getName () + " - could not deserialize object to be read!");
            e.printStackTrace ();
            System.exit (1);
        }
        catch (IOException e){
            System.out.println(Thread.currentThread ().getName () + " - error while reading object from the input channel of the communication socket!");
            e.printStackTrace ();
            System.exit (1);
        }
        catch (ClassNotFoundException e){ 
            System.out.println(Thread.currentThread ().getName () + " - read object corresponds to an unknown data type!");
            e.printStackTrace ();
            System.exit (1);
        }

        return fromClient;
    }

    /**
     * Writes an object to the communication channel.
     * @param toClient object to be written.
     */
    public void writeObject (Object toClient){
        try{ 
            output_stream.writeObject (toClient);
        }
        catch (InvalidClassException e){ 
            System.out.println(Thread.currentThread ().getName () + " - could not deserialize object to be written!");
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotSerializableException e){ 
            System.out.println(Thread.currentThread ().getName () + " - object to be written belong to a data type not serializable!");
            e.printStackTrace ();
            System.exit (1);
        }
        catch (IOException e){ 
            System.out.println(Thread.currentThread ().getName () + " - error while writting object of the output channel communication socket!");
            e.printStackTrace ();
            System.exit (1);
        }
    }
}