package com.kuleuven.swop.group17.CoolGameWorld.applicationLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Goal;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Boat;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.IceBerg;
import com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.CoolGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.CoolGameWorld.events.BoatAddedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.BoatChangedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.SupportedActions;
import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;

/**
 * ElementControllerTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class BoatControllerTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private GUIListener listener;
	@Spy
	private HashSet<GUIListener> guiListeners;
	@Mock
	private ElementRepository elementRepository;
	@Mock
	private EventFactory eventFactory;
	@Mock
	private Boat boat;

	@Captor
	private ArgumentCaptor<Coordinate> coordinateCaptor;
	@Captor
	private ArgumentCaptor<BoatState> boatStateCaptor;
	@Captor
	private ArgumentCaptor<ElementType> elementTypeCaptor;
	@InjectMocks
	private BoatController controller;

	@Before
	public void setUp() throws Exception {
		TypeFactory tf = new TypeFactory();

		guiListeners.clear();
		guiListeners.add(listener);

		when(elementRepository.getElementByType(ElementType.BOAT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> boats = new HashSet<Element>();
				boats.add(boat);
				return boats;
			}

		});

		when(elementRepository.getGameAreaHeight()).thenReturn(11);
		when(elementRepository.getGameAreaWidth()).thenReturn(5);
		
		
		
		
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(tf.createCoordinate(1, 1));

		when(eventFactory.createBoatAddedEvent(coordinateCaptor.capture(), boatStateCaptor.capture()))
				.thenReturn(mock(BoatAddedEvent.class));
		when(eventFactory.createBoatChangedEvent(coordinateCaptor.capture(), boatStateCaptor.capture()))
				.thenReturn(mock(BoatChangedEvent.class));
		
		
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBoatController() {
		BoatController newController = new BoatController(elementRepository);
		try {
			Field f = BoatController.class.getDeclaredField("eventFactory");
			f.setAccessible(true);
			assertTrue("eventFactory was not initialised", f.get(newController) != null);
			f = BoatController.class.getDeclaredField("guiListeners");
			f.setAccessible(true);
			assertTrue("guiListeners was not initialised", f.get(newController) != null);
			f = BoatController.class.getDeclaredField("elementRepository");
			f.setAccessible(true);
			assertTrue("elementRepository was not initialised", f.get(newController) != null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	@Test
	public void testBoatControllerNullElementRepository() {
		String excMessage = "The given elementRepository can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		new BoatController(null);
	}

	@Test
	public void testTurnLeftNoBoat() {
		String excMessage = "No boat has been added to the CoolGameWorld.";


		when(elementRepository.getElementByType(ElementType.BOAT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> boats = new HashSet<Element>();
				return boats;
			}

		});
		
		boolean succes = false;
		try {
			controller.turnLeft();
		}catch(NoSuchElementException e){
			assertEquals(excMessage,e.getMessage());
			verify(boat, times(0)).getBoatState();
			verify(boat, times(0)).getCoordinate();
			verify(boat, times(0)).setBoatState(any());
			verify(boat, times(0)).setCoordinate(any());
			succes = true;
		}
		assertTrue(succes);
	}

	@Test
	public void testTurnLeft() {
		controller.turnLeft();
		Coordinate c = boat.getCoordinate();
		verify(boat).setCoordinate(coordinateCaptor.capture());
		c = c.setX(c.getX()-1);
		c = c.setY(c.getY()-1);
		
		assertEquals(c, coordinateCaptor.getValue());
	}

	@Test
	public void testTurnRightNoBoat() {
		String excMessage = "No boat has been added to the CoolGameWorld.";

		when(elementRepository.getElementByType(ElementType.BOAT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> boats = new HashSet<Element>();
				return boats;
			}

		});
		boolean succes = false;
		try {
			controller.turnRight();
		}catch(NoSuchElementException e){
			assertEquals(excMessage,e.getMessage());
			verify(boat, times(0)).getBoatState();
			verify(boat, times(0)).getCoordinate();
			verify(boat, times(0)).setBoatState(any());
			verify(boat, times(0)).setCoordinate(any());
			succes = true;
		}
		assertTrue(succes);

		

	}

	@Test
	public void testTurnRight() {
		controller.turnRight();
		Coordinate c = boat.getCoordinate();
		verify(boat).setCoordinate(coordinateCaptor.capture());
		c = c.setX(c.getX()+1);
		c = c.setY(c.getY()-1);
		
		assertEquals(c, coordinateCaptor.getValue());
	}

	@Test
	public void testMoveForwardNoBoat() {
		String excMessage = "No boat has been added to the CoolGameWorld.";

		when(elementRepository.getElementByType(ElementType.BOAT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> boats = new HashSet<Element>();
				return boats;
			}

		});

		boolean succes = false;
		try {
			controller.moveForward();
		}catch(NoSuchElementException e){
			assertEquals(excMessage,e.getMessage());
			verify(boat, times(0)).getBoatState();
			verify(boat, times(0)).getCoordinate();
			verify(boat, times(0)).setBoatState(any());
			verify(boat, times(0)).setCoordinate(any());
			succes = true;
		}
		assertTrue(succes);

	}
	
	private void moveChecksBoundary(SupportedActions action, int expectedX, int expectedY, int times) {
		TypeFactory tf = new TypeFactory();
		Coordinate expectedC = tf.createCoordinate(expectedX, expectedY);

		switch(action) {
		case MOVEFORWARD:
			controller.moveForward();
			break;
		case TURNLEFT:
			controller.turnLeft();
			break;
		case TURNRIGHT:
			controller.turnRight();
			break;
			
		}

		verify(boat, atLeast(1)).getBoatState();
		verify(boat, atLeast(1)).getCoordinate();

		verify(boat, times(0)).setBoatState(any(BoatState.class));

		if (times > 0) {
			verify(boat, times(times)).setCoordinate(coordinateCaptor.capture());
			assertEquals(expectedC, coordinateCaptor.getValue());
		}
		else {
			verify(boat, times(times)).setCoordinate(any(Coordinate.class));
			
		}
	}
	
	private void moveChecksIceBerg(SupportedActions action, int expectedX, int expectedY, int times) {
		TypeFactory tf = new TypeFactory();
		Coordinate expectedC = tf.createCoordinate(expectedX, expectedY);

		switch(action) {
		case MOVEFORWARD:
			controller.moveForward();
			break;
		case TURNLEFT:
			controller.turnLeft();
			break;
		case TURNRIGHT:
			controller.turnRight();
			break;
			
		}

		verify(boat, atLeast(1)).getBoatState();
		verify(boat, atLeast(1)).getCoordinate();

		verify(boat, times(1)).setBoatState(BoatState.CRASHED);

		if (times > 0) {
			verify(boat, times(times)).setCoordinate(coordinateCaptor.capture());
			assertEquals(expectedC, coordinateCaptor.getValue());
		}
		else {
			verify(boat, times(times)).setCoordinate(any(Coordinate.class));
			
		}
	}

	private void moveChecksGoal(SupportedActions action, int expectedX, int expectedY, int times) {
		TypeFactory tf = new TypeFactory();
		Coordinate expectedC = tf.createCoordinate(expectedX, expectedY);

		switch(action) {
		case MOVEFORWARD:
			controller.moveForward();
			break;
		case TURNLEFT:
			controller.turnLeft();
			break;
		case TURNRIGHT:
			controller.turnRight();
			break;
			
		}

		verify(boat, atLeast(1)).getBoatState();
		verify(boat, atLeast(1)).getCoordinate();

		verify(boat, times(1)).setBoatState(BoatState.ARRIVED);

		if (times > 0) {
			verify(boat, times(times)).setCoordinate(coordinateCaptor.capture());
			assertEquals(expectedC, coordinateCaptor.getValue());
		}
		else {
			verify(boat, times(times)).setCoordinate(any(Coordinate.class));
			
		}
	}
	
	
	
	@Test
	public void testMoveForwardBoatStateCrashedMoveForward() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, 0);
		when(boat.getBoatState()).thenReturn(BoatState.CRASHED);
		when(boat.getCoordinate()).thenReturn(rc);

		
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveChecksBoundary(SupportedActions.MOVEFORWARD , rc.getX(), rc.getY(), 1);
	}
	
	@Test
	public void testMoveForwardWithBoundaryInFrontFloatingMoveForward() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, 0);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);

		
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveChecksBoundary(SupportedActions.MOVEFORWARD , rc.getX(), rc.getY(), 0);
	}

	@Test
	public void testMoveForwardWithBoundaryInFrontFloatingTurnLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, 0);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveChecksBoundary(SupportedActions.TURNLEFT, rc.getX(), rc.getY(), 0);
	}

	@Test
	public void testMoveForwardWithBoundaryInFrontFloatingTurnRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(4, 0);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});
		
		moveChecksBoundary(SupportedActions.TURNRIGHT , rc.getX(), rc.getY(), 0);
	}

	@Test
	public void testMoveForwardWithSideBoundaryFloatingTurnLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0,3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveChecksBoundary(SupportedActions.TURNLEFT, elementRepository.getGameAreaWidth()-1, rc.getY()-1, 1);
	}
	
	@Test
	public void testMoveForwardWithSideBoundaryFloatingTurnRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(4,3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveChecksBoundary(SupportedActions.TURNRIGHT, 0, rc.getY()-1, 1);
	}
	
	
	
	

	@Test
	public void testMoveForwardWithIceBergInFrontFloatingMoveForward() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});
		moveChecksIceBerg(SupportedActions.MOVEFORWARD, rc.getX(), rc.getY()-1, 1);

	}

	@Test
	public void testMoveForwardWithGoalInFrontFloatingMoveForward() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});
		moveChecksGoal(SupportedActions.MOVEFORWARD , rc.getX(), rc.getY() - 1, 1);

	}

	@Test
	public void testMoveForwardWithIceBergInFrontFloatingTurnLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()-1) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});

		moveChecksIceBerg(SupportedActions.TURNLEFT , rc.getX() - 1, rc.getY() - 1, 1);
	}

	@Test
	public void testMoveForwardWithGoalInFrontFloatingTurnLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()-1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		moveChecksGoal(SupportedActions.TURNLEFT ,rc.getX()-1, rc.getY()-1 ,1);
	}

	@Test
	public void testMoveForwardWithIceBergInFrontFloatingTurnRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY() - 1) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});
		moveChecksIceBerg(SupportedActions.TURNRIGHT, rc.getX()+1, rc.getY()-1, 1);
	}

	@Test
	public void testMoveForwardWithGoalInFrontTurnRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY() - 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});
		moveChecksGoal(SupportedActions.TURNRIGHT,rc.getX()+1, rc.getY() - 1, 1);
	}


	@Test
	public void testAddBoat() {
		TypeFactory tf = new TypeFactory();
		BoatState boatState = BoatState.FLOATING;
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addBoat(coordinate, boatState);

		verify(elementRepository).addElement(elementTypeCaptor.capture(), coordinateCaptor.capture());
		assertEquals(ElementType.BOAT, elementTypeCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());

		verify(boat).setBoatState(boatStateCaptor.capture());
		assertEquals(boatState, boatStateCaptor.getValue());

		verify(eventFactory).createBoatAddedEvent(coordinateCaptor.capture(), boatStateCaptor.capture());
		assertEquals(boatState, boatStateCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());

		verify(listener).onBoatAddedEvent(any(BoatAddedEvent.class));
	}

	@Test
	public void testAddBoatNullBoatState() {
		String excMessage = "The given BoatState can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		TypeFactory tf = new TypeFactory();
		BoatState boatState = null;
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addBoat(coordinate, boatState);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(boat, times(0)).setBoatState(any(BoatState.class));

		verify(eventFactory, times(0)).createBoatAddedEvent(any(Coordinate.class), any(BoatState.class));

		verify(listener, times(0)).onBoatAddedEvent(any(BoatAddedEvent.class));

	}

	@Test
	public void testAddBoatNullCoordinate() {
		String excMessage = "The given Coordinate can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		BoatState boatState = BoatState.FLOATING;
		Coordinate coordinate = null;
		controller.addBoat(coordinate, boatState);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(boat, times(0)).setBoatState(any(BoatState.class));

		verify(eventFactory, times(0)).createBoatAddedEvent(any(Coordinate.class), any(BoatState.class));

		verify(listener, times(0)).onBoatAddedEvent(any(BoatAddedEvent.class));

	}

	@Test
	public void testCheckIfIceBergInFrontNoBoat() {
		String excMessage = "No boat has been added to the CoolGameWorld.";
		exceptionRule.expect(NoSuchElementException.class);
		exceptionRule.expectMessage(excMessage);

		when(elementRepository.getElementByType(ElementType.BOAT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> boats = new HashSet<Element>();
				return boats;
			}

		});

		controller.checkIfIceBergInFront();

		verify(boat, times(0)).getBoatState();
		verify(boat, times(0)).getCoordinate();

	}

	@Test
	public void testCheckIfIceBergInFrontWithIceBergInFront() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});

		checkIfIceBergInFrontChecks(true, 1);

	}

	@Test
	public void testCheckIfIceBergInFrontWithoutIceBergInFront() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getBoatState()).thenReturn(BoatState.FLOATING);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() - 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		checkIfIceBergInFrontChecks(false, 1);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfIceBergInFrontChecks(false, 2);

	}

	private void checkIfIceBergInFrontChecks(Boolean valid, int times) {
		Boolean result = controller.checkIfIceBergInFront();

		verify(boat, times(times)).getBoatState();
		verify(boat, times(times)).getCoordinate();

		assertEquals(valid, result);
	}

	@Captor
	private ArgumentCaptor<GUIListener> listenerCaptor;

	@Test
	public void testRemoveListener() {
		GUIListener listenerToRemove = mock(GUIListener.class);
		guiListeners.add(listenerToRemove);
		assertEquals(2, guiListeners.size());

		controller.removeListener(listenerToRemove);
		verify(guiListeners).remove(listenerCaptor.capture());

		assertEquals(listenerToRemove, listenerCaptor.getValue());
		assertEquals(1, guiListeners.size());

	}

	@Test
	public void testRemoveListenerNullListener() {
		String excMessage = "The given GUIListener can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		GUIListener listenerToRemove = mock(GUIListener.class);
		guiListeners.add(listenerToRemove);
		assertEquals(2, guiListeners.size());

		controller.removeListener(null);
		verify(guiListeners, times(0)).remove(any(GUIListener.class));

		assertEquals(2, guiListeners.size());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddListener() {
		reset(guiListeners);

		GUIListener listenerToAdd = mock(GUIListener.class);
		assertEquals(1, guiListeners.size());

		controller.addListener(listenerToAdd);
		verify(guiListeners).add(listenerCaptor.capture());

		assertEquals(listenerToAdd, listenerCaptor.getValue());
		assertEquals(2, guiListeners.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddListenerNullListener() {
		reset(guiListeners);
		String excMessage = "The given GUIListener can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		GUIListener listenerToAdd = null;
		assertEquals(1, guiListeners.size());

		controller.addListener(listenerToAdd);
		verify(guiListeners, times(0)).add(any(GUIListener.class));

		assertEquals(1, guiListeners.size());
	}

}
