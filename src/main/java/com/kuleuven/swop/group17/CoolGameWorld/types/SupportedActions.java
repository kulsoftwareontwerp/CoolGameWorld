package com.kuleuven.swop.group17.CoolGameWorld.types;
/**
 * The supported actions of the CoolGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public enum SupportedActions {
	TURNLEFT{
		public String toString() {
			return "To Port !";
		}
	},TURNRIGHT{
		public String toString() {
			return "To Starboard !";
		}
	},MOVEFORWARD{
		public String toString() {
			return "Sail Forward";
		}
	}
}
