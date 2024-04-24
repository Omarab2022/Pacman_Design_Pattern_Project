package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Blinky;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.viewer.bridge.ConcreteGhostViewer;

public class BlinkyViewer extends ConcreteGhostViewer {
    public BlinkyViewer(Blinky blinky, GUI gui) {
        super(blinky, gui);
    }


    protected void drawGhost() {
        gui.drawBlinky((Blinky) ghost);
    }
}
