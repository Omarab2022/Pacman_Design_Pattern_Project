package org.DesignPattProject.gui;

import org.DesignPattProject.model.elements.Pacman;
import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.model.elements.ghost.*;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawPacman(Pacman pacman);

    void drawWall(Position position);

    void drawPacDot(Position position);

    void drawPowerPellet(Position position);

    void drawBlinky(Blinky blinky);

    void drawPinky(Pinky pinky);

    void drawInky(Inky inky);

    void drawClyde(Clyde clyde);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    public void drawGhost(Ghost ghost);

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
