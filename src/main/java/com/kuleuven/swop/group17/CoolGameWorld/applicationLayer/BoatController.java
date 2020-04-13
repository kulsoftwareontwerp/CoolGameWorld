package com.kuleuven.swop.group17.CoolGameWorld.applicationLayer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Boat;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.SolidElement;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.IceBerg;
import com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.CoolGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.CoolGameWorld.events.GUISubject;
import com.kuleuven.swop.group17.CoolGameWorld.events.BoatAddedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.BoatChangedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;

/**
 * The BoatController handles all requests regarding the management of a boat.
 * 
 * @version 0.1
 * @author group17
 */
public class BoatController implements GUISubject {

	private Collection<GUIListener> guiListeners;
	private ElementRepository elementRepository;
	private EventFactory eventFactory;

	BoatController(ElementRepository elementRepository) {
		if (elementRepository == null) {
			throw new NullPointerException("The given elementRepository can't be null.");
		}

		createBoatController(new EventFactory(), elementRepository, new HashSet<GUIListener>());

	}

	@SuppressWarnings("unused")
	private BoatController(ElementRepository elementRepository, Collection<GUIListener> guiListeners,
			EventFactory eventFactory) {
		createBoatController(eventFactory, elementRepository, guiListeners);
	}

	private void createBoatController(EventFactory eventFactory, ElementRepository elementRepository,
			Collection<GUIListener> guiListeners) {
		this.eventFactory = eventFactory;
		this.elementRepository = elementRepository;
		this.guiListeners = guiListeners;
	}

	private void fireBoatAddedEvent(Coordinate coordinate, Orientation orientation) {
		BoatAddedEvent event = eventFactory.createBoatAddedEvent(coordinate, orientation);

		for (GUIListener listener : guiListeners) {
			listener.onBoatAddedEvent(event);
		}
	}

	private void fireBoatChangeEvent() {
		Boat boat = getBoat();
		BoatChangedEvent event = eventFactory.createBoatChangedEvent(boat.getCoordinate(), boat.getOrientation());

		for (GUIListener listener : guiListeners) {
			listener.onBoatChangeEvent(event);
		}
	}

	/**
	 * Turn the boat left
	 * 
	 * @event BoatChangeEvent If the operation was successful.
	 */
	void turnLeft() {
		Boat boat = getBoat();
		Orientation currentOrientation = boat.getOrientation();
		Orientation newOrientation = currentOrientation.getLeft();
		boat.setOrientation(newOrientation);
		fireBoatChangeEvent();
	}

	/**
	 * Turn the boat right
	 * 
	 * @event BoatChangeEvent If the operation was successful.
	 */
	void turnRight() {
		Boat boat = getBoat();
		Orientation currentOrientation = boat.getOrientation();
		Orientation newOrientation = currentOrientation.getRight();
		boat.setOrientation(newOrientation);
		fireBoatChangeEvent();
	}

	/**
	 * Move the boat forward
	 * 
	 * @event BoatChangeEvent If the operation was successful.
	 */
	void moveForward() {

		Coordinate cifor = getCoordinatesInFrontOfBoat();
		Set<Element> elements = elementRepository.getElements(cifor);

		// Check if the boat stays within the boundries of the Game Area, else do
		// nothing
		if (cifor.getX() < 0 || cifor.getX() > elementRepository.getGameAreaWidth() - 1 || cifor.getY() < 0
				|| cifor.getY() > elementRepository.getGameAreaHeight() - 1) {
			return;
		}

		Iterator<Element> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			// If another solid element is already in the new spot of the boat, the
			// position of the boat stays the same.
			if (element instanceof SolidElement) {
				return;
			}
		}

		// If no solid objects are found on the new position, we move the boat into
		// that position.
		getBoat().setCoordinate(cifor);

		fireBoatChangeEvent();

	}

	/**
	 * Add a boat to the CoolGameWorld.
	 * 
	 * @param coordinate  The coordinate for the Boat.
	 * @param orientation The orientation for the Boat.
	 * @event BoatAddedEvent When the operation is successful and elementType is
	 *        boat the BoatAddedEvent will be thrown to all the listeners.
	 * @throws NullPointerException When the given Orientation or the given
	 *                              Coordinate is null.
	 * 
	 */
	void addBoat(Coordinate coordinate, Orientation orientation){
		if (orientation == null) {
			throw new NullPointerException("The given Orientation can't be null.");
		}
		if (coordinate == null) {
			throw new NullPointerException("The given Coordinate can't be null.");
		}
		elementRepository.addElement(ElementType.BOAT, coordinate);
		getBoat().setOrientation(orientation);
		fireBoatAddedEvent(coordinate, orientation);
	}

	/**
	 * Check if there is a wall in front of the boat.
	 * 
	 * @return True if there is a wall in front of the boat, false otherwise.
	 */
	Boolean checkIfWallInFront() {
		Coordinate cifor = getCoordinatesInFrontOfBoat();
		Set<Element> elements = elementRepository.getElements(cifor);

		Iterator<Element> iterator = elements.iterator();
		while (iterator.hasNext()) {
			Element element = iterator.next();
			if (element instanceof IceBerg) {
				return true;
			}
		}
		return false;
	}

	private Boat getBoat() throws NoSuchElementException {

		Optional<Boat> boat = elementRepository.getElementByType(ElementType.BOAT).stream().map(s -> (Boat) s).findFirst();
		if (boat.isPresent()) {
			return boat.get();
		} else {
			throw new NoSuchElementException("No boat has been added to the CoolGameWorld.");
		}
	}

	private Coordinate getCoordinatesInFrontOfBoat() {
		Boat boat = getBoat();
		Orientation currentBoatOrientation = boat.getOrientation();

		Coordinate rc = boat.getCoordinate();
		Coordinate toReturn = rc;

		switch (currentBoatOrientation) {
		case UP:
			toReturn = rc.setY(rc.getY() - 1);
			break;
		case DOWN:
			toReturn = rc.setY(rc.getY() + 1);
			break;
		case LEFT:
			toReturn = rc.setX(rc.getX() - 1);
			break;
		case RIGHT:
			toReturn = rc.setX(rc.getX() + 1);
			break;
		}
		return toReturn;
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
