package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;

@RunWith(MockitoJUnitRunner.class)
public class CellFactoryTest {
	private TypeFactory tf;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Spy
	private CellFactory factory;
	
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
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.CellFactory#CellFactory()}.
	 */
	@Test
	public void testCellFactory() {
		CellFactory cf = new CellFactory();
		assertNotNull(cf);
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
//	@Test
//	public void testCreateCellElementTypeCoordinateBoatStateNoCoordinate() {
//		exceptionRule.expect(IllegalArgumentException.class);
//		exceptionRule.expectMessage("coordinate can't be null");
//	    factory.createCell(ElementType.GOAL, null, BoatState.FLOATING);
//	
//	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
//	@Test
//	public void testCreateCellElementTypeCoordinateBoatStateNoBoatState() {
//		exceptionRule.expect(IllegalArgumentException.class);
//		exceptionRule.expectMessage("boatState can't be null");
//	    factory.createCell(ElementType.BOAT, tf.createCoordinate(0, 0), null);
//
//	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinateBoatState() {
	    Cell c = factory.createCell(ElementType.BOAT, tf.createCoordinate(0, 0), BoatState.FLOATING);
	    
	    assertEquals(ElementType.BOAT, c.getType());
	    assertEquals(BoatState.FLOATING, c.getBoatState());
	    assertEquals(tf.createCoordinate(0, 0), c.getCoordinate());
	    

	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinate() {
	    Cell c = factory.createCell(ElementType.BOAT, tf.createCoordinate(0, 0));
	    
	    assertEquals(ElementType.BOAT, c.getType());
	    assertEquals(tf.createCoordinate(0, 0), c.getCoordinate());
	    }
	
	/**
	 * Test method for {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.CellFactory#createCell(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testCreateCellElementTypeCoordinateNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("coordinate can't be null");
	    factory.createCell(ElementType.GOAL, null);
	    }
	
}
