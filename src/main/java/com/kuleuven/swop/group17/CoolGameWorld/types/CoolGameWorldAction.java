package com.kuleuven.swop.group17.CoolGameWorld.types;

import com.kuleuven.swop.group17.GameWorldApi.Action;


/**
 * CoolGameWorldActions are used to specify what the CoolGameWorld can do.
 * 
 * @version 0.1
 * @author group17
 */
public class CoolGameWorldAction implements Action {
	private SupportedActions action;

	/**
	 * Create a CoolGameWorldAction
	 * @param action the SupportedAction associated with this CoolGameWorldAction. 
	 */
	CoolGameWorldAction(SupportedActions action) {
		super();
		if(action==null) {
			throw new NullPointerException("The given action can't be null");
		}
		this.action = action;
	}
	
	
	
	/**
	 * Retrieve the SupportedAction associated with this CoolGameWorldAction.
	 * @return the SupportedAction associated with this CoolGameWorldAction
	 */
	public SupportedActions getAction() {
		return action;
	}




	@Override
	public String toString() {
		return action.toString();
	}
	
	

}
