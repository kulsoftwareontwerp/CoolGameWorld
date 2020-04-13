package com.kuleuven.swop.group17.CoolGameWorld.events;

/**
 * A GUIlistener resides in the GUI layer and updates the GUI according to events emerging from the domain.
 * 
 * @version 0.1
 * @author group17
 */
public interface GUIListener {

	
	/**
	 * Perform the needed actions in the GUI after the boat changed in the domain.
	 * @param 	event
	 * 			A BoatChangeEvent
	 */
	void onBoatChangeEvent(BoatChangedEvent event);

	/**
	 * Perform the needed actions in the GUI after a boat has been added to the domain.
	 * @param 	event
	 * 			A BoatAddedEvent
	 */
	void onBoatAddedEvent(BoatAddedEvent event);

	/**
	 * Perform the needed actions in the GUI after an element has been added to the domain.
	 * @param 	event
	 * 			A ElementAddedEvent
	 */
	void onElementAddedEvent(ElementAddedEvent event);
	
	
	/**
	 * Perform the needed actions in the GUI after all elements are removed from the domain.
	 * @param 	event
	 * 			A ElementsClearedEvent
	 */
	void onElementsClearedEvent(ElementsClearedEvent event);

}