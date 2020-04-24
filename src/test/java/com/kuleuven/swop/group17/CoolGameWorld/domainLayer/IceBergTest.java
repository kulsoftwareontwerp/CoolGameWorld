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

import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;


@RunWith(MockitoJUnitRunner.class)
public class IceBergTest {
	private TypeFactory tf;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private Coordinate coordinate;
	@Spy
	@InjectMocks
	private IceBerg iceBerg;

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
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(ElementType.ICEBERG, iceBerg.getType());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 3));
		assertTrue(g.equals(g));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsNull() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 3));
		assertFalse(g.equals(null));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherClass() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 3));
		assertFalse(g.equals(ElementType.WATER));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsCoordinate() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 3));
		IceBerg g2 = new IceBerg(tf.createCoordinate(3, 2));
		assertFalse(g.equals(g2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherElementClass() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 3));
		Boat w = new Boat(tf.createCoordinate(3, 2));
		assertFalse(g.equals(w));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#equals(java.lang.Object)}.
	 */
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 3));
		IceBerg g2 = new IceBerg(tf.createCoordinate(5, 3));

		assertTrue(g.equals(g2));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsNegativeNotIceBerg() {
		Boat b = new Boat(tf.createCoordinate(5, 3));
		IceBerg g2 = new IceBerg(tf.createCoordinate(5, 3));
		assertFalse(g2.equals(b));
	}
	
	@Test
	public void testEqualsNegativeNotObject() {
		IceBerg g2 = new IceBerg(tf.createCoordinate(5, 3));
		assertFalse(g2.equals(2));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.IceBerg#IceBerg(com.kuleuven.swop.group17.BoatGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testIceBerg() {
		IceBerg w = new IceBerg(tf.createCoordinate(5, 3));
		assertNotNull(w);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		IceBerg g = new IceBerg(tf.createCoordinate(5, 1));
		IceBerg g2 = new IceBerg(tf.createCoordinate(5, 3));
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
		iceBerg.setCoordinate(null);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.BoatGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinate() {
		iceBerg.setCoordinate(tf.createCoordinate(5, 3));
		assertEquals(tf.createCoordinate(5, 3), iceBerg.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.BoatGameWorld.domainLayer.Element#clone()}.
	 */
	@Test
	public void testClone() {
		IceBerg w = new IceBerg(tf.createCoordinate(5, 3));
		IceBerg clone = (IceBerg) w.clone();
		assertTrue(clone != w);
		assertTrue(clone.equals(w));
	}

}
