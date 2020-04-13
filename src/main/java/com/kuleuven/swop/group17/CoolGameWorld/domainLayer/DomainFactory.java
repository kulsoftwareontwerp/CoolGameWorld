/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.domainLayer;

/**
 * DomainFactory
 *
 * @version 0.1
 * @author group17
 */
public class DomainFactory {
	/**
	 * Create a new DomainFactory
	 */
	public DomainFactory() {

	}

	/**
	 * Create a new elementRepository
	 * 
	 * @return a new ElementRepository
	 */
	public ElementRepository createElementRepository() {
		return new ElementRepository();
	}

	/**
	 * Create a new ElementFactory
	 * 
	 * @return a new ElementFactory
	 */
	public ElementFactory createElementFactory() {
		return new ElementFactory();
	}
}
