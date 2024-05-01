package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Pinky;
import org.DesignPattProject.gui.GUI;

import org.DesignPattProject.viewer.bridge.GhostViewer;

public class PinkyViewer implements GhostViewer {
    private Pinky pinky;

    public PinkyViewer(Pinky pinky) {
        this.pinky = pinky;
    }

    @Override
    public void draw(GUI gui) {
        gui.drawPinky(pinky);
    }
}
