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

import org.DesignPattProject.viewer.bridge.GhostViewer;
import org.DesignPattProject.viewer.bridge.GhostViewerBridge;
import org.DesignPattProject.viewer.bridge.ghost.BlinkyViewer;
import org.DesignPattProject.viewer.bridge.ghost.ClydeViewer;
import org.DesignPattProject.viewer.bridge.ghost.InkyViewer;
import org.DesignPattProject.viewer.bridge.ghost.PinkyViewer;

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
        GhostViewerBridge blinkyViewer = new GhostViewerBridge(new BlinkyViewer(this.getModel().getBlinky()));
        GhostViewerBridge inkyViewer = new GhostViewerBridge(new InkyViewer(this.getModel().getInky()));
        GhostViewerBridge pinkyViewer = new GhostViewerBridge(new PinkyViewer(this.getModel().getPinky()));
        GhostViewerBridge clydeViewer = new GhostViewerBridge(new ClydeViewer(this.getModel().getClyde()));


        for (Wall wall : walls) {
            wallViewer.draw(wall, gui);
        }

        for (PacDot pacDot : pacDots) {
            pacDotViewer.draw(pacDot, gui);
        }

        for (PowerPellet powerPellet : powerPellets) {
            powerPelletsViewer.draw(powerPellet, gui);
        }

        blinkyViewer.draw(gui);
        inkyViewer.draw(gui);
        pinkyViewer.draw(gui);
        clydeViewer.draw(gui);

        pacmanViewer.draw(pacman, gui);
    }
}
