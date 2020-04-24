package com.kuleuven.swop.group17.CoolGameWorld.types;

/**
 * The elementTypes in the CoolGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public enum ElementType {
	ICEBERG{
		public String toString() {
			return "iceberg";
		}
		public String toBoatStateString(BoatState boatState) {
			return toString();
		}
	},
	BOAT{
		public String toString() {
			return "boat";
		}

		
		public String toBoatStateString(BoatState boatState) {
			if(boatState==null) {
				boatState = BoatState.FLOATING;
			}

			return toString()+boatState.toString();				
		}
		
	},
	GOAL{
		public String toString() {
			return "goal";
		}

		
		public String toBoatStateString(BoatState boatState) {
			return toString();				
		}
	}, 
	WATER{
		public String toString() {
			return "water";
		}

		
		public String toBoatStateString(BoatState boatState) {
			return toString();
		}
	};
	
	public abstract String toBoatStateString(BoatState boatState);
}