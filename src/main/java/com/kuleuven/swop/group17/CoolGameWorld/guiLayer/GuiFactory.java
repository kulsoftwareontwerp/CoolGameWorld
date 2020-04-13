/**
 * 
 */
package com.kuleuven.swop.group17.CoolGameWorld.guiLayer;

/**
 * GuiFactory
 *
 * @version 0.1
 * @author group17
 */
public class GuiFactory {
/**
 * Create a new GuiFactory
 */
	public GuiFactory() {
		super();
	}
	
	/**
	 * Create a new BoatCanvas
	 * @return a new BoatCanvas
	 */
	public BoatCanvas createBoatCanvas() {
		return new BoatCanvas();
	}
}
