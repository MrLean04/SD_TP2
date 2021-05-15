/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.Hostess.Interfaces;

import entities.Hostess.States.*;

/**
 *
 * @author Leandro e João
 */
public interface HostessDA {

    public  void preparePassBoarding();

        public  boolean checkAndWait(HostessState state);

    
        public  boolean planeReadyToTakeoff(); 

    
        public  void waitForNextFlightH(HostessState state);
        //public  boolean readyForCheck();
        public boolean hostessJobDone( HostessState state);
        public  boolean queueNotEmpty( HostessState state);
}