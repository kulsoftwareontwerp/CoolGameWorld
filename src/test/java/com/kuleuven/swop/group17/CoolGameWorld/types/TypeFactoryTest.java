/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;

/**
 * TypeFactoryTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class TypeFactoryTest {
	private TypeFactory tf;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.tf = new TypeFactory();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#createSnapshot(java.util.Set)}.
	 */
	@Test
	public void testCreateSnapshot() {
		CoolGameWorldSnapshot snapshot = tf.createSnapshot(new HashSet<Element>());
		assertNotNull(snapshot);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#createType()}.
	 */
	@Test
	public void testCreateType() {
		CoolGameWorldType type = tf.createType();
		assertNotNull(type);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#createAction(com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld)}.
	 */
	@Test
	public void testCreateAction() {
		CoolGameWorldAction action = tf.createAction(mock(SupportedActions.class));
		assertNotNull(action);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#createPredicate(com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld)}.
	 */
	@Test
	public void testCreatePredicate() {
		CoolGameWorldPredicate predicate = tf.createPredicate(mock(SupportedPredicates.class));
		assertNotNull(predicate);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#createCoordinate(int, int)}.
	 */
	@Test
	public void testCreateCoordinate() {
		Coordinate coordinate = tf.createCoordinate(0,0);
		assertNotNull(coordinate);
	
	}

}
