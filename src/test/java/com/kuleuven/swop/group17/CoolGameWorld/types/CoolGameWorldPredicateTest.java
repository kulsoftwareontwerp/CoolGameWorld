/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * CoolGameWorldPredicateTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class CoolGameWorldPredicateTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

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
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#CoolGameWorldPredicate(com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld)}.
	 */
	@Test
	public void testCoolGameWorldPredicate() {
		CoolGameWorldPredicate predicate = new CoolGameWorldPredicate(SupportedPredicates.ICEBERGINFRONT);
		try {
			Field f = CoolGameWorldPredicate.class.getDeclaredField("predicate");
			f.setAccessible(true);
			assertTrue("predicate was not initialised",
					(SupportedPredicates) f.get(predicate) == SupportedPredicates.ICEBERGINFRONT);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#CoolGameWorldPredicate(com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld)}.
	 */
	@Test
	public void testCoolGameWorldPredicateNullPredicate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("The given predicate can't be null");
		CoolGameWorldPredicate predicate = new CoolGameWorldPredicate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#getPredicate()}.
	 */
	@Test
	public void testGetPredicate() {
		CoolGameWorldPredicate predicate = new CoolGameWorldPredicate(SupportedPredicates.ICEBERGINFRONT);
		assertEquals(SupportedPredicates.ICEBERGINFRONT,predicate.getPredicate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#toString()}.
	 */
	@Test
	public void testToString() {
		SupportedPredicates sp = mock(SupportedPredicates.class);
		when(sp.toString()).thenReturn("predicate test");
		CoolGameWorldPredicate predicate = new CoolGameWorldPredicate(sp);

		assertEquals("predicate test", predicate.toString());	}

}
