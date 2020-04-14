/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.applicationLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.io.IOException;
import java.lang.reflect.Field;
import java.rmi.UnexpectedException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.DomainFactory;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementFactory;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Boat;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.IceBerg;
import com.kuleuven.swop.group17.CoolGameWorld.events.GUIListener;
import com.kuleuven.swop.group17.CoolGameWorld.guiLayer.GuiFactory;
import com.kuleuven.swop.group17.CoolGameWorld.guiLayer.BoatCanvas;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.Orientation;
import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorldAction;
import com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorldPredicate;
import com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorldSnapshot;
import com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorldType;
import com.kuleuven.swop.group17.CoolGameWorld.types.SupportedActions;
import com.kuleuven.swop.group17.CoolGameWorld.types.SupportedPredicates;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;

/**
 * CoolGameWorldTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class CoolGameWorldTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private BoatController boatController;

	@Mock
	private ElementController elementController;

	@Mock
	private BoatCanvas boatCanvas;
	
	@Mock
	private TypeFactory typeFactory;
	
	@Mock
	private GuiFactory guiFactory;


	
	@Mock
	private CoolGameWorldSnapshot snapshot;
	
	@Mock 
	private Graphics mockGraphics;
	
	@Mock
	private CoolGameWorldType type;
	@Mock
	private CoolGameWorldAction action;
	
	@Mock
	private CoolGameWorldPredicate predicate;
	
	
	
	@Captor
	private ArgumentCaptor<Graphics> graphics;

	@Captor
	private ArgumentCaptor<SupportedActions> supportedAction;	
	@Captor
	private ArgumentCaptor<SupportedPredicates> supportedPredicate;	
	
	@Captor
	private ArgumentCaptor<Set<Element>> elements;

	@InjectMocks
	private CoolGameWorld world;


	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Stub TypeFactory
		when(typeFactory.createAction(supportedAction.capture())).then(new Answer<CoolGameWorldAction>() {
			@Override
			public CoolGameWorldAction answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				when(action.getAction()).thenReturn((SupportedActions) args[0]);
				return action;
			}
			
		});
		when(typeFactory.createPredicate(supportedPredicate.capture())).then(new Answer<CoolGameWorldPredicate>() {
			@Override
			public CoolGameWorldPredicate answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				when(predicate.getPredicate()).thenReturn((SupportedPredicates) args[0]);
				return predicate;
			}
			
		});
		when(typeFactory.createCoordinate(any(int.class), any(int.class))).then(new Answer<Coordinate>() {

			@Override
			public Coordinate answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Coordinate c = mock(Coordinate.class);
				when(c.getX()).thenReturn((int)args[0]);
				when(c.getY()).thenReturn((int)args[1]);
				
				return c;
			}
			
		});


		when(typeFactory.createType()).thenReturn(type);
		
		
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#CoolGameWorld()}.
	 */
	@Ignore
	public void testCoolGameWorldFieldsInitialised() {
		// The constructor of the CoolGameWorld is responsible for creating the
		// BoatController, the ElementController, the elementRepository and the
		// BoatCanvas.

		CoolGameWorld newWorld = new CoolGameWorld();
		try {
			Field f = CoolGameWorld.class.getDeclaredField("typeFactory");
			f.setAccessible(true);
			assertTrue("CoolGameWorldSnapshotFactory was not initialised", f.get(newWorld) != null);
			f = CoolGameWorld.class.getDeclaredField("boatController");
			f.setAccessible(true);
			assertTrue("BoatController was not initialised", f.get(newWorld) != null);
			f = CoolGameWorld.class.getDeclaredField("elementController");
			f.setAccessible(true);
			assertTrue("ElementController was not initialised", f.get(newWorld) != null);
			f = CoolGameWorld.class.getDeclaredField("boatCanvas");
			f.setAccessible(true);
			assertTrue("BoatCanvas was not initialised", f.get(newWorld) != null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#CoolGameWorld()}.
	 */
	@Test
	public void testCoolGameWorldListenersAdded() {
		// The constructor should also add the correct listeners to the needed
		// controllers.
		// At last The CoolGameWorld itself is also initialized by adding elements and
		// a boat.
		verify(elementController).addListener(any(GUIListener.class));
		verify(boatController).addListener(any(GUIListener.class));

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#CoolGameWorld()}.
	 */
	@Test
	public void testCoolGameWorldInitialized() {
		// At last The CoolGameWorld itself is also initialized by adding elements and
		// a boat. A boatGameWorld should at least add a boat, otherwise it won't be
		// able to do any meaningful actions.
		verify(boatController).addBoat(any(), any(BoatState.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformAction() {
		world.performAction(typeFactory.createAction(SupportedActions.MOVEFORWARD));
		verify(boatController).moveForward();
		world.performAction(typeFactory.createAction(SupportedActions.TURNLEFT));
		verify(boatController).turnLeft();
		world.performAction(typeFactory.createAction(SupportedActions.TURNRIGHT));
		verify(boatController).turnRight();
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformActionInvalidAction() {
		String excMessage = "The given action is not a supported action for a CoolGameWorld.";
		exceptionRule.expect(UnsupportedOperationException.class);
		exceptionRule.expectMessage(excMessage);
		Action action = new Action() {
		};
		world.performAction(action);
		Mockito.verifyNoInteractions(boatController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test(expected = RuntimeException.class)
	public void testPerformActionUnexpectedException() {
		doThrow(new NoSuchElementException("I did not see this comming.")).when(boatController).moveForward();

		world.performAction(typeFactory.createAction(SupportedActions.MOVEFORWARD));
		Mockito.verifyNoInteractions(boatController);
	}
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#performAction(com.kuleuven.swop.group17.GameWorldApi.Action)}.
	 */
	@Test
	public void testPerformActionNullAction() {
		String excMessage = "The given action can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
		world.performAction(null);
		verifyNoInteractions(boatController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test
	public void testEvaluate() {
		world.evaluate(typeFactory.createPredicate(SupportedPredicates.ICEBERGINFRONT));
		verify(boatController).checkIfIceBergInFront();
	}
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test
	public void testEvaluateInvalidPredicate() {
		String excMessage = "The given predicate is not a supported predicate for a CoolGameWorld.";
		exceptionRule.expect(UnsupportedOperationException.class);
		exceptionRule.expectMessage(excMessage);
		Predicate predicate = new Predicate() {
		};
		world.evaluate(predicate);
		verifyNoInteractions(boatController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test(expected = RuntimeException.class)
	public void testEvaluateUnexpectedException() {
		doThrow(new NoSuchElementException("I did not see this comming.")).when(boatController).checkIfIceBergInFront();

		world.evaluate(typeFactory.createPredicate(SupportedPredicates.ICEBERGINFRONT));
		Mockito.verifyNoInteractions(boatController);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#evaluate(com.kuleuven.swop.group17.GameWorldApi.Predicate)}.
	 */
	@Test
	public void testEvaluateNullPredicate() {
		String excMessage = "The given predicate can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		world.evaluate(null);
		verifyNoInteractions(boatController);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#saveState()}.
	 */
	@Test
	public void testSaveState() {
		Set<Element> state = constructTestState();

		when(typeFactory.createSnapshot(elements.capture())).thenAnswer(new Answer<CoolGameWorldSnapshot>() {
			@SuppressWarnings("unchecked")
			public CoolGameWorldSnapshot answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				when(snapshot.getElements()).thenReturn((Set<Element>) args[0]);
				return snapshot;
			}
		});
		
		when(elementController.getElements()).thenReturn(state);

		when(typeFactory.createSnapshot(elements.capture())).thenAnswer(new Answer<CoolGameWorldSnapshot>() {
			@SuppressWarnings("unchecked")
			public CoolGameWorldSnapshot answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				when(snapshot.getElements()).thenReturn((Set<Element>) args[0]);
				return snapshot;
			}
		});

		CoolGameWorldSnapshot snap = (CoolGameWorldSnapshot) world.saveState();

		verify(elementController).getElements();
		verify(typeFactory).createSnapshot(any());
		assertEquals(elements.getValue(),state);
		assertEquals(state, snap.getElements());

	}
	
	private Set<Element> constructTestState(){
		DomainFactory f = new DomainFactory();
		ElementFactory ef = f.createElementFactory();
		
		Set<Element> state = new HashSet<Element>();
		Boat r =(Boat) ef.createElement(ElementType.BOAT, typeFactory.createCoordinate(0, 0));
		r.setBoatState(BoatState.FLOATING);
		state.add(r);
		state.add(ef.createElement(ElementType.GOAL,typeFactory.createCoordinate(2, 2)));
		state.add(ef.createElement(ElementType.ICEBERG,typeFactory.createCoordinate(2, 3)));
		state.add(ef.createElement(ElementType.ICEBERG,typeFactory.createCoordinate(3, 2)));
		state.add(ef.createElement(ElementType.ICEBERG,typeFactory.createCoordinate(2, 1)));
		state.add(ef.createElement(ElementType.ICEBERG,typeFactory.createCoordinate(1, 2)));
		return state;
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreState() {
		// the mocks boatController and elementController are reset.
		// this means that all previously called invocations and
		// all previously made stubs on these mocks are forgotten.
		// In this case it's used so the count of the number of times a method was called 
		// only keeps in account the statements here.
		reset(boatController);
		reset(elementController);
		
		Set<Element> state = constructTestState();
		
		when(snapshot.getElements()).thenReturn(state);
		
		world.restoreState(snapshot);

		
		
		verify(boatController, times(1)).addBoat(any(Coordinate.class), any(BoatState.class));
		verify(elementController, times(5)).addElement(any(ElementType.class), any(Coordinate.class));
		
	}


	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreStateNullState() {
		// the mocks boatController and elementController are reset.
		// this means that all previously called invocations and
		// all previously made stubs on these mocks are forgotten.
		// In this case it's used so the count of the number of times a method was called 
		// only keeps in account the statements here.
		reset(boatController);
		reset(elementController);	

		String excMessage = "The given GameWorldSnapshot can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);
			
		world.restoreState(null);

		
		
		verifyNoInteractions(boatController);
		verifyNoInteractions(elementController);
		
	}
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#restoreState(com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot)}.
	 */
	@Test
	public void testRestoreStateInvalidState() {
		// the mocks boatController and elementController are reset.
		// this means that all previously called invocations and
		// all previously made stubs on these mocks are forgotten.
		// In this case it's used so the count of the number of times a method was called 
		// only keeps in account the statements here.
		reset(boatController);
		reset(elementController);	

		String excMessage = "The given GameWorldSnapshot is not a valid snapshot for a CoolGameWorld.";
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage(excMessage);
			
		GameWorldSnapshot state = new GameWorldSnapshot() {
		};
		
		world.restoreState(state);

		
		
		verifyNoInteractions(boatController);
		verifyNoInteractions(elementController);
		
	}
	
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaint() {
		world.paint(mockGraphics);

		verify(boatCanvas).paint(graphics.capture());
		assertEquals(mockGraphics, graphics.getValue());
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaintNullGraphics() {
		String excMessage = "The given Graphics can't be null";
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage(excMessage);

		world.paint(null);
		
		verifyNoInteractions(boatCanvas);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#paint(java.awt.Graphics)}.
	 */
	@Test(expected = RuntimeException.class)
	public void testPaintUnexpectedException() {
		doThrow(new NoSuchElementException("I did not see this comming.")).when(boatCanvas).paint(any(Graphics.class));
		world.paint(mockGraphics);
		Mockito.verifyNoInteractions(boatCanvas);
	}
	
	
	
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.CoolGameWorld#getType()}.
	 */
	@Test
	public void testGetType() {
		
		
		GameWorldType testType = world.getType();
		
		
		verify(typeFactory).createType();
		assertEquals(type, testType);
	}

}
