package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * A goal is a type of element in the CoolGameWorld, it has a type and a
 * coordinate.
 * 
 * @version 0.1
 * @author group17
 */
public class Goal extends Element {

	/**
	 * Create a Goal
	 * 
	 * @param coordinate the coordinate for this goal.
	 */
	Goal(Coordinate coordinate) {
		super(coordinate);

	}

	@Override
	public ElementType getType() {
		return ElementType.GOAL;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Goal goal = (Goal) obj;
		if (goal.getType() != getType())
			return false;

		return true;
	}

}