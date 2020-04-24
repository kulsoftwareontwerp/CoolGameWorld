package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.CoolGameWorld.events.BoatAddedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.BoatChangedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.ElementAddedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.ElementsClearedEvent;
import com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory;
import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;


@RunWith(MockitoJUnitRunner.class)
public class BoatCanvasTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Spy
	private TypeFactory typeFactory;
	@Spy
	private CellFactory factory;

	@Spy
	private HashMap<Coordinate, Cell> cells;

	@Spy
	@InjectMocks
	private BoatCanvas canvas;

	@Captor
	private ArgumentCaptor<ElementType> type;
	@Captor
	private ArgumentCaptor<Coordinate> coordinate;
	@Captor
	private ArgumentCaptor<BoatState> boatState;

	private EventFactory eventFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventFactory = new EventFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#BoatCanvas()}.
	 */
	@Test
	public void testBoatCanvas() {
		BoatCanvas canvas = new BoatCanvas();
		Field f;
		try {
			f = BoatCanvas.class.getDeclaredField("cells");
			f.setAccessible(true);
			assertNotNull(f.get(canvas));
			f = BoatCanvas.class.getDeclaredField("factory");
			f.setAccessible(true);
			assertNotNull(f.get(canvas));
			f = BoatCanvas.class.getDeclaredField("typeFactory");
			f.setAccessible(true);
			assertNotNull(f.get(canvas));

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaint() {
		Graphics g = spy(Graphics.class);
		when(g.getClipBounds()).thenReturn(new Rectangle(500, 600));
		canvas.paint(g);
		verify(g, atLeastOnce()).drawLine(any(Integer.class), any(Integer.class), any(Integer.class),
				any(Integer.class));
		//verify(g, atLeastOnce()).drawImage(any(Image.class), any(Integer.class), any(Integer.class), any());

	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#paint(java.awt.Graphics)}.
	 */
	@Test
	public void testPaintNullGraphics() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Graphics object can't be null");
		canvas.paint(null);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onBoatChangeEvent(com.kuleuven.swop.group17.BoatGameWorld.events.BoatChangedEvent)}.
	 */
	@Test
	public void testOnBoatChangeEventNoBoat() {
		reset(factory);
		BoatChangedEvent e = eventFactory.createBoatChangedEvent(typeFactory.createCoordinate(1, 0), BoatState.FLOATING);
		canvas.onBoatChangeEvent(e);
		verify(factory,times(0)).createCell(eq(ElementType.BOAT), coordinate.capture(), boatState.capture());
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onBoatChangeEvent(com.kuleuven.swop.group17.BoatGameWorld.events.BoatChangedEvent)}.
	 */
	@Test
	public void testOnBoatChangeEvent() {
		Cell spyBoat = spy(factory.createCell(ElementType.BOAT, typeFactory.createCoordinate(1, 0), BoatState.FLOATING));
		cells.put(spyBoat.getCoordinate(), spyBoat);
		reset(factory);
		BoatChangedEvent e = eventFactory.createBoatChangedEvent(typeFactory.createCoordinate(0, 0), BoatState.CRASHED);
		canvas.onBoatChangeEvent(e);
		verify(factory).createCell(eq(ElementType.BOAT), coordinate.capture(), boatState.capture());
		assertEquals(e.getCoordinate(), coordinate.getValue());
		assertEquals(e.getBoatState(), boatState.getValue());
		assertEquals(ElementType.WATER,spyBoat.getType());
		
		Optional<Cell> robotCell = cells.values().stream().filter(c -> c.getType() == ElementType.BOAT).findFirst();
		assertTrue(robotCell.isPresent());
		
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onBoatChangeEvent(com.kuleuven.swop.group17.BoatGameWorld.events.BoatChangedEvent)}.
	 */
	@Test
	public void testOnBoatChangeEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onBoatChangeEvent(null);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onBoatAddedEvent(com.kuleuven.swop.group17.BoatGameWorld.events.BoatAddedEvent)}.
	 */
	@Test
	public void testOnBoatAddedEvent() {
		BoatAddedEvent e = eventFactory.createBoatAddedEvent(typeFactory.createCoordinate(0, 0), BoatState.FLOATING);
		canvas.onBoatAddedEvent(e);
		verify(factory,atLeastOnce()).createCell(type.capture(), coordinate.capture(), boatState.capture());
		assertEquals(e.getCoordinate(), coordinate.getValue());
		assertEquals(ElementType.BOAT, type.getValue());
		assertEquals(e.getBoatSate(), boatState.getValue());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onBoatAddedEvent(com.kuleuven.swop.group17.BoatGameWorld.events.BoatAddedEvent)}.
	 */
	@Test
	public void testOnBoatAddedEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onBoatAddedEvent(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onElementAddedEvent(com.kuleuven.swop.group17.BoatGameWorld.events.ElementAddedEvent)}.
	 */
	@Test
	public void testOnElementAddedEvent() {
		ElementAddedEvent e = eventFactory.createElementAddedEvent(typeFactory.createCoordinate(0, 0),
				ElementType.ICEBERG);
		canvas.onElementAddedEvent(e);
		verify(factory,atLeastOnce()).createCell(type.capture(), coordinate.capture());
		assertEquals(e.getCoordinate(), coordinate.getValue());
		assertEquals(e.getType(), type.getValue());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onElementAddedEvent(com.kuleuven.swop.group17.BoatGameWorld.events.ElementAddedEvent)}.
	 */
	@Test
	public void testOnElementAddedEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onElementAddedEvent(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onElementsClearedEvent(com.kuleuven.swop.group17.BoatGameWorld.events.ElementsClearedEvent)}.
	 */
	@Test
	public void testOnElementsClearedEvent() {
		canvas.onElementsClearedEvent(mock(ElementsClearedEvent.class));
		verify(cells).clear();
		verify(cells, atLeastOnce()).put(any(Coordinate.class), any(Cell.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.guiLayer.BoatCanvas#onElementsClearedEvent(com.kuleuven.swop.group17.BoatGameWorld.events.ElementsClearedEvent)}.
	 */
	@Test
	public void testOnElementsClearedEventNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("event can't be null");
		canvas.onElementsClearedEvent(null);
	}
}
