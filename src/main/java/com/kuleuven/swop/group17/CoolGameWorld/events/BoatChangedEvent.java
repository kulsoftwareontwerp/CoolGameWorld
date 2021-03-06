package com.kuleuven.swop.group17.CoolGameWorld.events;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;

/**
 * The event thrown when the boat has been changed in the domain.
 * 
 * @version 0.1
 * @author group17
 */
public class BoatChangedEvent implements EventObject {

	private Coordinate coordinate;
	private BoatState boatState;

	/**
	 * Create the BoatChangeEvent
	 * 
	 * @param coordinate  The coordinate to which the element was added.
	 * @param boatState The boatstate
	 */
	BoatChangedEvent(Coordinate coordinate,BoatState boatState) {
		super();
		this.coordinate = coordinate;
		this.boatState = boatState;
	}

	/**
	 * Retrieve the coordinate of the boat.
	 * 
	 * @return the coordinate of the boat
	 */
	public Coordinate getCoordinate() {

		return coordinate;
	}

	/**
	 * Retrieve the floating state of the boat.
	 * @return the state of the boat.
	 */
	public BoatState getBoatState() {
		return boatState;
	}

}