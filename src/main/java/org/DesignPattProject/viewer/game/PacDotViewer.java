package org.DesignPattProject.viewer.game;

import org.DesignPattProject.model.elements.PacDot;
import org.DesignPattProject.gui.GUI;

public class PacDotViewer implements IElementViewer<PacDot> {
    @Override
    public void draw(PacDot pacDot, GUI gui) {
        gui.drawPacDot(pacDot.getPosition());
    }
}
