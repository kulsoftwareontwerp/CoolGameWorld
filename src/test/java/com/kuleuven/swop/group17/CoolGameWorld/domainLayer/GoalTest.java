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
public class GoalTest {
	private TypeFactory tf;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Mock
	private Coordinate coordinate;
	@Spy
	@InjectMocks
	private Goal goal;
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
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals(ElementType.GOAL, goal.getType());
}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		assertTrue(g.equals(g));
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsNull() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		assertFalse(g.equals(null));
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherClass() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		assertFalse(g.equals(ElementType.WATER));
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsCoordinate() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		Goal g2 = new Goal(tf.createCoordinate(3, 2));
		assertFalse(g.equals(g2));
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsOtherElementClass() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		IceBerg w = new IceBerg(tf.createCoordinate(3, 2));
		assertFalse(g.equals(w));
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsType() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		Goal g2 = mock(Goal.class);
		assertFalse(g.equals(g2));
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEqualsClassVsType() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		IceBerg g2 = mock(IceBerg.class);
		assertFalse(g.equals(g2));
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		Goal g2 = new Goal(tf.createCoordinate(5,3));
		
		assertTrue(g.equals(g2));
	}
	
	
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Goal#Goal(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testGoal() {
		Goal g = new Goal(tf.createCoordinate(5,3));
		assertNotNull(g);
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Goal g = new Goal(tf.createCoordinate(5,1));
		Goal g2 = new Goal(tf.createCoordinate(5,3));
		assertNotEquals(g.hashCode(), g2.hashCode());
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#getCoordinate()}.
	 */
	@Test
	public void testGetCoordinate() {
		IceBerg g = new IceBerg(tf.createCoordinate(5,3));
		assertEquals(tf.createCoordinate(5,3), g.getCoordinate());
	}

	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinateNull() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Coordinate can't be null");
		goal.setCoordinate(null);
	}
	/**
	 * Test method for {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#setCoordinate(com.kuleuven.swop.group17.RobotGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testSetCoordinate() {
		goal.setCoordinate(tf.createCoordinate(5,3));
		assertEquals(tf.createCoordinate(5,3), goal.getCoordinate());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.Element#clone()}.
	 */
//	@Test(expected = RuntimeException.class)
//	public void testCloneCloneNotSupported() {
//		Goal w = new Goal(tf.createCoordinate(5, 3));
//		try {
//			Field f = Element.class.getDeclaredField("cloneSupported");
//			f.setAccessible(true);
//			f.set(w, false);
//		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		w.clone();
//
//	}
}
