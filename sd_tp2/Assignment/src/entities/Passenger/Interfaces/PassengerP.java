/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.Passenger.Interfaces;

import entities.Passenger.States.*;
/**
 *
 * @author Leandro e João
 */
public interface PassengerP {
	public boolean BoardThePlane(int id,PassengerState state);
	public void WaitingForEndOfFlight();
}