package com.kuleuven.swop.group17.CoolGameWorld.types;

public enum BoatState {
	FLOATING{
		public String toString() {
			return "floating";
		}
	},
	CRASHED{
		public String toString() {
			return "crashed";
		}
	},
	ARRIVED{
		public String toString() {
			return "arrived";
		}
	};
}
