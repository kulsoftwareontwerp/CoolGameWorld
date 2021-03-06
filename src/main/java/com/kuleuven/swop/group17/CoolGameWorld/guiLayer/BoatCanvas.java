package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

import java.awt.Graphics;
import java.util.Collection;
import java.util.HashMap;

import com.kuleuven.swop.group17.CoolGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.CoolGameWorld.events.BoatAddedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.BoatChangedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;

public class BoatCanvas implements GUIListener {
	private HashMap<Coordinate, Cell> cells;
	private CellFactory factory;
	private final int OFFSET_GAMEAREA_CELLS = 0;
	private static final int CELL_SIZE = 50;
	private TypeFactory typeFactory;

	BoatCanvas() {
		cells = new HashMap<Coordinate, Cell>();
		factory = new CellFactory();
		typeFactory = new TypeFactory();
		initCells();
	}

	private void addCell(Cell cell) {
		cell.setCoordinateOffset(typeFactory.createCoordinate(0, OFFSET_GAMEAREA_CELLS));
		cells.put(cell.getCoordinate(), cell);
	}

	// look for boat, set that cell to WATER
	private void moveBoat(Coordinate coordinate, BoatState boatState) {
		try {
			Cell previousCell = getCells().stream().filter(e -> e.getType() == ElementType.BOAT).findFirst().get();
			previousCell.setType(null);
			Cell boat = factory.createCell(ElementType.BOAT, coordinate, boatState);
			addCell(boat);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initCells() {

		// Intermediate Fill Cells (REAL CELLS)
		for (int x = 0; x <= 4; x++) {
			for (int y = 0; y <= 11; y++) {
				Coordinate coordinate = typeFactory.createCoordinate(x, y);
				cells.put(coordinate, factory.createCell(ElementType.WATER, coordinate));
			}
		}
	}

	private Collection<Cell> getCells() {
		return cells.values();
	}

	/**
	 * Paint the BoatGameArea on the given graphics
	 * 
	 * @param g the graphics on which the BoatGameArea should be drawn.
	 */
	public void paint(Graphics g) {
		if (g == null) {
			throw new IllegalArgumentException("Graphics object can't be null");
		}
		g.drawLine(0, 0, 0, g.getClipBounds().height);
		g.drawLine(0, 200, g.getClipBounds().width, 200);
		g.drawLine(0, 400, g.getClipBounds().width, 400);
		for (Cell cell : getCells()) {
			g.drawImage(cell.getImage(), cell.getCoordinate().getX() * CELL_SIZE,
					cell.getCoordinate().getY() * CELL_SIZE, null);
		}
	}

	private void clearCells() {
		cells.clear();
		initCells();
	}
	@Override
	public void onBoatChangeEvent(BoatChangedEvent event) {
		if(event == null)
			throw new IllegalArgumentException("event can't be null");
		moveBoat(event.getCoordinate(), event.getBoatState());
	}
	@Override
	public void onBoatAddedEvent(BoatAddedEvent event) {
		if(event == null)
			throw new IllegalArgumentException("event can't be null");
		addCell(factory.createCell(ElementType.BOAT, event.getCoordinate(), event.getBoatSate()));

	}

	@Override
	public void onElementAddedEvent(ElementAddedEvent event) {
		if(event == null)
			throw new IllegalArgumentException("event can't be null");
		addCell(factory.createCell(event.getType(), event.getCoordinate()));
	}

	@Override
	public void onElementsClearedEvent(ElementsClearedEvent event) {
		if(event == null)
			throw new IllegalArgumentException("event can't be null");
		clearCells();

	}


}