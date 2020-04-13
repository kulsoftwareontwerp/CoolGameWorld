package com.kuleuven.swop.group17.CoolGameWorld.applicationLayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.CoolGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.CoolGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.CoolGameWorld.events.GUISubject;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;

/**
 * The ElementController handles all requests regarding the management of
 * elements.
 * 
 * @version 0.1
 * @author group17
 */
public class ElementController implements GUISubject {

	private Collection<GUIListener> guiListeners;
	private ElementRepository elementRepository;
	private EventFactory eventFactory;

	/**
	 * Create an ElementController
	 * 
	 * @param elementRepository The elementrepository associated with this
	 *                          controller.
	 * @throws NullPointerException When the given ElementRepository is null.
	 */
	ElementController(ElementRepository elementRepository) {
		if (elementRepository == null) {
			throw new NullPointerException("The given elementRepository can't be null.");
		}

		createElementController(new EventFactory(), elementRepository,  new HashSet<GUIListener>());
		

	}

	@SuppressWarnings("unused")
	private ElementController(ElementRepository elementRepository, Collection<GUIListener> guiListeners,EventFactory eventFactory) {
		createElementController(eventFactory, elementRepository, guiListeners);
	}
	
	private void createElementController(EventFactory eventFactory,ElementRepository elementRepository, Collection<GUIListener> guiListeners) {
		this.eventFactory=eventFactory;
		this.elementRepository = elementRepository;
		this.guiListeners = guiListeners;
	}

	private void fireElementAddedEvent(ElementType element, Coordinate coordinate) {
		ElementAddedEvent event = eventFactory.createElementAddedEvent(coordinate, element);
		for (GUIListener listener : guiListeners) {
			listener.onElementAddedEvent(event);
		}
	}

	private void fireElementClearedEvent() {
		ElementsClearedEvent event = eventFactory.createElementsClearedEvent();
		for (GUIListener listener : guiListeners) {
			listener.onElementsClearedEvent(event);
		}
	}

	/**
	 * Remove all elements in the gameWorld
	 * 
	 * @event ElementsClearedEvent When the elements have been removed from the
	 *        domain.
	 */
	void clearElements() {
		elementRepository.clearElements();
		fireElementClearedEvent();
	}

	/**
	 * Add an element to the CoolGameWorld.
	 * 
	 * @param element    The type of element.
	 * @param coordinate The coordinate of the element.
	 * @event ElementAddedEvent When the operation is successful ElementAddedEvent
	 *        will be thrown to all the listeners.
	 * @throws NullPointerException When the given ElementType or the given
	 *                              Coordinate is null.
	 * 
	 */
	void addElement(ElementType element, Coordinate coordinate) {
		if (element == null) {
			throw new NullPointerException("The given ElementType can't be null.");
		}
		if (coordinate == null) {
			throw new NullPointerException("The given Coordinate can't be null.");
		}
		elementRepository.addElement(element, coordinate);

		fireElementAddedEvent(element, coordinate);
	}

	/**
	 * Retrieve all elements in the CoolGameWorld.
	 * 
	 * @return all elements in the CoolGameWorld.
	 */
	public Set<Element> getElements() {
		return elementRepository.getElements();
	}

	@Override
	public void removeListener(GUIListener listener) {
		if (listener == null) {
			throw new NullPointerException("The given GUIListener can't be null.");
		}
		guiListeners.remove(listener);

	}

	@Override
	public void addListener(GUIListener listener) {
		if (listener == null) {
			throw new NullPointerException("The given GUIListener can't be null.");
		}
		guiListeners.add(listener);
	}

}
