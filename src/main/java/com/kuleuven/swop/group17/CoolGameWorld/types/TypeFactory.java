/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import java.util.Set;

import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;

/**
 * TypeFactory
 *
 * @version 0.1
 * @author group17
 */
public class TypeFactory {
	/**
	 * Create a TypeFactory
	 */
	public TypeFactory() {
		super();
	}

	/**
	 * Create a CoolGameWorldSnapshot with the given state.
	 * 
	 * @param elements The given state of the CoolGameWorld to create a snapshot
	 *                 of.
	 * @return the CoolGameWorldSnapshot associated with the given state.
	 */
	public CoolGameWorldSnapshot createSnapshot(Set<Element> elements) {
		return new CoolGameWorldSnapshot(elements);
	}

	/**
	 * Create a CoolGameWorldType.
	 * 
	 * @return a CoolGameWorldType
	 */
	public CoolGameWorldType createType() {
		return new CoolGameWorldType();
	}
	
	/**
	 * Create a CoolGameWorldAction
	 * 
	 * @param action The supportedAction for this CoolGameWorldAction
	 * @return a CoolGameWorldAction with the given SupportedAction
	 */
	public CoolGameWorldAction createAction(SupportedActions action) {
		return new CoolGameWorldAction(action);
	}
	
	/**
	 * Create a CoolGameWorldPredicate
	 * @param predicate The SupportedPredicate for this BoatGameWolrdPredicate
	 * @return a CoolGameWorldPredicate with the given SupportedPredicate
	 */
	public CoolGameWorldPredicate createPredicate(SupportedPredicates predicate) {
		return new CoolGameWorldPredicate(predicate);
	}
	
	
	
	/**
	 * Create a new Coordinate with the given x and y values.
	 * @param x the X value for the coordinate.
	 * @param y the Y value for the coordinate.
	 * @return A coordinate with the given x and y values.
	 */
	public Coordinate createCoordinate(int x,int y) {
		return new Coordinate(x, y);
	}
	
	
}
