package org.DesignPattProject.model.elements.ghostState;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.DesignPattProject.model.elements.ghost.Blinky;
import org.DesignPattProject.model.elements.ghost.Ghost;
import org.DesignPattProject.model.elements.ghost.ghostStates.*;
import org.DesignPattProject.model.elements.ghost.ghostStrategies.BlinkyStrategy;

public class StateTest {

    IGhostState state;
    Ghost ghost;


    @BeforeEach
    public void setUp(){
        ghost = new Blinky(1, 1, new BlinkyStrategy());
    }

    @Test
    public void increaseTimerHunterTest(){
        ghost.setState(new HunterState(this.ghost));
        state = ghost.getState();
        Assertions.assertEquals(0, state.getTimer());
        state.increaseTimer();
        Assertions.assertEquals(0, state.getTimer());
    }

    @Test
    public void increaseTimerEatenTest(){
        ghost.setState(new EatenState(this.ghost));
        state = ghost.getState();
        Assertions.assertEquals(0, state.getTimer());
        state.increaseTimer();
        Assertions.assertEquals(0, state.getTimer());
    }

    @Test
    public void isBeingChasedFalseTest(){
        ghost.setState(new EatenState(this.ghost));
        state = ghost.getState();
        Assertions.assertFalse(this.state.isBeingChased());
    }

    @Test
    public void increaseTimerHouseTest(){
        ghost.setState(new HouseState(this.ghost));
        state = ghost.getState();
        Assertions.assertEquals(0, state.getTimer());
        state.increaseTimer();
        Assertions.assertEquals(1, state.getTimer());
    }

    @Test
    public void increaseTimerChasedTest(){
        ghost.setState(new ChasedState(this.ghost));
        state = ghost.getState();
        Assertions.assertEquals(0, state.getTimer());
        state.increaseTimer();
        Assertions.assertEquals(1, state.getTimer());
    }

    @Test
    public void isBeingChasedTrueTest(){
        ghost.setState(new ChasedState(this.ghost));
        state = ghost.getState();
        Assertions.assertTrue(this.state.isBeingChased());
    }
}
