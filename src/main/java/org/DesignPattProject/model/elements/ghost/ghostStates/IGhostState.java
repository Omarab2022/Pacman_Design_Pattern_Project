package org.DesignPattProject.model.elements.ghost.ghostStates;

public interface IGhostState {

    void powerPelletEaten();

    void pacManCollision();

    boolean isBeingChased();

    int getTimer();

    void increaseTimer();
}
