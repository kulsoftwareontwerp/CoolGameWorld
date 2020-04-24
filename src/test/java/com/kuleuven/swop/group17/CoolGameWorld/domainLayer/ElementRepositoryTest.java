package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Boat;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Element;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementFactory;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.Goal;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.IceBerg;
import com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate;
import com.kuleuven.swop.group17.CoolGameWorld.types.ElementType;
import com.kuleuven.swop.group17.CoolGameWorld.types.TypeFactory;


@RunWith(MockitoJUnitRunner.class)
public class ElementRepositoryTest {
	private TypeFactory tf;
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Mock
	private ElementFactory factory;

	@Spy
	private HashSet<Element> elements;

	@InjectMocks
	@Spy
	private ElementRepository repository;

	@Mock
	private Element element;
	@Captor
	private ArgumentCaptor<ElementType> type;
	@Captor
	private ArgumentCaptor<Coordinate> coordinate;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.tf = new TypeFactory();
		fillElements();
		when(factory.createElement(any(ElementType.class), any(Coordinate.class))).thenAnswer(new Answer<Element>() {

			@Override
			public Element answer(InvocationOnMock invocation) throws Throwable {
				return element;
			}

		});
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#ElementRepository()}.
	 */
	@Test
	public void testElementRepository() {
		ElementRepository er = new ElementRepository();
		assertNotNull(er);
		assertEquals(new HashSet<Element>(), er.getElements());
	}

	private void fillElements() {
		IceBerg w1 = mock(IceBerg.class);
		when(w1.getType()).thenReturn(ElementType.ICEBERG);
		when(w1.getCoordinate()).thenReturn(tf.createCoordinate(3, 2));

		IceBerg w2 = mock(IceBerg.class);
		when(w2.getType()).thenReturn(ElementType.ICEBERG);
		when(w2.getCoordinate()).thenReturn(tf.createCoordinate(2, 1));

		IceBerg w3 = mock(IceBerg.class);
		when(w3.getType()).thenReturn(ElementType.ICEBERG);
		when(w3.getCoordinate()).thenReturn(tf.createCoordinate(2, 2));

		IceBerg w4 = mock(IceBerg.class);
		when(w4.getType()).thenReturn(ElementType.ICEBERG);
		when(w4.getCoordinate()).thenReturn(tf.createCoordinate(1, 4));

		Boat r1 = mock(Boat.class);
		when(r1.getType()).thenReturn(ElementType.BOAT);
		when(r1.getCoordinate()).thenReturn(tf.createCoordinate(3, 3));

		Goal g1 = mock(Goal.class);
		when(g1.getType()).thenReturn(ElementType.GOAL);
		when(g1.getCoordinate()).thenReturn(tf.createCoordinate(3, 3));

		elements.clear();
		elements.add(w1);
		elements.add(r1);
		elements.add(w2);
		elements.add(w3);
		elements.add(w4);
		elements.add(g1);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#getElements(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testGetElementsCoordinate() {
		Set<Element> elems = repository.getElements(tf.createCoordinate(3, 2));
		Set<Element> correctSet = elements.stream().filter(s -> s.getCoordinate().equals(tf.createCoordinate(3, 2)))
				.collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));

		elems = repository.getElements(tf.createCoordinate(2, 1));
		correctSet = elements.stream().filter(s -> s.getCoordinate().equals(tf.createCoordinate(2, 1)))
				.collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));

		elems = repository.getElements(tf.createCoordinate(3, 3));
		correctSet = elements.stream().filter(s -> s.getCoordinate().equals(tf.createCoordinate(3, 3)))
				.collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));

		elems = repository.getElements(tf.createCoordinate(1, 4));
		correctSet = elements.stream().filter(s -> s.getCoordinate().equals(tf.createCoordinate(1, 4)))
				.collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));

		elems = repository.getElements(tf.createCoordinate(2, 2));
		correctSet = elements.stream().filter(s -> s.getCoordinate().equals(tf.createCoordinate(2, 2)))
				.collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#getElements(com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testGetElementsCoordinateNoCoordinate() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("Can't retrieve an element with a Coordinate of NULL");
		repository.getElements(null);
		verify(repository.getElements(), times(0));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#addElement(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testAddElement() {
		repository.addElement(ElementType.BOAT, tf.createCoordinate(0, 7));
		verify(factory).createElement(type.capture(), coordinate.capture());
		assertEquals(ElementType.BOAT, type.getValue());
		assertEquals(tf.createCoordinate(0, 7), coordinate.getValue());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#addElement(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testAddElementNoType() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Illegal elementType to create: ");
		repository.addElement(null, tf.createCoordinate(0, 7));
		verify(factory, times(0)).createElement(any(ElementType.class), any(Coordinate.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#addElement(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType, com.kuleuven.swop.group17.CoolGameWorld.types.Coordinate)}.
	 */
	@Test
	public void testAddElementNoCoordinate() {
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Coordinate can't be null");
		repository.addElement(ElementType.BOAT, null);
		verify(factory, times(0)).createElement(any(ElementType.class), any(Coordinate.class));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#getElements()}.
	 */
	@Test
	public void testGetElements() {
		Set<Element> elems = repository.getElements();
		assertEquals(elements.size(), elems.size());
		assertTrue(elems.containsAll(elements));
		assertTrue(elements.containsAll(elems));
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#getGameAreaHeight()}.
	 */
	@Test
	public void testGetGameAreaHeight() {
		assertEquals(11, repository.getGameAreaHeight());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#getGameAreaWidth()}.
	 */
	@Test
	public void testGetGameAreaWidth() {
		assertEquals(5, repository.getGameAreaWidth());
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#getElementsByType(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testGetElementsByType() {
		Set<Element> elems = repository.getElementByType(ElementType.ICEBERG);
		Set<Element> correctSet = elements.stream().filter(s -> s.getType() == ElementType.ICEBERG)
				.collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));

		elems = repository.getElementByType(ElementType.BOAT);
		correctSet = elements.stream().filter(s -> s.getType() == ElementType.BOAT).collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));

		elems = repository.getElementByType(ElementType.GOAL);
		correctSet = elements.stream().filter(s -> s.getType() == ElementType.GOAL).collect(Collectors.toSet());

		assertEquals(correctSet.size(), elems.size());
		assertTrue(elems.containsAll(correctSet));
		assertTrue(correctSet.containsAll(elems));

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#getElementsByType(com.kuleuven.swop.group17.CoolGameWorld.types.ElementType)}.
	 */
	@Test
	public void testGetElementsByTypeNoType() {
		exceptionRule.expect(NullPointerException.class);
		exceptionRule.expectMessage("Can't retrieve an element with a type of NULL");
		repository.getElementByType(null);
		verify(repository.getElements(), times(0));

	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.CoolGameWorld.domainLayer.ElementRepository#clearElements()}.
	 */
	@Test
	public void testClearElements() {
		repository.clearElements();
		assertEquals(0, elements.size());
	}
}
