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
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;
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
	private ArgumentCaptor<Orientation> orientationCaptor;
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

		when(elementRepository.getGameAreaHeight()).thenReturn(5);
		when(elementRepository.getGameAreaWidth()).thenReturn(5);
		
		
		
		
		when(boat.getOrientation()).thenReturn(Orientation.UP);
		when(boat.getCoordinate()).thenReturn(tf.createCoordinate(1, 1));

		when(eventFactory.createBoatAddedEvent(coordinateCaptor.capture(), orientationCaptor.capture()))
				.thenReturn(mock(BoatAddedEvent.class));
		when(eventFactory.createBoatChangedEvent(coordinateCaptor.capture(), orientationCaptor.capture()))
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
		exceptionRule.expect(NoSuchElementException.class);
		exceptionRule.expectMessage(excMessage);

		when(elementRepository.getElementByType(ElementType.BOAT)).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> boats = new HashSet<Element>();
				return boats;
			}

		});

		controller.turnLeft();

		verify(boat, times(0)).getOrientation();
		verify(boat, times(0)).getCoordinate();
		verify(boat, times(0)).setOrientation(any());
		verify(boat, times(0)).setCoordinate(any());

	}

	@Test
	public void testTurnLeft() {
		controller.turnLeft();
		Orientation o = boat.getOrientation();
		verify(boat).setOrientation(orientationCaptor.capture());

		assertEquals(o.getLeft(), orientationCaptor.getValue());

	}

	@Test
	public void testTurnRightNoBoat() {
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

		controller.turnRight();

		verify(boat, times(0)).getOrientation();
		verify(boat, times(0)).getCoordinate();
		verify(boat, times(0)).setOrientation(any());
		verify(boat, times(0)).setCoordinate(any());

	}

	@Test
	public void testTurnRight() {
		controller.turnRight();
		Orientation o = boat.getOrientation();
		verify(boat).setOrientation(orientationCaptor.capture());

		assertEquals(o.getRight(), orientationCaptor.getValue());
	}

	@Test
	public void testMoveForwardNoBoat() {
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

		controller.moveForward();

		verify(boat, times(0)).getOrientation();
		verify(boat, times(0)).getCoordinate();
		verify(boat, times(0)).setOrientation(any());
		verify(boat, times(0)).setCoordinate(any());

	}

	private void moveForwardChecks(int expectedX, int expectedY, int times) {
		TypeFactory tf = new TypeFactory();
		Coordinate expectedC = tf.createCoordinate(expectedX, expectedY);

		controller.moveForward();

		verify(boat, atLeast(1)).getOrientation();
		verify(boat, atLeast(1)).getCoordinate();

		verify(boat, times(0)).setOrientation(any(Orientation.class));

		if (times > 0) {
			verify(boat, times(times)).setCoordinate(coordinateCaptor.capture());
			assertEquals(expectedC, coordinateCaptor.getValue());
		}
		else {
			verify(boat, times(times)).setCoordinate(any(Coordinate.class));
			
		}
	}
	
	
	
	@Test
	public void testMoveForwardWithBoundaryInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, 0);
		when(boat.getOrientation()).thenReturn(Orientation.UP);
		when(boat.getCoordinate()).thenReturn(rc);

		
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveForwardChecks(rc.getX(), rc.getY(), 0);

	}
	
	@Test
	public void testMoveForwardWithBoundaryInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(0, 0);
		when(boat.getOrientation()).thenReturn(Orientation.LEFT);
		when(boat.getCoordinate()).thenReturn(rc);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		moveForwardChecks(rc.getX(), rc.getY(), 0);
	}
	
	@Test
	public void testMoveForwardWithBoundaryInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(4, 4);
		when(boat.getOrientation()).thenReturn(Orientation.DOWN);
		when(boat.getCoordinate()).thenReturn(rc);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});
		
		moveForwardChecks(rc.getX(), rc.getY(), 0);
	}
	
	@Test
	public void testMoveForwardWithBoundaryInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(4, 4);
		when(boat.getOrientation()).thenReturn(Orientation.RIGHT);
		when(boat.getCoordinate()).thenReturn(rc);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});
		moveForwardChecks(rc.getX(), rc.getY(),0);
	}
	
	
	
	
	

	@Test
	public void testMoveForwardWithWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.UP);
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

		moveForwardChecks(rc.getX(), rc.getY(), 0);

	}

	@Test
	public void testMoveForwardWithoutWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.UP);
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

		moveForwardChecks(rc.getX(), rc.getY() - 1, 1);

	}

	@Test
	public void testMoveForwardWithWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.LEFT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX(), rc.getY(), 0);
	}

	@Test
	public void testMoveForwardWithoutWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.LEFT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX()-1, rc.getY() ,1);
	}

	@Test
	public void testMoveForwardWithWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.DOWN);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});
		moveForwardChecks(rc.getX(), rc.getY(), 0);
	}

	@Test
	public void testMoveForwardWithoutWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.DOWN);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});
		moveForwardChecks(rc.getX(), rc.getY() + 1, 1);
	}

	@Test
	public void testMoveForwardWithWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.RIGHT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX(), rc.getY(),0);
	}

	@Test
	public void testMoveForwardWithoutWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(3, 3);
		when(boat.getOrientation()).thenReturn(Orientation.RIGHT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		moveForwardChecks(rc.getX()+1, rc.getY(), 1);

	}

	@Test
	public void testAddBoat() {
		TypeFactory tf = new TypeFactory();
		Orientation orientation = Orientation.UP;
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addBoat(coordinate, orientation);

		verify(elementRepository).addElement(elementTypeCaptor.capture(), coordinateCaptor.capture());
		assertEquals(ElementType.BOAT, elementTypeCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());

		verify(boat).setOrientation(orientationCaptor.capture());
		assertEquals(orientation, orientationCaptor.getValue());

		verify(eventFactory).createBoatAddedEvent(coordinateCaptor.capture(), orientationCaptor.capture());
		assertEquals(orientation, orientationCaptor.getValue());
		assertEquals(coordinate, coordinateCaptor.getValue());

		verify(listener).onBoatAddedEvent(any(BoatAddedEvent.class));
	}

	@Test
	public void testAddBoatNullOrientation() {
		String excMessage = "The given Orientation can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		TypeFactory tf = new TypeFactory();
		Orientation orientation = null;
		Coordinate coordinate = tf.createCoordinate(1, 4);
		controller.addBoat(coordinate, orientation);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(boat, times(0)).setOrientation(any(Orientation.class));

		verify(eventFactory, times(0)).createBoatAddedEvent(any(Coordinate.class), any(Orientation.class));

		verify(listener, times(0)).onBoatAddedEvent(any(BoatAddedEvent.class));

	}

	@Test
	public void testAddBoatNullCoordinate() {
		String excMessage = "The given Coordinate can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		Orientation orientation = Orientation.UP;
		Coordinate coordinate = null;
		controller.addBoat(coordinate, orientation);

		verify(elementRepository, times(0)).addElement(any(ElementType.class), any(Coordinate.class));

		verify(boat, times(0)).setOrientation(any(Orientation.class));

		verify(eventFactory, times(0)).createBoatAddedEvent(any(Coordinate.class), any(Orientation.class));

		verify(listener, times(0)).onBoatAddedEvent(any(BoatAddedEvent.class));

	}

	@Test
	public void testCheckIfWallInFrontNoBoat() {
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

		controller.checkIfWallInFront();

		verify(boat, times(0)).getOrientation();
		verify(boat, times(0)).getCoordinate();

	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.UP);
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

		checkIfWallInFrontChecks(true, 1);

	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontUp() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.UP);
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

		checkIfWallInFrontChecks(false, 1);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);

	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.LEFT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(true, 1);

	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontLeft() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.LEFT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() - 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(false, 1);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);

	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.DOWN);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});
		checkIfWallInFrontChecks(true, 1);
	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontDown() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.DOWN);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() && c.getY() == rc.getY() + 1) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});
		checkIfWallInFrontChecks(false, 1);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);
	}

	@Test
	public void testCheckIfWallInFrontWithWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.RIGHT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(IceBerg.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(true, 1);

	}

	@Test
	public void testCheckIfWallInFrontWithoutWallInFrontRight() {
		TypeFactory tf = new TypeFactory();
		Coordinate rc = tf.createCoordinate(1, 1);
		when(boat.getOrientation()).thenReturn(Orientation.RIGHT);
		when(boat.getCoordinate()).thenReturn(rc);
		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Coordinate c = (Coordinate) invocation.getArgument(0);
				Set<Element> elements = new HashSet<Element>();
				if (c.getX() == rc.getX() + 1 && c.getY() == rc.getY()) {
					elements.add(mock(Goal.class));
				}
				return elements;
			}

		});

		checkIfWallInFrontChecks(false, 1);

		when(elementRepository.getElements(any(Coordinate.class))).thenAnswer(new Answer<Set<Element>>() {

			@Override
			public Set<Element> answer(InvocationOnMock invocation) throws Throwable {
				Set<Element> elements = new HashSet<Element>();
				return elements;
			}
		});

		checkIfWallInFrontChecks(false, 2);

	}

	private void checkIfWallInFrontChecks(Boolean valid, int times) {
		Boolean result = controller.checkIfWallInFront();

		verify(boat, times(times)).getOrientation();
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
