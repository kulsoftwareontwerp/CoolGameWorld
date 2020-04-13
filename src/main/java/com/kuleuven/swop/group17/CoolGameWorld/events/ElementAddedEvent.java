package com.kuleuven.swop.group17.CoolGameWorld.events;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;

/**
 * The event thrown when an element has been added to the domain.
 * 
 * @version 0.1
 * @author group17
 */
public class ElementAddedEvent implements EventObject {

	private Coordinate coordinate;
	private ElementType type;
	
	/**
	 * Create the ElementAddedEvent
	 * 
	 * @param 	coordinate
	 * 			The coordinate to which the element was added.
	 * @param 	type
	 * 			The ElementType
	 */
	ElementAddedEvent(Coordinate coordinate, ElementType type) {
		super();
		this.coordinate = coordinate;
		this.type = type;
	}

	/**
	 * Retrieve the coordinate
	 * @return the coordinate of the added element
	 */
	public Coordinate getCoordinate() {
		
		return coordinate;
	}

	/**
	 * Retrieve the ElementType
	 * @return the ElementType of the added element
	 */
	public ElementType getType() {
		return type;
	}
	
	
	



}