package org.DesignPattProject.controller.ArenaController;

import org.DesignPattProject.Game;
import org.DesignPattProject.model.arena.Arena;
import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.model.elements.ghost.Ghost;
import org.DesignPattProject.model.elements.ghost.ghostStates.ChasedState;
import org.DesignPattProject.model.elements.ghost.ghostStates.EatenState;
import org.DesignPattProject.model.elements.ghost.ghostStates.HouseState;
import org.DesignPattProject.model.elements.ghost.ghostStates.HunterState;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.sound.SoundFX;
import java.util.List;

public class GhostController extends GameController {

    private long lastMovement;

    public GhostController(Arena arena) {
        super(arena);
        this.lastMovement = 0;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        List<Position> neighbours;
        if (time - lastMovement > 150) {
            for (Ghost ghost : this.getModel().getGhosts()) {
                try {
                    neighbours = ghost.getAllNeighbours();
                    neighbours.removeIf(n -> !getModel().isEmpty(n));
                    Position temp = ghost.nextMove(neighbours, this.getModel().getPacman());
                    moveGhost(ghost, temp);
                } catch (NullPointerException ignored) {}
            }
            this.lastMovement = time;
        }
    }

    private void moveGhost(Ghost ghost, Position position) {
        if (ghost.getState().getClass() == EatenState.class) {
            ghost.setPosition(this.getModel().getHouseSpawn());
            ghost.setState(new HouseState(ghost));
        } else if (ghost.getState().getClass() == HouseState.class) {
            if (ghost.getState().getTimer() > HouseState.MAX_TIME) {
                ghost.setPosition(this.getModel().getHunterSpawn());
                ghost.setState(new HunterState(ghost));
            }
            else {
                ghost.setPosition(position);
                ghost.getState().increaseTimer();
            }
        } else if (ghost.getState().getClass() == ChasedState.class) {
            if (ghost.getState().getTimer() > ChasedState.MAX_TIME) {
                SoundFX.loopGhostSiren1();
                ghost.setState(new HunterState(ghost));
            }
            ghost.getState().increaseTimer();
            if(position.getX() <= 0 || position.getY() <= 0)
                ghost.setPosition(this.getModel().getRightPortal());
            else if(position.getY() >= this.getModel().getHeight() - 1 || position.getX() >= this.getModel().getWidth()-1)
                ghost.setPosition(this.getModel().getLeftPortal());
            else ghost.setPosition(position);
        } else {
            if (position.getX() <= 0 || position.getY() <= 0)
                ghost.setPosition(this.getModel().getRightPortal());
            else if (position.getY() >= this.getModel().getHeight() - 1 || position.getX() >= this.getModel().getWidth()-1)
                ghost.setPosition(this.getModel().getLeftPortal());
            else ghost.setPosition(position);

            if (this.getModel().getPacman().getPosition().equals(ghost.getPosition())) {
                ghost.getState().pacManCollision();
            }
        }
    }
}

