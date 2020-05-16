package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.CoolGameWorld.types.BoatState;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;



@RunWith(MockitoJUnitRunner.class)
public class BoatTest {
	private TypeFactory tf;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Mock
	private Coordinate coordinate;
	@Spy
	@InjectMocks
	private Boat boat;

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
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(ElementType.BOAT, boat.getType());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		assertTrue(g.equals(g));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsNull() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		assertFalse(g.equals(null));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherSuperClass() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		assertFalse(g.equals(ElementType.WATER));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsNotEqualCoordinates() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		Boat g2 = new Boat(tf.createCoordinate(3, 2));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherElementClass() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		IceBerg w = new IceBerg(tf.createCoordinate(3, 2));
		assertFalse(g.equals(w));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsType() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		Boat g2 = mock(Boat.class);
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsClassVsType() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		IceBerg g2 = mock(IceBerg.class);
		when(g2.getType()).thenReturn(ElementType.BOAT);
		when(g2.getCoordinate()).thenReturn(tf.createCoordinate(5, 3));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsBoatState() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		g.setBoatState(BoatState.FLOATING);
		Boat g2 = new Boat(tf.createCoordinate(5, 3));
		g2.setBoatState(BoatState.CRASHED);
		assertFalse(g.equals(g2));//Werkt
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		g.setBoatState(BoatState.CRASHED);
		Boat g2 = new Boat(tf.createCoordinate(5, 3));
		g2.setBoatState(BoatState.CRASHED);

		assertTrue(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#Boat(com.kuleuven.swop.group17.BoatGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testBoat() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		assertNotNull(g);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#getBoatState()}.
	 */
	@Test
	public void testGetBoatState() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		g.setBoatState(BoatState.FLOATING);

		assertEquals(BoatState.FLOATING, g.getBoatState());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#setBoatState(com.kuleuven.swop.group17.BoatGameWorld.types.BoatState)}.
	 */
	@Test
	public void testSetBoatState() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		g.setBoatState(BoatState.FLOATING);
		
		assertEquals(BoatState.FLOATING, g.getBoatState());
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Boat#setBoatState(com.kuleuven.swop.group17.BoatGameWorld.types.BoatState)}.
	 */
	@Test
	public void testSetBoatStateNull() {
		Boat g = new Boat(tf.createCoordinate(5, 3));
		g.setBoatState(null);
		
		
		assertEquals(BoatState.FLOATING, g.getBoatState());
		
		
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Boat g = new Boat(tf.createCoordinate(5, 1));
		Boat g2 = new Boat(tf.createCoordinate(5, 3));
		assertNotEquals(g.hashCode(), g2.hashCode());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 3));
		assertEquals(tf.createCoordinate(5, 3), g.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.BoatGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Coordinate can't be null");
		boat.setCoordinate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.BoatGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinate() {
		boat.setCoordinate(tf.createCoordinate(5, 3));
		assertEquals(tf.createCoordinate(5, 3), boat.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#clone()}.
	 */
	@Test(expected = RuntimeException.class)
	public void testCloneCloneNotSupported() {
		Boat w = new Boat(tf.createCoordinate(5, 3));
		try {
			Field f = Element.class.getDeclaredField("cloneSupported");
			f.setAccessible(true);
			f.set(w, false);
		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
			e.printStackTrace();
		}
		w.clone();

	}
}
