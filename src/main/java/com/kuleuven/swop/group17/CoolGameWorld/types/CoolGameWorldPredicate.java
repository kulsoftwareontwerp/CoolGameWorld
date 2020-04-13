package com.kuleuven.swop.group17.CoolGameWorld.types;

import com.kuleuven.swop.group17.GameWorldApi.Predicate;

public class CoolGameWorldPredicate implements Predicate {
	SupportedPredicates predicate;

	/**
	 * Create a CoolGameWorldPredicate
	 * @param predicate the SupportedPredicate associated with this CoolGameWorldPredicate. 
	 */
	CoolGameWorldPredicate(SupportedPredicates predicate) {
		super();
		if(predicate==null) {
			throw new NullPointerException("The given predicate can't be null");
		}
		this.predicate = predicate;
	}

	/**
	 * Retrieve the SupportedPredicates associated with this CoolGameWorldPredicate.
	 * 
	 * @return the SupportedPredicate associated with this CoolGameWorldPredicate
	 */
	public SupportedPredicates getPredicate() {
		return predicate;
	}

	@Override
	public String toString() {
		return predicate.toString();
	}

}
