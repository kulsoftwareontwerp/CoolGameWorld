/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SupportedPredicatesTest
 *
 * @version 0.1
 * @author group17
 */
public class SupportedPredicatesTest {

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

	@Test
	public void testToString() {
		assertEquals("Iceberg In Front!", SupportedPredicates.ICEBERGINFRONT.toString());
	}

}
