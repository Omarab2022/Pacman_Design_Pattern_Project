package org.DesignPattProject.viewer.bridge;

import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.model.elements.ghost.*;

public class ConcreteGhostViewer implements GhostViewer {
	protected Ghost ghost;
	protected GUI gui;

	public ConcreteGhostViewer(Ghost ghost, GUI gui) {
		this.ghost = ghost;
		this.gui = gui;
	}

	@Override
	public void draw() {
		if (ghost instanceof Blinky) {
			gui.drawBlinky((Blinky) ghost);
		} else if (ghost instanceof Inky) {
			gui.drawInky((Inky) ghost);
		} else if (ghost instanceof Pinky) {
			gui.drawPinky((Pinky) ghost);
		} else if (ghost instanceof Clyde) {
			gui.drawClyde((Clyde) ghost);
		}
	}
}
