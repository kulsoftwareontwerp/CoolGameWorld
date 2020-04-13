/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * OrientationTest
 *
 * @version 0.1
 * @author group17
 */
public class OrientationTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#getLeft()}.
	 */
	@Test
	public void testGetLeft() {
		assertEquals(Orientation.LEFT,Orientation.UP.getLeft());
		assertEquals(Orientation.DOWN,Orientation.LEFT.getLeft());
		assertEquals(Orientation.RIGHT,Orientation.DOWN.getLeft());
		assertEquals(Orientation.UP,Orientation.RIGHT.getLeft());
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#getRight()}.
	 */
	@Test
	public void testGetRight() {
		assertEquals(Orientation.RIGHT,Orientation.UP.getRight());
		assertEquals(Orientation.DOWN,Orientation.RIGHT.getRight());
		assertEquals(Orientation.LEFT,Orientation.DOWN.getRight());
		assertEquals(Orientation.UP,Orientation.LEFT.getRight());
	}

}
