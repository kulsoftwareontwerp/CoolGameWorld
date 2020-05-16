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
	private boolean cloneSupported;

	/**
	 * Create an element given a certain coordinate
	 * 
	 * @param coordinate the coordinate for this element.
	 */
	Element(Coordinate coordinate) {
		setCoordinate(coordinate);
		this.cloneSupported = true;
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
		if(coordinate == null)
			throw new IllegalArgumentException("Coordinate can't be null");
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
			/**
			 * CloneNotSupportedException is a very stupid checked exception that's thrown
			 * when a class doesn't implement the cloneable interface or when the class
			 * itself wants to throw a not cloneableException. Hence it won't be able to be
			 * thrown in a normal scenario. That's why there's a private flag here with the
			 * only goal to use reflection in the tests to trigger the exception.
			 */
			if (cloneSupported) {
				return (Element) super.clone();
			} else {
				throw new CloneNotSupportedException();
			}
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coordinate.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Element))
			return false;
		Element other = (Element) obj;
		if (!getCoordinate().equals(other.getCoordinate()))
			return false;
		if (other.getType() != getType())
			return false;
		return true;
	}
	
	

}