package com.kuleuven.swop.group17.CoolGameWorld;

import java.awt.Graphics;

import com.kuleuven.swop.group17.GameWorldApi.Action;
import com.kuleuven.swop.group17.GameWorldApi.GameWorld;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldSnapshot;
import com.kuleuven.swop.group17.GameWorldApi.GameWorldType;
import com.kuleuven.swop.group17.GameWorldApi.Predicate;

public class CoolGameWorld implements GameWorld {

	public Boolean evaluate(Predicate predicate) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This method was not implemented yet.");
	}

	public GameWorldType getType() {
		throw new UnsupportedOperationException("This method was not implemented yet.");
	}

	public void paint(Graphics graphics) {
		throw new UnsupportedOperationException("This method was not implemented yet.");

	}

	public void performAction(Action action) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This method was not implemented yet.");
	}

	public void restoreState(GameWorldSnapshot state) throws IllegalArgumentException {
		throw new UnsupportedOperationException("This method was not implemented yet.");
	}

	public GameWorldSnapshot saveState() {
		throw new UnsupportedOperationException("This method was not implemented yet.");
	}

}
