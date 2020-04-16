/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.events;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * EventFactory
 *
 * @version 0.1
 * @author group17
 */
public class EventFactory {
	/**
	 * Create a EventFactory
	 */
	public EventFactory() {
		super();
	}
	
	/**
	 * Create a BoatChangeEvent with the given Coordinate and boatState.
	 * @param coordinate The new Coordinate of the Boat.
	 * @param boatState The new State of the Boat.
	 * @return a new BoatChangeEvent.
	 */
	public BoatChangedEvent createBoatChangedEvent(Coordinate coordinate,BoatState boatState) {
		return new BoatChangedEvent(coordinate, boatState);
	}
	
	/**
	 * Create a BoatAddedEvent with the given Coordinate and boatState.
	 * @param coordinate The new Coordinate of the Boat.
	 * @param boatState The new boatState of the Boat.
	 * @return a new BoatAddedEvent.
	 */
	public BoatAddedEvent createBoatAddedEvent(Coordinate coordinate,BoatState boatState) {
		return new BoatAddedEvent(coordinate, boatState);
	}
	
	/**
	 * Create a new ElementsClearedEvent
	 * @return a new ElementsClearedEvent
	 */
	public ElementsClearedEvent createElementsClearedEvent() {
		return new ElementsClearedEvent();
	}
	
	/**
	 * Create a new ElementAddedEvent with the given Coordinate and ElementType
	 * @param coordinate The Coordinate for the new Element.
	 * @param type The ElementType of the new Element.
	 * @return a new ElementAddedEvent
	 */
	public ElementAddedEvent createElementAddedEvent(Coordinate coordinate,ElementType type) {
		return new ElementAddedEvent(coordinate, type);
	}
	
	
}
