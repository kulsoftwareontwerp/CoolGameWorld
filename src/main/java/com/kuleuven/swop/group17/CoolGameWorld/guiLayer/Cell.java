package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * A Cell is the visual representation of an Element. No element corresponds to
 * a cell with ElementType sand.
 * 
 * @version 0.1
 * @author group17
 */
public class Cell {

	private ElementType type;
	private String resourcePath;
	private Coordinate coordinate;
	private BoatState boatState;
	private BufferedImage image;
	
	private boolean triggerIOException;

	/**
	 * Create a cell with the given ElementType,boatState and Coordinate
	 * 
	 * @param type        The ElementType corresponding to the Cell.
	 * @param coordinate  The coordinate of the cell.
	 * @param boatState 	The boatState of the cell
	 */
	public Cell(Coordinate coordinate, BoatState boatState, ElementType type)  {
		triggerIOException = false;
		if(coordinate == null)
			throw new IllegalArgumentException("coordinate can't be null.");
		if(type == null)
			setType(ElementType.WATER);
		else
			setType(type);
		setCoordinate(coordinate);
		setType(type);
		setBoatState(boatState);
	}

	/**
	 * Retrieve the boatState associated with this Cell
	 * 
	 * @return the boatState associated with this Cell
	 */
	public BoatState getBoatState() {
		return boatState;
	}
	
	
	/**
	 * Set the boatState associated with this Cell
	 * 
	 * @param boatState The new boatState to be associated with this Cell
	 */
	public void setBoatState(BoatState boatState) {
		this.boatState = boatState;
		setResourcePath("CoolGameWorld/images/" + getType().toBoatStateString(getBoatState()) + ".png");
		createImage();
		
	}
	
	

	/**
	 * Set the Coordinate of this Cell.
	 * 
	 * @param coordinate The coordinate to set this cell to.
	 */
	public void setCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			throw new IllegalArgumentException("coordinate can't be null.");
		}
		this.coordinate = coordinate;
	}

	/**
	 * Change the coordinate of this cell with the given offset.
	 * 
	 * @param offset The offset to adapt the coordinates with.
	 */
	public void setCoordinateOffset(Coordinate offset) {
		if (offset == null) {
			throw new IllegalArgumentException("offset can't be null.");
		}
		coordinate = coordinate.setX(coordinate.getX() + offset.getX());
		coordinate = coordinate.setY(coordinate.getY() + offset.getY());
	}

	/**
	 * Retrieve the coordinate for this cell
	 * 
	 * @return the coordinate for this cell.
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Retrieve the type of this Cell
	 * 
	 * @return the type of this Cell.
	 */
	public ElementType getType() {
		return type;
	}

	/**
	 * Set the type of this cell
	 * 
	 * @param type The elementType to set the type of this cell to.
	 */
	public void setType(ElementType type)  {
		if (type == null) {
			type = ElementType.WATER;
		}
		this.type = type;
		setResourcePath("CoolGameWorld/images/" + getType().toBoatStateString(getBoatState()) + ".png");
		createImage();
	}


	private String getResourcePath() {
		return resourcePath;
	}

	private void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;

	}

	private void createImage()  {
		BufferedImage image;
		InputStream in = getClass().getClassLoader().getResourceAsStream(getResourcePath());

		if (in == null) {
			throw new IllegalArgumentException("image for Cell is not found ");
		} else {
			try {
				if(triggerIOException) {
					throw new IOException();
				}
				image = ImageIO.read(in);
			} catch (IOException e) {
				System.err.println("Got an error while loading in image");
				throw new RuntimeException(e);
			}
		}
		this.image = image;
	}

	/**
	 * Retrieve the image associated with this Cell
	 * 
	 * @return the image associated with this Cell
	 */
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coordinate.hashCode();
		result = prime * result + type.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (!coordinate.equals(other.coordinate))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
