package org.DesignPattProject.viewer.game;

import org.DesignPattProject.model.arena.Arena;
import org.DesignPattProject.model.elements.PacDot;
import org.DesignPattProject.model.elements.Pacman;
import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.model.elements.PowerPellet;
import org.DesignPattProject.model.elements.Wall;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.sound.SoundFX;
import org.DesignPattProject.viewer.Viewer;
import org.DesignPattProject.viewer.bridge.ConcreteGhostViewer;
import org.DesignPattProject.viewer.bridge.GhostViewer;
import java.util.List;

public class GameViewer extends Viewer<Arena> {
    public GameViewer(Arena model) {
        super(model);
        SoundFX.playStartUp();
    }

    @Override
    protected void drawElements(GUI gui) {
        gui.drawText(new Position(0, 0),
                "SCORE:" + this.getModel().getPacman().getScore(), "#FFFFFF");
        gui.drawText(new Position(12, 0),
                "HIGHSCORE:" + this.getModel().getPacman().getHighScore(), "#FFFFFF");
        gui.drawText(new Position(0, 32), "Q to quit", "#FFFFFF");

        List<Wall> walls = this.getModel().getWalls();
        List<PacDot> pacDots = this.getModel().getPacDots();
        List<PowerPellet> powerPellets = this.getModel().getPowerPellets();
        Pacman pacman = this.getModel().getPacman();

        PacmanViewer pacmanViewer = new PacmanViewer();
        WallViewer wallViewer = new WallViewer();
        PacDotViewer pacDotViewer = new PacDotViewer();
        PowerPelletsViewer powerPelletsViewer = new PowerPelletsViewer();

        // Utilisation du pattern Bridge pour afficher les fantômes
        GhostViewer blinkyViewer = new ConcreteGhostViewer(this.getModel().getBlinky(), gui);
        GhostViewer inkyViewer = new ConcreteGhostViewer(this.getModel().getInky(), gui);
        GhostViewer pinkyViewer = new ConcreteGhostViewer(this.getModel().getPinky(), gui);
        GhostViewer clydeViewer = new ConcreteGhostViewer(this.getModel().getClyde(), gui);

        for (Wall wall : walls) {
            wallViewer.draw(wall, gui);
        }

        for (PacDot pacDot : pacDots) {
            pacDotViewer.draw(pacDot, gui);
        }

        for (PowerPellet powerPellet : powerPellets) {
            powerPelletsViewer.draw(powerPellet, gui);
        }

        // Dessiner les fantômes
        blinkyViewer.draw();
        inkyViewer.draw();
        pinkyViewer.draw();
        clydeViewer.draw();

        pacmanViewer.draw(pacman, gui);
    }
}
