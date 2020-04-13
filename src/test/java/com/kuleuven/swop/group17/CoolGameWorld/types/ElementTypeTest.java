/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ElementTypeTest
 *
 * @version 0.1
 * @author group17
 */
public class ElementTypeTest {

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
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#toOrientationString(com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld)}.
	 */
	@Test
	public void testToOrientationString() {
		for (ElementType et : ElementType.values()) {
			for (Orientation o : Orientation.values()) {
				if (et == ElementType.BOAT) {
					assertEquals(et.toString() + o.toString(), et.toOrientationString(o));
				} else {
					assertEquals(et.toString(), et.toOrientationString(o));
				}
			}
		}
		
		assertEquals(ElementType.BOAT.toString() + Orientation.UP.toString(), ElementType.BOAT.toOrientationString(null));		
	}

}
