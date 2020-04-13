package com.kuleuven.swop.group17.CoolGameWorld.events;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;

/**
 * The event thrown when the boat has been changed in the domain.
 * 
 * @version 0.1
 * @author group17
 */
public class BoatChangedEvent implements EventObject {

	private Coordinate coordinate;
	private Orientation orientation;

	/**
	 * Create the BoatChangeEvent
	 * 
	 * @param coordinate  The coordinate to which the element was added.
	 * @param orientation The orientation
	 */
	BoatChangedEvent(Coordinate coordinate, Orientation orientation) {
		super();
		this.coordinate = coordinate;
		this.orientation = orientation;
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
	 * Retrieve the orientation
	 * 
	 * @return the new orientation of the boat
	 */
	public Orientation getOrientation() {
		return orientation;
	}

}