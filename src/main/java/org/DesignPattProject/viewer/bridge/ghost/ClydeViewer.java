package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Clyde;
import org.DesignPattProject.gui.GUI;

import org.DesignPattProject.viewer.bridge.GhostViewer;

public class ClydeViewer implements GhostViewer {
    private Clyde clyde;

    public ClydeViewer(Clyde clyde) {
        this.clyde = clyde;
    }

    @Override
    public void draw(GUI gui) {
        gui.drawClyde(clyde);
    }
}