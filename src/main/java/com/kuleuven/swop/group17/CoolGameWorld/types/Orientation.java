package com.kuleuven.swop.group17.CoolGameWorld.types;

/**
 * Possible Orientations UP,LEFT,DOWN,RIGHT with their relative orientations left and right to..
 * 
 * @version 0.1
 * @author group17
 */
public enum Orientation {
	UP {
		public String toString() {
			return "up";
		}
	},
	LEFT {
		public String toString() {
			return "left";
		}
	},
	DOWN {
		public String toString() {
			return "down";
		}
	},
	RIGHT {
		public String toString() {
			return "right";
		}
	};

	private Orientation left;
	private Orientation right;

	static {
		UP.left = LEFT;
		LEFT.left = DOWN;
		DOWN.left = RIGHT;
		RIGHT.left = UP;

		UP.right = RIGHT;
		LEFT.right = UP;
		DOWN.right = LEFT;
		RIGHT.right = DOWN;
	}

	
	/**
	 * Retrieve the orientation left of this orientation.
	 * @return the orientation left of this orientation.
	 */
	public Orientation getLeft() {
		return left;
	}

	/**
	 * Retrieve the orientation right of this orientation.
	 * @return the orientation right of this orientation.
	 */
	public Orientation getRight() {
		return right;
	}

}
