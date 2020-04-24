package com.kuleuven.swop.group17.CoolGameWorld.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;



public class BoatAddedEventTest {
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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent#RobotAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.Orientation)}.
	 */
	@Test
	public void testRobotAddedEvent() {
		BoatAddedEvent e = new BoatAddedEvent(tf.createCoordinate(0, 0), BoatState.FLOATING);
		assertNotNull(e);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		BoatAddedEvent e = new BoatAddedEvent(tf.createCoordinate(0, 0), BoatState.FLOATING);
		assertEquals(tf.createCoordinate(0, 0), e.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.events.RobotAddedEvent#getOrientation()}.
	 */
	@Test
	public void testGetOrientation() {
		BoatAddedEvent e = new BoatAddedEvent(tf.createCoordinate(0, 0), BoatState.FLOATING);
		assertEquals(BoatState.FLOATING, e.getBoatSate());
	}
}
