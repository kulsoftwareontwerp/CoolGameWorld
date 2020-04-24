package com.kuleuven.swop.group17.CoolGameWorld.events;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElementClearedEventTest {
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
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.events.ElementsClearedEvent#ElementsClearedEvent()}.
	 */
	@Test
	public void testElementsClearedEvent() {
		ElementsClearedEvent e= new ElementsClearedEvent();
		assertNotNull(e);
	}
}
