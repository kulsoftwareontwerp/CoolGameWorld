package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * A boat is a type of element in the CoolGameWorld, it has a type, a
 * coordinate and an orientation.
 * 
 * @version 0.1
 * @author group17
 */
public class Boat extends Element implements SolidElement {
	private BoatState boatState;

	/**
	 * Create a Boat element.
	 * 
	 * @param coordinate The coordinate for this boat.
	 */
	Boat(Coordinate coordinate) {
		super(coordinate);
		this.boatState = BoatState.FLOATING;
	}


	
	public BoatState getBoatState() {
		return boatState;
	}


	
	public void setBoatState(BoatState state) {
		if(state == null)
			this.boatState = BoatState.FLOATING;
		else
			this.boatState = state;
	}

	@Override
	public ElementType getType() {
		return ElementType.BOAT;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Boat))
			return false;
		Boat boat = (Boat) obj;
		if (boat.getBoatState() != getBoatState())
			return false;

		return true;
	}

}