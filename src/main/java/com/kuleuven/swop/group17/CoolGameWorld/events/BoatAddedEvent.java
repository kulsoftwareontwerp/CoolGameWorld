package com.kuleuven.swop.group17.CoolGameWorld.events;

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
	private Orientation orientation;
	
	/**
	 * Create the BoatAddedEvent
	 * 
	 * @param 	coordinate
	 * 			The coordinate to which the element was added.
	 * @param 	orientation
	 * 			The orientation
	 */
	BoatAddedEvent(Coordinate coordinate, Orientation orientation) {
		super();
		this.coordinate = coordinate;
		this.orientation = orientation;
	}
	

	
	/**
	 * Retrieve the coordinate
	 * @return the coordinate of the added boat
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	/**
	 * Retrieve the orientation
	 * @return the orientation of the added boat
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	

}