package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Blinky;
import org.DesignPattProject.gui.GUI;

import org.DesignPattProject.viewer.bridge.GhostViewer;


public class BlinkyViewer implements GhostViewer {
    private Blinky blinky;

    public BlinkyViewer(Blinky blinky) {
        this.blinky = blinky;
    }

    @Override
    public void draw(GUI gui) {
        gui.drawBlinky(blinky);
    }
}