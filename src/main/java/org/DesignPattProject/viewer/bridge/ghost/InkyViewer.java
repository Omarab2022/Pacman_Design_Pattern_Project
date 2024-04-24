package org.DesignPattProject.viewer.bridge.ghost;

import org.DesignPattProject.model.elements.ghost.Inky;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.viewer.bridge.ConcreteGhostViewer;

public class InkyViewer extends ConcreteGhostViewer {
    public InkyViewer(Inky inky, GUI gui) {
        super(inky, gui);
    }


    protected void drawGhost() {
        gui.drawInky((Inky) ghost);
    }
}
