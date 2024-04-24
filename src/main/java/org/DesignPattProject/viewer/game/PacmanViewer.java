package org.DesignPattProject.viewer.game;

import org.DesignPattProject.model.elements.Pacman;
import org.DesignPattProject.gui.GUI;

public class PacmanViewer implements IElementViewer<Pacman> {

    @Override
    public void draw(Pacman pacman, GUI gui) {
        gui.drawPacman(pacman);
    }
}
