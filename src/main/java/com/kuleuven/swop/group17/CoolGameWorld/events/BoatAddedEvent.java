package com.kuleuven.swop.group17.CoolGameWorld.events;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;

/**
 * The event thrown when the boat has been added to the domain.
 * 
 * @version 0.1
 * @author group17
 */
public class BoatAddedEvent implements EventObject {

	private Coordinate coordinate;
	private BoatState boatState;
	
	/**
	 * Create the BoatAddedEvent
	 * 
	 * @param 	coordinate
	 * 			The coordinate to which the element was added.
	 * @param 	orientation
	 * 			The orientation
	 */
	BoatAddedEvent(Coordinate coordinate, BoatState boatState) {
		super();
		this.coordinate = coordinate;
		this.boatState = boatState;
	}
	

	
	/**
	 * Retrieve the coordinate
	 * @return the coordinate of the added boat
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	/**
	 * Retrieve the boatState
	 * @return
	 */
	public BoatState getBoatSate() {
		return boatState;
	}
	

}