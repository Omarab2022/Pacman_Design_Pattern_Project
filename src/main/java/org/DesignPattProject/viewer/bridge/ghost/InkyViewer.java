package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Inky;
import org.DesignPattProject.gui.GUI;

import org.DesignPattProject.viewer.bridge.GhostViewer;

public class InkyViewer implements GhostViewer {
    private Inky inky;

    public InkyViewer(Inky inky) {
        this.inky = inky;
    }

    @Override
    public void draw(GUI gui) {
        gui.drawInky(inky);
    }
}