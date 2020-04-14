package com.kuleuven.swop.group17.CoolGameWorld.applicationLayer;

import java.awt.Graphics;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.DomainFactory;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Boat;
import com.kuleuven.swop.group17.CoolGameWorld.guiLayer.GuiFactory;
import com.kuleuven.swop.group17.CoolGameWorld.guiLayer.BoatCanvas;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;
import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorldAction;
import com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorldPredicate;
import com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorldSnapshot;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;

/**
 * A GameWorld is described by a GameWorldType. GameWorlds are able to perform
 * Actions, evaluate predicates, save and restore their state and draw
 * themselves on a given graphics object.
 * 
 * @version 0.1
 * @author group17
 */
public class CoolGameWorld implements GameWorld {
	private BoatController boatController;
	private ElementController elementController;
	private BoatCanvas boatCanvas;
	private TypeFactory typeFactory;

	/**
	 * Create a CoolGameWorld
	 */
	public CoolGameWorld() {
		super();
		DomainFactory domainFactory = new DomainFactory();
		ElementRepository elementRepository = domainFactory.createElementRepository();

		BoatController boatController = new BoatController(elementRepository);
		ElementController elementController = new ElementController(elementRepository);
		createCoolGameWorld(boatController, elementController, new TypeFactory(),
				(new GuiFactory()).createBoatCanvas());

	}

	// This private constructor is called using reflection by CoolGameWorldTest to
	// initialize the fields with mocks.
	@SuppressWarnings("unused")
	private CoolGameWorld(BoatController boatController, ElementController elementController,
			TypeFactory typeFactory, BoatCanvas boatCanvas) {
		super();
		createCoolGameWorld(boatController, elementController, typeFactory, boatCanvas);
	}

	private void createCoolGameWorld(BoatController boatController, ElementController elementController,
			TypeFactory typeFactory, BoatCanvas boatCanvas) {

		this.typeFactory = typeFactory;

		this.boatCanvas = boatCanvas;

		this.elementController = elementController;
		this.boatController = boatController;

		this.boatController.addListener(boatCanvas);
		this.elementController.addListener(boatCanvas);

		initializeCoolGameWorld();
	}

	private void initializeCoolGameWorld() {
		boatController.addBoat(typeFactory.createCoordinate(2, 3), BoatState.FLOATING);
		elementController.addElement(ElementType.ICEBERG, typeFactory.createCoordinate(0, 0));
		elementController.addElement(ElementType.ICEBERG, typeFactory.createCoordinate(4, 0));
		elementController.addElement(ElementType.ICEBERG, typeFactory.createCoordinate(1, 2));
		elementController.addElement(ElementType.ICEBERG, typeFactory.createCoordinate(2, 2));
		elementController.addElement(ElementType.ICEBERG, typeFactory.createCoordinate(3, 2));
		elementController.addElement(ElementType.GOAL, typeFactory.createCoordinate(2, 1));
	}

	/**
	 * Perform the given action on the gameWorld.
	 * 
	 * @param action The action that should be performed.
	 * @throws UnsupportedOperationException when an action was given that's not
	 *                                       listed in the supportedActions of the
	 *                                       corresponding gameWorldType of this
	 *                                       gameWorld.
	 * @throws NullPointerException          when the given action is null.
	 * @throws RuntimeException              when something went wrong in the
	 *                                       execution of the action.
	 */
	public void performAction(Action action)
			throws UnsupportedOperationException, NullPointerException, RuntimeException {
		if (action == null) {
			throw new NullPointerException("The given action can't be null");

		}
		if (!(action instanceof CoolGameWorldAction)) {
			throw new UnsupportedOperationException("The given action is not a supported action for a CoolGameWorld.");
		}
		try {
			CoolGameWorldAction boatAction = (CoolGameWorldAction) action;
			switch (boatAction.getAction()) {
			case MOVEFORWARD:
				boatController.moveForward();
				break;
			case TURNLEFT:
				boatController.turnLeft();
				break;
			case TURNRIGHT:
				boatController.turnRight();
				break;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Evaluates the given predicate on the gameWorld.
	 * 
	 * @param predicate The predicate that should be evaluated.
	 * @throws UnsupportedOperationException when a predicate was given that's not
	 *                                       listed in the supportedPredicates of
	 *                                       the corresponding gameWorldType of this
	 *                                       gameWorld.
	 * @throws NullPointerException          when the given predicate is null.
	 * @throws RuntimeException              when something went wrong in the
	 *                                       evaluation of the predicate.
	 * @return the evaluation of the given predicate.
	 */
	public Boolean evaluate(Predicate predicate)
			throws UnsupportedOperationException, NullPointerException, RuntimeException {
		if (predicate == null) {
			throw new NullPointerException("The given predicate can't be null");
		}

		if (!(predicate instanceof CoolGameWorldPredicate)) {
			throw new UnsupportedOperationException(
					"The given predicate is not a supported predicate for a CoolGameWorld.");
		}
		try {
			CoolGameWorldPredicate boatPredicate = (CoolGameWorldPredicate) predicate;

			boolean evaluation = false;

			switch (boatPredicate.getPredicate()) {
			case ICEBERGINFRONT:
				evaluation = boatController.checkIfIceBergInFront();
				break;
			}

			return evaluation;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Saves the current state of the gameWorld.
	 * 
	 * @return a non inspectable snapshot with current state of the gameWorld
	 */
	public GameWorldSnapshot saveState() {
		return typeFactory.createSnapshot(elementController.getElements());
	}

	/**
	 * Set the state of the gameWorld to the given gameWorldState
	 * 
	 * @param state the state to which the gameWorld should be set.
	 * @throws IllegalArgumentException when the given state is not a state of this
	 *                                  gameWorld.
	 * @throws NullPointerException     when the given GameWorlSnapshot is null.
	 */
	public void restoreState(GameWorldSnapshot state) throws IllegalArgumentException {
		if (state == null) {
			throw new NullPointerException("The given GameWorldSnapshot can't be null");
		}
		if (!(state instanceof CoolGameWorldSnapshot)) {
			throw new IllegalArgumentException(
					"The given GameWorldSnapshot is not a valid snapshot for a CoolGameWorld.");
		}
		elementController.clearElements();
		for (Element element : ((CoolGameWorldSnapshot) state).getElements()) {
			if (element.getType() == ElementType.BOAT) {
				boatController.addBoat(element.getCoordinate(),((Boat) element).getBoatState());
			} else {
				elementController.addElement(element.getType(), element.getCoordinate());
			}
		}
	}

	/**
	 * Paint the gameWorld on the given graphics object.
	 * 
	 * @param graphics the graphics object on which the gameWorld should be painted.
	 * @throws NullPointerException when the given graphics object is null.
	 * @throws RuntimeException     when something went wrong during painting
	 */
	public void paint(Graphics graphics) throws NullPointerException, RuntimeException {
		if (graphics == null) {
			throw new NullPointerException("The given Graphics can't be null");
		}
		try {
			boatCanvas.paint(graphics);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieve the type corresponding to the CoolGameWorld.
	 * 
	 * @return the type corresponding to the CoolGameWorld
	 */
	public GameWorldType getType() {
		return typeFactory.createType();
	}

}
