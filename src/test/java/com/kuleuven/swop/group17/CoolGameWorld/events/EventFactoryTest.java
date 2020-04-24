package com.kuleuven.swop.group17.CoolGameWorld.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;



@RunWith(MockitoJUnitRunner.class)
public class EventFactoryTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Spy
	private EventFactory ef;
	private TypeFactory tf;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tf = new TypeFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#EventFactory()}.
	 */
	@Test
	public void testEventFactory() {
		EventFactory ef = new EventFactory();
		assertNotNull(ef);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createBoatChangedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateBoatChangedEvent() {
		BoatChangedEvent e = ef.createBoatChangedEvent(tf.createCoordinate(0, 0), BoatState.FLOATING);
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createBoatChangedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateBoatChangedEventNoCoordinate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A boat needs to have a coordinate");
		BoatChangedEvent e = ef.createBoatChangedEvent(null, BoatState.FLOATING);
		assertEquals(null, e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createBoatChangedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateBoatChangedEventNoOrientation() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A boat needs to have an boatstate");
		BoatChangedEvent e = ef.createBoatChangedEvent(tf.createCoordinate(0, 0), null);
		assertEquals(null, e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createBoatAddedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateBoatAddedEvent() {
		BoatAddedEvent e = ef.createBoatAddedEvent(tf.createCoordinate(0, 0), BoatState.FLOATING);
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createBoatAddedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateBoatAddedEventNoCoordinate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A boat needs to have a coordinate");
		BoatAddedEvent e = ef.createBoatAddedEvent(null, BoatState.FLOATING);
		assertEquals(null, e);

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createBoatAddedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.Orientation)}.
	 */
	@Test
	public void testCreateBoatAddedEventNoOrientation() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("A boat needs to have an boatstate");
		BoatAddedEvent e = ef.createBoatAddedEvent(tf.createCoordinate(0, 0), null);
		assertEquals(null, e);

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createElementsClearedEvent()}.
	 */
	@Test
	public void testCreateElementsClearedEvent() {
		ElementsClearedEvent e = ef.createElementsClearedEvent();
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createElementAddedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCreateElementAddedEvent() {
		ElementAddedEvent e = ef.createElementAddedEvent(tf.createCoordinate(0, 0), ElementType.BOAT);
		assertNotNull(e);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createElementAddedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCreateElementAddedEventNoCoordinate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("An element needs to have a coordinate");
		ElementAddedEvent e = ef.createElementAddedEvent(null, ElementType.BOAT);
		assertEquals(null, e);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.events.EventFactory#createElementAddedEvent(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCreateElementAddedEventNoType() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("An element needs to have a type");
		ElementAddedEvent e = ef.createElementAddedEvent(tf.createCoordinate(0, 0), null);
		assertEquals(null, e);
	}
}
