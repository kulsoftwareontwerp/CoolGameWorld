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
			return "some king of cold white mountain.";
		}
		public String toOrientationString(Orientation orientation) {
			return toString();
		}
	},
	BOAT{
		public String toString() {
			return "The Titanic, strongest boat ever !";
		}

		@Override
		public String toOrientationString(Orientation orientation) {
			if(orientation==null) {
				orientation = Orientation.UP;
			}

			return toString()+orientation.toString();				
		}
		
	},
	GOAL{
		public String toString() {
			return "New York";
		}

		@Override
		public String toOrientationString(Orientation orientation) {
			return toString();
		}
	}, 
	WATER{
		public String toString() {
			return "water";
		}

		@Override
		public String toOrientationString(Orientation orientation) {
			return toString();
		}
	};
	
	public abstract String toOrientationString(Orientation orientation);
}