/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.DomainFactory;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementFactory;

/**
 * CoolGameWorldSnapshotTest
 *
 * @version 0.1
 * @author group17
 */
@RunWith(MockitoJUnitRunner.class)
public class CoolGameWorldSnapshotTest {
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
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#CoolGameWorldSnapshot(java.util.Set)}.
	 */
	@Test
	public void testCoolGameWorldSnapshot() {
		CoolGameWorldSnapshot snapshot = new CoolGameWorldSnapshot(new HashSet<Element>());
		try {
			Field f = CoolGameWorldSnapshot.class.getDeclaredField("elements");
			f.setAccessible(true);
			assertTrue("elements were not initialised",
					 f.get(snapshot) != null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			fail("One or more of the required fields were not declared.");
		}
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#CoolGameWorldSnapshot(java.util.Set)}.
	 */
	@Test
	public void testCoolGameWorldSnapshotNullElements() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("The given elements can't be null");
		CoolGameWorldSnapshot snapshot = new CoolGameWorldSnapshot(null);
	}
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#getElements()}.
	 */
	@Test
	public void testGetElements() {
		Set<Element> elements = constructState();
		
		CoolGameWorldSnapshot snapshot = new CoolGameWorldSnapshot(elements); 
		
		assertEquals(elements, snapshot.getElements());
		
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#getElements()}.
	 */
	@Test
	public void testGetElementsImmutableList() {
		Set<Element> elements = constructState();
		DomainFactory df = new DomainFactory();
		ElementFactory ef = df.createElementFactory();
		Element e = ef.createElement(ElementType.ICEBERG, new Coordinate(3, 4));
		elements.add(e);
		
		
		CoolGameWorldSnapshot snapshot = new CoolGameWorldSnapshot(elements); 
		elements.remove(e);

		
		assertNotEquals(elements, snapshot.getElements());
		
	}
	
	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.types.CoolGameWorld#getElements()}.
	 */
	@Test
	public void testGetElementsImmutableElement() {
		Set<Element> elements = constructState();
		DomainFactory df = new DomainFactory();
		ElementFactory ef = df.createElementFactory();
		Element e = ef.createElement(ElementType.ICEBERG, new Coordinate(3, 4));
		elements.add(e);
		
		
		CoolGameWorldSnapshot snapshot = new CoolGameWorldSnapshot(elements); 
		elements.remove(e);
		e.setCoordinate(new Coordinate(5,3));
		elements.add(e);
		
		assertNotEquals(elements, snapshot.getElements());
		
	}
	

	private Set<Element> constructState() {
		DomainFactory df = new DomainFactory();
		ElementFactory ef = df.createElementFactory();
		HashSet<Element> elements = new HashSet<Element>();
		elements.add(ef.createElement(ElementType.GOAL, new Coordinate(5, 3)));
		elements.add(ef.createElement(ElementType.ICEBERG, new Coordinate(9, 0)));
		elements.add(ef.createElement(ElementType.BOAT, new Coordinate(8, 2)));
		elements.add(ef.createElement(ElementType.ICEBERG, new Coordinate(3, 4)));
		return elements;
	}

}
