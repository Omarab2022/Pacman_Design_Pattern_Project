package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Clyde;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.viewer.bridge.ConcreteGhostViewer;

public class ClydeViewer extends ConcreteGhostViewer {
    public ClydeViewer(Clyde clyde, GUI gui) {
        super(clyde, gui);
    }


    protected void drawGhost() {
        gui.drawClyde((Clyde) ghost);
    }
}