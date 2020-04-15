package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

import java.io.IOException;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;

/**
 * The CellFactory is responsible for creating cells in the GUI.
 * 
 * @version 0.1
 * @author group17
 */
public class CellFactory {
	CellFactory() {
	}

	/**
	 * Create a cell with the given ElementType,Orientation and Coordinate
	 * 
	 * @param type        The ElementType corresponding to the Cell.
	 * @param coordinate  The coordinate of the cell.
	 * @param orientation The orientation of the cell
	 * @return The cell corresponding to the given parameters.
	 * @throws NullPointerException when coordinate is null.
	 */
	public Cell createCell(ElementType type, Coordinate coordinate, BoatState boatState) {
		if (coordinate == null) {
			throw new NullPointerException("coordinate can't be null.");
		}

		return new Cell(coordinate, boatState, type);
	}

	/**
	 * Create a cell with the given ElementType and Coordinate
	 * 
	 * @param type       The ElementType corresponding to the Cell.
	 * @param coordinate The coordinate of the cell.
	 * @return The cell corresponding to the given parameters.
	 * @throws NullPointerException when coordinate is null.
	 */
	public Cell createCell(ElementType type, Coordinate coordinate) {

		return createCell(type, coordinate, null);
	}
}
