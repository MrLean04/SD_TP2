/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.Pilot.Interfaces;

import entities.Pilot.States.*;

/**
 *
 * @author Leandro e João
 */
public interface PilotP {
	
	public boolean WaitForAllInBoard(PilotState PilotState);
	public void atDestinationPoint(PilotState state);
	public  void lFly();
	public void upd(PilotState PilotState);

}