package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Pinky;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.viewer.bridge.ConcreteGhostViewer;

public class PinkyViewer extends ConcreteGhostViewer {
    public PinkyViewer(Pinky pinky, GUI gui) {
        super(pinky, gui);
    }


    protected void drawGhost() {
        gui.drawPinky((Pinky) ghost);
    }
}
