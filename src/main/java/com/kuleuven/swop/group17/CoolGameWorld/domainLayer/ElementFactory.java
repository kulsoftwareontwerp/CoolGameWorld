package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
/**
 * The ElementFactory is responsible for creating elements in the domain.
 * 
 * 
 * @version 0.1
 * @author group17
 */
public class ElementFactory {

	ElementFactory() {	
	}

	/**
	 * Create an element with the given type and coordinate.
	 * @param type	The type to set this element to.
	 * @param coordinate	The coordinate to set this element to.
	 * @throws 	IllegalArgumentException
	 * 			thrown when type is null or sand.
	 * @return an element with the givent type and coordinate.
	 */
	public Element createElement(ElementType type, Coordinate coordinate) {
		Element element;
		switch (type) {
		case GOAL:
			element=new Goal(coordinate);
			break;
		case BOAT:
			element=new Boat(coordinate);
			break;
		case ICEBERG:
			element=new IceBerg(coordinate);
			break;
		default:
			throw new IllegalArgumentException("Illegal elementType to create: " + type);
		}
		
		return element;
	}


}