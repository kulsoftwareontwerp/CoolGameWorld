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
	public void testToBoatStateString() {
		for (ElementType et : ElementType.values()) {
			for (BoatState o : BoatState.values()) {
				if (et == ElementType.BOAT) {
					assertEquals(et.toString() + o.toString(), et.toBoatStateString(o));
				} else {
					assertEquals(et.toString(), et.toBoatStateString(o));
				}
			}
		}
		
		assertEquals(ElementType.BOAT.toString() + BoatState.ARRIVED.toString(), ElementType.BOAT.toBoatStateString(null));		
	}

}
