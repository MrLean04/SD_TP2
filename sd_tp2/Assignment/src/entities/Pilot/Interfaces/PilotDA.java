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
public interface PilotDA {
	public void parkAtTransfer( PilotState PilotState);
    public void readyForBoarding( PilotState PilotState);
    public void WaitForBoarding( PilotState PilotState);
    public void everyoneStops();
    public void last();
}