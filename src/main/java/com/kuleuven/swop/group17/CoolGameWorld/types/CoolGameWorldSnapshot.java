/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import java.util.HashSet;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;

/**
 * The CoolGameWorldSnapshot is a snapshot of the state of a boatGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public class CoolGameWorldSnapshot implements GameWorldSnapshot {
	private Set<Element> elements;

	
	/**
	 * Create a snapshot of the boatGameWorld.
	 * @param elements All the elements from the boatGameWorld that define the current state.
	 */
	CoolGameWorldSnapshot(Set<Element> elements) {
		super();
		if(elements==null) {
			throw new NullPointerException("The given elements can't be null");
		}
		setElements(elements);
	}

	/**
	 * Retrieve all the elements in this CoolGameWorldSnapshot
	 * @return all the elements in this CoolGameWorldSnapshot
	 */
	public Set<Element> getElements() {
		return new HashSet<Element>(elements);
	}

	
	private void setElements(Set<Element> elements) {		
		HashSet<Element> elems = new HashSet<Element>();
		for(Element e:elements ) {
			elems.add(e.clone());
		}
		
		
		this.elements = elems;
	}
	
}
