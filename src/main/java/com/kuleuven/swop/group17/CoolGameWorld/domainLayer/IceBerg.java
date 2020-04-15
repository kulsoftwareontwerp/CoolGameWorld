package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * A wall is a type of element in the CoolGameWorld, it has a type and a
 * coordinate.
 * 
 * @version 0.1
 * @author group17
 */
public class IceBerg extends Element implements SolidElement {

	/**
	 * Create a wall
	 * 
	 * @param coordinate The coordinate of this wall.
	 */
	IceBerg(Coordinate coordinate) {
		super(coordinate);
	}

	@Override
	public ElementType getType() {
		return ElementType.ICEBERG;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;

		IceBerg wall = (IceBerg) obj;
		if (wall.getType() != getType())
			return false;

		return true;
	}

}