package com.kuleuven.swop.group17.CoolGameWorld.events;

/**
 * A GUISubject maintains it's list of GUIListeners and fires the DomainEvents.
 * 
 * @version 0.1
 * @author group17
 */
public interface GUISubject {

	/**
	 * Add a GUIListener
	 * 
	 * @param listener The listener to be added.
	 * @throws NullPointerException When the given GUIListener is null.
	 * 
	 */
	void addListener(GUIListener listener);

	/**
	 * Remove a GUIListener
	 * 
	 * @param listener The listener to be removed.
	 * @throws NullPointerException When the given GUIListener is null.
	 */
	void removeListener(GUIListener listener);

}