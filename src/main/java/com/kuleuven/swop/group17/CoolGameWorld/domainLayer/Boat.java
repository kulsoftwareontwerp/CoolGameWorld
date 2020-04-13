package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;

/**
 * A boat is a type of element in the CoolGameWorld, it has a type, a
 * coordinate and an orientation.
 * 
 * @version 0.1
 * @author group17
 */
public class Boat extends Element implements SolidElement {
	private Orientation orientation;

	/**
	 * Create a Boat element.
	 * 
	 * @param coordinate The coordinate for this boat.
	 */
	Boat(Coordinate coordinate) {
		super(coordinate);
		this.orientation = null;
	}

	/**
	 * Retrieve the orientation
	 * 
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return this.orientation;
	}

	/**
	 * Set the orientation to the given orientation
	 * 
	 * @param orientation the orientation to which the boat orientation must be
	 *                    set.
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public ElementType getType() {
		return ElementType.BOAT;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		Boat boat = (Boat) obj;
		if (boat.getType() != getType())
			return false;
		if (boat.getOrientation() != getOrientation())
			return false;

		return true;
	}

}