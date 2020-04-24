package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class GuiFactoryTest {
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
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.GuiFactory#GuiFactory()}.
	 */
	@Test
	public void testGuiFactory() {
		GuiFactory gf = new GuiFactory();
		assertNotNull(gf);
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.GuiFactory#createBoatCanvas()}.
	 */
	@Test
	public void testCreateBoatCanvas() {
		GuiFactory gf = new GuiFactory();
		BoatCanvas canvas = gf.createBoatCanvas();
		assertNotNull(canvas);
	}
}
