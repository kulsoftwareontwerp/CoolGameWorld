package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * The ElementRepository manages all elements in the CoolGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public class ElementRepository {

	private Collection<Element> elements;
	private ElementFactory factory;
	private final int gameAreaHeight=11;
	private final int gameAreaWidth=5;

	

	/**
	 * Create a new ElementRepository
	 */
	ElementRepository() {
		factory=new ElementFactory();
		elements=new HashSet<Element>();
	}

	


	/**
	 * Retrieve a set of elements by its coordinates
	 * @param coordinate The coordinate of the elements to retrieve.
	 * @return a set with all elements on the given coordinate.
	 */
	public Set<Element> getElements(Coordinate coordinate) {
		HashSet<Element> resultSet = new HashSet<Element>();
		
		Iterator<Element> iterator = getElements().iterator();
		while(iterator.hasNext()) {
			Element element = iterator.next();
			if(element.getCoordinate().equals(coordinate)) {
				resultSet.add(element);
			}
		}
		
		return resultSet;
	}

	/**
	 * Add an element to the gameWorld.
	 * @param type	The type of the element.
	 * @param coordinate	The coordinate of the element.
	 */
	public void addElement(ElementType type, Coordinate coordinate) {
		Element el = factory.createElement(type, coordinate);
		elements.add(el);
	}
	
	/**
	 * Retrieve all elements in the gameWorld.
	 * @return all elements in the gameWorld.
	 */
	public Set<Element> getElements() {
		return new HashSet<Element>(elements);
	}

	
	/**
	 * Retrieve the height of the game area.
	 * @return the height of the game area
	 */
	public int getGameAreaHeight() {
		return gameAreaHeight;
	}

	/**
	 * Retrieve the width of the game area.
	 * @return the width of the game area
	 */
	public int getGameAreaWidth() {
		return gameAreaWidth;
	}



	/**
	 * Retrieve elements based on its type.
	 * @param type the ElementType of the elements to retrieve.
	 * @return a set with all elements of the given type.
	 */
	public Set<Element> getElementByType(ElementType type) {
		return elements.stream().filter(s->s.getType()==type).collect(Collectors.toSet());
		
	}



	/**
	 * Remove all elements in the gameWorld
	 */
	public void clearElements() {
		elements.clear();		
	}

}