package org.DesignPattProject.viewer.game;

import org.DesignPattProject.model.elements.Wall;
import org.DesignPattProject.gui.GUI;

public class WallViewer implements IElementViewer<Wall> {

    @Override
    public void draw(Wall wall, GUI gui) {
        gui.drawWall(wall.getPosition());
    }
}
