package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * An Element is a concept in the CoolGameWorld, it has a type and a
 * coordinate.
 * 
 * @version 0.1
 * @author group17
 */
public abstract class Element implements Cloneable {

	private Coordinate coordinate;

	/**
	 * Create an element given a certain coordinate
	 * 
	 * @param coordinate the coordinate for this element.
	 */
	Element(Coordinate coordinate) {
		setCoordinate(coordinate);
	}

	/**
	 * Retrieve the coordinate for this element.
	 * 
	 * @return the coordinate for this element.
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Set the Coordinate of this Element.
	 * 
	 * @param coordinate The coordinate to set this element to.
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Retrieve the type of this Element
	 * 
	 * @return the type of this Element.
	 */
	public abstract ElementType getType();

	@Override
	public Element clone() {
		try {
			return (Element) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		return true;
	}
	
	

}