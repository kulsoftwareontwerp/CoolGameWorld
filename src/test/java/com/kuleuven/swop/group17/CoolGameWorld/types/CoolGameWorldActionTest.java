/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * CoolGameWorldActionTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class CoolGameWorldActionTest {

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
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#CoolGameWorldAction(com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld)}.
	 */
	@Test
	public void testCoolGameWorldAction() {
		CoolGameWorldAction action = new CoolGameWorldAction(SupportedActions.MOVEFORWARD);
		try {
			Field f = CoolGameWorldAction.class.getDeclaredField("action");
			f.setAccessible(true);
			assertTrue("action was not initialised", (SupportedActions) f.get(action) == SupportedActions.MOVEFORWARD);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#CoolGameWorldAction(com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld)}.
	 */
	@Test
	public void testCoolGameWorldActionNullAction() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("The given action can't be null");
		CoolGameWorldAction action = new CoolGameWorldAction(null);

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#getAction()}.
	 */
	@Test
	public void testGetAction() {
		CoolGameWorldAction action = new CoolGameWorldAction(SupportedActions.MOVEFORWARD);
		assertEquals(SupportedActions.MOVEFORWARD, action.getAction());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#toString()}.
	 */
	@Test
	public void testToString() {
		SupportedActions sa = mock(SupportedActions.class);
		when(sa.toString()).thenReturn("action test");
		CoolGameWorldAction action = new CoolGameWorldAction(sa);

		assertEquals("action test", action.toString());
	}

}
