package org.DesignPattProject.model.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.DesignPattProject.model.elements.ghost.Blinky;
import org.DesignPattProject.model.elements.ghost.Clyde;
import org.DesignPattProject.model.elements.ghost.Inky;
import org.DesignPattProject.model.elements.ghost.Pinky;
import org.DesignPattProject.model.elements.ghost.ghostStates.ChasedState;
import org.DesignPattProject.model.elements.ghost.ghostStates.EatenState;
import org.DesignPattProject.model.elements.ghost.ghostStates.HouseState;
import org.DesignPattProject.model.elements.ghost.ghostStates.HunterState;
import org.DesignPattProject.model.elements.ghost.ghostStrategies.BlinkyStrategy;
import org.DesignPattProject.model.elements.ghost.ghostStrategies.ClydeStrategy;
import org.DesignPattProject.model.elements.ghost.ghostStrategies.InkyStrategy;
import org.DesignPattProject.model.elements.ghost.ghostStrategies.PinkyStrategy;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class GhostTest {

    @Test
    public void PositionTest(){
        Blinky blinky = new Blinky(5, 10, new BlinkyStrategy());
        Position expected = new Position(5, 10);
        Assertions.assertEquals(expected, blinky.getPosition());
    }

    @Test
    public void BlinkyStrategyTest(){
        Blinky blinky = new Blinky(10, 10, new BlinkyStrategy());
        Assertions.assertEquals(blinky.getStrategy().getClass(), BlinkyStrategy.class);
    }

    @Test
    public void ClydeStrategyTest(){
        Clyde clyde = new Clyde(10, 10, new ClydeStrategy());
        Assertions.assertEquals(clyde.getStrategy().getClass(), ClydeStrategy.class);
    }

    @Test
    public void InkyStrategyTest(){
        Inky inky = new Inky(10, 10, new InkyStrategy());
        Assertions.assertEquals(inky.getStrategy().getClass(), InkyStrategy.class);
    }

    @Test
    public void PinkyStrategyTest(){
        Pinky pinky = new Pinky(10, 10, new PinkyStrategy());
        Assertions.assertEquals(pinky.getStrategy().getClass(), PinkyStrategy.class);
    }

    @Test
    public void setStrategyTest(){
        Pinky pinky = new Pinky(10, 10, new PinkyStrategy());
        pinky.setStrategy(new BlinkyStrategy());
        Assertions.assertEquals(pinky.getStrategy().getClass(), BlinkyStrategy.class);
    }

    @Test
    public void powerPelletHouseState(){
        Clyde clyde = new Clyde(10, 10, new ClydeStrategy());
        clyde.powerPelletEaten();
        Assertions.assertEquals(clyde.getState().getClass(), HouseState.class);
    }

    @Test
    public void powerPelletHunterState(){
        Clyde clyde = new Clyde(10, 10, new ClydeStrategy());
        clyde.setState(new HunterState(clyde));
        clyde.powerPelletEaten();
        Assertions.assertEquals(clyde.getState().getClass(), ChasedState.class);
    }

    @Test
    public void powerPelletChasedState(){
        Clyde clyde = new Clyde (10, 10, new ClydeStrategy());
        clyde.setState(new ChasedState(clyde));
        clyde.powerPelletEaten();
        Assertions.assertEquals(clyde.getState().getClass(), ChasedState.class);
    }

    @Test
    public void powerPelletEatenState(){
        Clyde clyde = new Clyde(10, 10, new ClydeStrategy());
        clyde.setState(new EatenState(clyde));
        clyde.powerPelletEaten();
        Assertions.assertEquals(clyde.getState().getClass(), HunterState.class);
    }

    @Test
    public void pacManCollisionChasedState(){
        Inky inky = new Inky(10, 10, new InkyStrategy());
        inky.setState(new ChasedState(inky));
        inky.pacManCollision();
        Assertions.assertEquals(inky.getState().getClass(), EatenState.class);
    }

    @Test
    public void pacManCollisionEatenState(){
        Inky inky = new Inky(10, 10, new InkyStrategy());
        inky.setState(new EatenState(inky));
        inky.pacManCollision();
        Assertions.assertEquals(inky.getState().getClass(), EatenState.class);
    }

    @Test
    public void pacManCollisionHouseState(){
        Inky inky = new Inky(10, 10, new InkyStrategy());
        inky.setState(new HouseState(inky));
        inky.pacManCollision();
        Assertions.assertEquals(inky.getState().getClass(), HouseState.class);
    }

    @Test
    public void pacManCollisionHunterState(){
        Inky inky = new Inky (10, 10, new InkyStrategy());
        inky.setState(new HunterState(inky));
        inky.pacManCollision();
        Assertions.assertEquals(inky.getState().getClass(), HunterState.class);
    }

    @Test
    public void equalsNullTest(){
        Pinky pinky1 = null;
        Pinky pinky2 = new Pinky(10,10, new PinkyStrategy());
        Assertions.assertFalse(pinky2.equals(pinky1));
    }

    @Test
    public void equalsDifferentClassTest(){
        Inky inky = new Inky(10, 10, new InkyStrategy());
        Pinky pinky = new Pinky(10,10, new PinkyStrategy());
        Assertions.assertFalse(pinky.equals(inky));
    }

    @Test
    public void chasedStateTest(){
        Blinky blinky = new Blinky(1, 2, new BlinkyStrategy());
        Pacman pacman = mock(Pacman.class);
        Mockito.when(pacman.getPosition()).thenReturn(new Position(5,6));
        List<Position> neighbours = new ArrayList<>();
        neighbours.add(new Position(2, 2));
        neighbours.add(new Position(0, 2));
        neighbours.add(new Position(1, 3));
        neighbours.add(new Position(1, 1));
        blinky.setState(new ChasedState(blinky));
        Assertions.assertNotEquals(blinky.getStrategy(), blinky.nextMove(neighbours, pacman));
    }

}
