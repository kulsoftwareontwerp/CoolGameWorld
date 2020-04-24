package com.kuleuven.swop.group17.CoolGameWorld.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;



public class ElementAddedEventTest {
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
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent#ElementAddedEvent(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate, com.kuleuven.swop.group17.RobotGameWorld.types.ElementType)}.
	 */
	@Test
	public void testElementAddedEvent() {
		ElementAddedEvent e = new ElementAddedEvent(tf.createCoordinate(0, 0), ElementType.ICEBERG);
		assertNotNull(e);	
		}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		ElementAddedEvent e = new ElementAddedEvent(tf.createCoordinate(0, 0), ElementType.ICEBERG);
		assertEquals(tf.createCoordinate(0, 0), e.getCoordinate());
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.events.ElementAddedEvent#getType()}.
	 */
	@Test
	public void testGetType() {
		ElementAddedEvent e = new ElementAddedEvent(tf.createCoordinate(0, 0), ElementType.ICEBERG);
		assertEquals(ElementType.ICEBERG, e.getType());
	}
}
