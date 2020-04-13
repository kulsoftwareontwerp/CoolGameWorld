package com.kuleuven.swop.group17.CoolGameWorld.types;

import java.util.HashSet;
import java.util.Set;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;

/**
 * The CoolGameWorldType defines the characteristics of the CoolGameWorld.
 * 
 * @version 0.1
 * @author group17
 */
public class CoolGameWorldType implements GameWorldType {
	
	
	/**
	 * Create a boatGameWorldType
	 */
	CoolGameWorldType() {
		super();
	}

	/**
	 * Retrieve all the supported actions for the CoolGameWorld.
	 * @return A set containing all the actions in the boatGameWorld.
	 */
	public Set<Action> supportedActions() {
		Set<Action> actions = new HashSet<Action>();
		for (SupportedActions action :  SupportedActions.values()) {
			actions.add(new CoolGameWorldAction(action));		
		}
		return actions;
	}

	
	/**
	 * Retrieve all the supported predicates for the CoolGameWorld.
	 * @return A set containing all the predicates in the boatGameWorld.
	 */
	public Set<Predicate> supportedPredicates() {
		Set<Predicate> predicates = new HashSet<Predicate>();
		for (SupportedPredicates predicate :  SupportedPredicates.values()) {
			predicates.add(new CoolGameWorldPredicate(predicate));		
		}
		return predicates;
	}

}
