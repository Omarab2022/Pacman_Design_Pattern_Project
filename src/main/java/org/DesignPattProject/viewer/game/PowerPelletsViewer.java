package org.DesignPattProject.viewer.game;

import org.DesignPattProject.model.elements.PowerPellet;
import org.DesignPattProject.gui.GUI;

public class PowerPelletsViewer implements IElementViewer<PowerPellet> {

    @Override
    public void draw(PowerPellet powerPellet, GUI gui) {
        gui.drawPowerPellet(powerPellet.getPosition());
    }
}
