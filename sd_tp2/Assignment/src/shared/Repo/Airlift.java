package shared.Repo;

import java.util.Queue;

import entities.Hostess.States.*;
import entities.Passenger.States.*;
import entities.Pilot.States.*;
//import genclass.GenericIO;
//import genclass.TextFile;

/**
 *
 * @author Leandro e Jo�o
 */
public class Airlift {

    private int nPassengers;
	private PassengerState[] PassengerStates;	
	private PilotState PilotState;
    private HostessState HostessState;
    private int inqueue=0;
    private int inPlane=0;
    private int atDestination=0;
    private int numberF=1;
	
    private String fileName = "Airlift.log";
    /**
     * Airlift's Constructor
     * @param nPassengers number of passengers
     * @param fileName name of the file 
     */
    public Airlift(int nPassengers, String fileName) {
		this.nPassengers = nPassengers;
		PassengerStates = new PassengerState[nPassengers];
    
        for (int i = 0; i < nPassengers; i++) {
			PassengerStates[i] = PassengerState.values()[0];
		}
		PilotState = PilotState.values()[0];
        HostessState = HostessState.values()[0];
		
        if ((fileName != null) && !("".equals(fileName))) {
            this.fileName = fileName;
        }
        //reportInitialStatus();
    }

    public Airlift(){
      //
    }
    /**
     * Airlift's method. Update from departure airport.
     * @param inQueue queue from departure airport
     * @param id id of the passenger
     * @param PassengerState current state of the passenger
     */
    public synchronized void DepartureairportUpdate( int id, PassengerState PassengerState){
      this.PassengerStates[id]= PassengerState; 
      //reportStatus();
    }
    
    /**
     * Airlift's method. Update from departure airport.
     * @param inQueue queue from departure airport
     */
    public synchronized void DepartureairportUpdate( Queue<Integer> inQueue){
      this.inqueue= inQueue.size();
    }
    /**
     * Airlift's method. Update from departure airport.
     * @param HostessState current state of the hostess
     */
    public synchronized void DepartureairportUpdate( HostessState HostessState){
      this.HostessState= HostessState; 
      //reportStatus();
    }
    
    /**
     * Airlift's method. Update from departure airport.
     * @param numberF number of the flight
     * @param PilotState current state of the pilot
     */
    public synchronized void DepartureairportUpdate( int numberF, PilotState PilotState){
      this.numberF= numberF;
      this.PilotState= PilotState;  
      //reportStatus();
    }
    /**
     * Airlift's method. Update from plane.
     * @param inPlane queue from plane 
     * @param id id of the passenger
     * @param state current state of the passenger
     */
    public synchronized void PlaneUpdate( int id, PassengerState state) {
      this.PassengerStates[id]= state; 
      //reportStatus();
    }
    /**
     * Airlift's method. Update from plane.
     * @param PilotState current state of the pilot
     */
    public synchronized void PlaneUpdate(PilotState PilotState) {
      this.PilotState= PilotState; 
      //reportStatus();
    }
    /**
     * Airlift's method. Update from plane.
     * @param inPlane queue from plane 
     */
    public synchronized void PlaneUpdate(Queue<Integer> inPlane) {
      this.inPlane= inPlane.size();
    	//reportStatus();
    }
    /**
     * Airlift's method. Update from destination airport.
     * @param inDestinationAirport queue from destination airport
     * @param id id of the passenger
     * @param state current state of the passenger
     */
    public synchronized void DestinationairportUpdate(int id, PassengerState state) {
      this.PassengerStates[id]= state;  
      //reportStatus();
    }
    
    public synchronized void DestinationairportUpdate( Queue<Integer> inDestinationAirport) {
      this.atDestination= inDestinationAirport.size();
    }
    
    /**
     * Airlift's method. Update from destination airport.
     * @param PilotState current state from the pilot
     */
    public synchronized void DestinationairportUpdate(PilotState PilotState) {
      this.PilotState= PilotState;  
      //reportStatus();
    }
    
    /**
     *First log report.
     */
    /** 
    public void reportInitialStatus() {
        TextFile log = new TextFile(); 

        //fileName = "Airlift.log";
        if (!log.openForWriting("./src", fileName)) {
            GenericIO.writelnString("A opera�ao de cria�ao do ficheiro " + fileName + " falhou!");
            System.exit(1);
        }
        log.writelnString("                                       Airlift - Description of the internal state");
        
        log.writelnString("");
		
        log.writeString(" PT   HT   P00  P01  P02  P03  P04  P05  P06  P07  P08  P09  P10  P11  P12  P13  P14  P15  P16  P17  P18  P19  P20         InQ InF PTAL\n");

        log.writeString( PilotState.toString() + "  " + HostessState.toString() + "  ");

        for (int i = 0; i < 21; i++) {
          log.writeString(PassengerStates[i].toString() + "  ");
        }
        log.writeString("   " + inqueue + "   " +  inPlane + "   " + atDestination + "\n");	

        if (!log.close()) {
            GenericIO.writelnString("A opera�ao de fecho do ficheiro " + fileName + " falhou! -initialstatus");
            System.exit(1);
        }
        reportStatus();
    }
    /**
     *Report status to log
private void reportStatus() {
        
	TextFile log = new TextFile ();                      // instantiation of a text file handler

    String lineStatus = "";                              // state line to be printed

    if (!log.openForAppending("./src", fileName))
      { GenericIO.writelnString ("The operation of opening for appending the file " + fileName + " failed!");
        System.exit (1);
      }
    switch (PilotState)
      { case AT_TRANSFER_GATE:   lineStatus += "ATG  ";
                                      break;
        case READY_FOR_BOARDING: lineStatus += "RFB  ";
                                      break;
        case WAIT_FOR_BOARDING: lineStatus += "WFB  ";
                                      break;
        case FLYING_FORWARD: lineStatus += "FF  ";
                                      break;
        case DEBOARDING: lineStatus += "D  ";
                                      break;
        case FLYING_BACK: lineStatus += "FB  ";
                                      break;
      }
    
    switch (HostessState)
      { case WAIT_FOR_NEXT_FLIGHT:   lineStatus += "WFNF  ";
                                      break;
        case WAIT_FOR_PASSENGER:   lineStatus += "WFP  ";
                                      break;
        case CHECK_PASSENGER:   lineStatus += "CP  ";
                                      break;
        case READY_TO_FLY:   lineStatus += "RTF  ";
                                      break;
      }
    
    for (int i = 0; i < 21; i++)
      switch (PassengerStates[i])
      { case GOING_TO_AIRPORT:   lineStatus += " GTA ";
                                      break;
        case IN_QUEUE:   lineStatus += " IQ  ";
                                      break;
        case IN_FLIGHT:   lineStatus += "IF  ";
                                      break;
        case AT_DESTINATION:   lineStatus += "AD  ";
                                      break;
      }
    
    lineStatus += "    " + inqueue + "     " + inPlane + "      " + atDestination;
    log.writelnString (lineStatus);
    if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }
    }

public void reportBoarding ()
{
   TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending("./src", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString (" Flight " + numberF + ": boarding started.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }
}


public void reportCheck (int id)
{
   TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending("./src", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": passenger " + id +  " checked.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }
}

  /**
*  Report that the flight has departed.
*  Internal operation.


public void reportDeparted ()
{
   TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending("./src", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": departed with " + "5" +  " passengers.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }
}

public void reportLDeparted ()
{
   TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending("./src", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": departed with " + "1" +  " passengers.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }
}

/**
*  Report that the flight has arrived at arrival airport.
*  Internal operation.


public void reportArrived ()
{
   TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending("./src", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": arrived.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }
}

  /**
*  Report that the flight is returning to the initial airport.
*  Internal operation.


public void reportreturning ()
{
   TextFile log = new TextFile ();                      // instantiation of a text file handler

  if (!log.openForAppending("./src", fileName))
      { GenericIO.writelnString ("The operation of creating the file " + fileName + " failed!");
        System.exit (1);
      }
  log.writelnString ("\n");
  log.writelnString ("Flight " + numberF + ": returning.");
  if (!log.close ())
      { GenericIO.writelnString ("The operation of closing the file " + fileName + " failed!");
        System.exit (1);
      }
}*/

}
