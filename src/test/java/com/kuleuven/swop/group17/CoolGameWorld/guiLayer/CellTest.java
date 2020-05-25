package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import java.awt.Image;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;
@RunWith(MockitoJUnitRunner.class)
public class CellTest {
	private TypeFactory tf;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	private Cell cell;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tf = new TypeFactory();
		cell = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.BOAT);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Cell g = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.ICEBERG);
		Cell g2 = new Cell(tf.createCoordinate(5, 3), BoatState.FLOATING, ElementType.ICEBERG);
		assertNotEquals(g.hashCode(), g2.hashCode());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setCoordinate(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinate() {
		cell.setCoordinate(tf.createCoordinate(1, 9));
		assertEquals(tf.createCoordinate(1, 9), cell.getCoordinate());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setCoordinate(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("coordinate can't be null.");
		cell.setCoordinate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setBoatState(com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
//	@Test
//	public void testSetBoatStateNoBoatState() {
//		exceptionRule.expect(IllegalArgumentException.class);
//		exceptionRule.expectMessage("boatState can't be null.");
//		cell.setBoatState(null);
//	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setType(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testSetTypeNoType() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);

		c.setType(null);
		assertEquals(ElementType.WATER, c.getType());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.BoatState, com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCell() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.ICEBERG);
		try {
			Field f;
			f = Cell.class.getDeclaredField("coordinate");
			f.setAccessible(true);
			assertEquals(tf.createCoordinate(5, 1), f.get(c));
			f = Cell.class.getDeclaredField("boatState");
			f.setAccessible(true);
			assertEquals(BoatState.FLOATING, f.get(c));
			f = Cell.class.getDeclaredField("type");
			f.setAccessible(true);
			assertEquals(ElementType.ICEBERG, f.get(c));
			f = Cell.class.getDeclaredField("cachedImages");
			f.setAccessible(true);
			assertNotNull(f.get(c));
			f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			assertNotNull(f.get(c));
		} catch (Exception e) {
			fail("fields were not initialized correctly");
		}

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.BoatState, com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCellNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("coordinate can't be null.");
		new Cell(null, BoatState.FLOATING, ElementType.ICEBERG);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.BoatState, com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
//	@Test
//	public void testCellNoBoatState() {
//		exceptionRule.expect(IllegalArgumentException.class);
//		exceptionRule.expectMessage("boatState can't be null.");
//		new Cell(tf.createCoordinate(5, 1), null, ElementType.ICEBERG);
//	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#Cell(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate, com.kuleuven.swop.group17.CoolGameWorld.types.BoatState, com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testCellNoType() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, null);
		try {
			Field f;
			f = Cell.class.getDeclaredField("coordinate");
			f.setAccessible(true);
			assertEquals(tf.createCoordinate(5, 1), f.get(c));
			f = Cell.class.getDeclaredField("boatState");
			f.setAccessible(true);
			assertEquals(BoatState.FLOATING, f.get(c));
			f = Cell.class.getDeclaredField("type");
			f.setAccessible(true);
			assertEquals(ElementType.WATER, f.get(c));
			f = Cell.class.getDeclaredField("image");
			f.setAccessible(true);
			assertNotNull(f.get(c));
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setCoordinateOffset(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateOffset() {
		Coordinate offset = tf.createCoordinate(1, 4);
		Coordinate current = cell.getCoordinate();
		Coordinate newCoordinateWithOffset = current.setX(current.getX() + offset.getX());
		newCoordinateWithOffset = newCoordinateWithOffset.setY(newCoordinateWithOffset.getY() + offset.getY());
		cell.setCoordinateOffset(offset);
		assertEquals(newCoordinateWithOffset, cell.getCoordinate());

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setCoordinateOffset(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateOffsetNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("offset can't be null.");
		cell.setCoordinateOffset(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		Coordinate c = cell.getCoordinate();
		assertEquals(tf.createCoordinate(5, 1), c);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(ElementType.BOAT, cell.getType());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setType(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testSetType() {
		Cell cell = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.BOAT);
		String resourcePathBefore = "";
		String resourcePathAfter = "";
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathBefore = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		cell.setType(ElementType.ICEBERG);
		assertEquals(ElementType.ICEBERG, cell.getType());
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathAfter = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}

		assertNotEquals(resourcePathBefore, resourcePathAfter);

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#getBoatState()}.
	 */
	@Test
	public void testGetBoatState() {
		assertEquals(BoatState.FLOATING, cell.getBoatState());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setBoatState(com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
	@Test
	public void testSetBoatStateOfBoat() {
		Cell cell = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.BOAT);
		String resourcePathBefore = "";
		String resourcePathAfter = "";
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathBefore = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		cell.setBoatState(BoatState.CRASHED);
		assertEquals(BoatState.CRASHED, cell.getBoatState());
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathAfter = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		assertNotEquals(resourcePathBefore, resourcePathAfter);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setBoatState(com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
	@Test
	public void testSetBoatStateOfNonBoat() {
		Cell cell = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.ICEBERG);
		String resourcePathBefore = "";
		String resourcePathAfter = "";
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathBefore = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		cell.setBoatState(BoatState.CRASHED);
		assertEquals(BoatState.CRASHED, cell.getBoatState());
		try {
			Field f = Cell.class.getDeclaredField("resourcePath");
			f.setAccessible(true);
			resourcePathAfter = (String) f.get(cell);
		} catch (Exception e) {
			System.err.println(e);
		}
		assertEquals(resourcePathBefore, resourcePathAfter);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setBoatState(com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
	@Test
	public void testCreateImageWrongResourcePath() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("image for Cell is not found");
		ElementType type = mock(ElementType.class);
		lenient().when(type.toBoatStateString(any(BoatState.class))).thenReturn("thisthingisnotavailable.qmlkdfqmslkfd/qzlijaàz");
		Cell cell = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, type);
		
		cell.setBoatState(BoatState.CRASHED);

	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#setBoatState(com.kuleuven.swop.group17.CoolGameWorld.types.BoatState)}.
	 */
//	@Test(expected=RuntimeException.class)
//	public void testCreateImageIOException() {
//		Cell cell = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
//		
//		try {
//			Field f = Cell.class.getDeclaredField("triggerIOException");
//			f.setAccessible(true);
//			f.set(cell, true);
//		}catch(Exception e) {
//			System.err.println(e);
//		}
//		
//		cell.setBoatState(BoatState.CRASHED);
//
//	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#getImage()}.
	 */
	@Test
	public void testGetImage() {
		Image i = cell.getImage();
		assertNotNull(i);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		Cell c2 = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		
		assertEquals(c, c2);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		
		assertEquals(c, c);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObjectNull() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		
		assertNotEquals(c, null);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsWrongClass() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		
		assertNotEquals(c, ElementType.WATER);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsWrongCoordinate() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		Cell c2 = new Cell(tf.createCoordinate(2, 1), BoatState.FLOATING, ElementType.GOAL);
		
		assertNotEquals(c, c2);
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsCoordinateNull() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		Cell c2 = new Cell(tf.createCoordinate(2, 1), BoatState.FLOATING, ElementType.GOAL);
		
		assertNotEquals(c, c2);
	}
	
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.guiLayer.Cell#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsType() {
		Cell c = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.GOAL);
		Cell c2 = new Cell(tf.createCoordinate(5, 1), BoatState.FLOATING, ElementType.ICEBERG);
		
		assertNotEquals(c, c2);
	}
	
	@Test(expected=RuntimeException.class)
	public void testCreateImageIOException() {
		Cell cell = new Cell(tf.createCoordinate(5, 1) ,null, ElementType.GOAL);
		
		try {
			Field f = Cell.class.getDeclaredField("triggerIOException");
			f.setAccessible(true);
			f.set(cell, true);
		}catch(Exception e) {
			System.err.println(e);
		}
		
		cell.setBoatState(null);

	}
	
	
}
