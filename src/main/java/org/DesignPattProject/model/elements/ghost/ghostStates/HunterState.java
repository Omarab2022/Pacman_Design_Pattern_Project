package org.DesignPattProject.model.elements.ghost.ghostStates;

import org.DesignPattProject.model.elements.ghost.Ghost;
import org.DesignPattProject.sound.SoundFX;

public class HunterState implements IGhostState {
    private final Ghost ghost;
    public HunterState(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public void powerPelletEaten() {
        this.ghost.setState(new ChasedState(this.ghost));
    }

    @Override
    public void pacManCollision() {
        SoundFX.stopGameSounds();
        this.ghost.setState(new HunterState(this.ghost));
    }

    @Override
    public boolean isBeingChased() {
        return false;
    }

    @Override
    public int getTimer() {
        return 0;
    }

    @Override
    public void increaseTimer() {}
}
