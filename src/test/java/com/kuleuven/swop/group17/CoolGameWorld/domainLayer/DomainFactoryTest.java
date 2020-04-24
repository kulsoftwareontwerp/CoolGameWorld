package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class DomainFactoryTest {
	@Spy
	private DomainFactory df;

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
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.DomainFactory#DomainFactory()}.
	 */
	@Test
	public void testDomainFactory() {
		DomainFactory df = new DomainFactory();
		assertNotNull(df);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.DomainFactory#createElementRepository()}.
	 */
	@Test
	public void testCreateElementRepository() {
		ElementRepository er= df.createElementRepository();
		assertNotNull(er);
	}

	/**
	 * Test method for
	 * {@link com.kuleuven.swop.group17.RobotGameWorld.domainLayer.DomainFactory#createElementFactory()}.
	 */
	@Test
	public void testCreateElementFactory() {
		ElementFactory ef= df.createElementFactory();
		assertNotNull(ef);	}
}
