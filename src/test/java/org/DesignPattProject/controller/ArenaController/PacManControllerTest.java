package org.DesignPattProject.controller.ArenaController;

import com.googlecode.lanterna.input.KeyStroke;
import org.junit.jupiter.api.*;
import org.DesignPattProject.Game;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.model.arena.Arena;
import org.DesignPattProject.model.elements.Pacman;
import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.model.elements.Wall;
import org.DesignPattProject.model.elements.ghost.Blinky;
import org.DesignPattProject.model.elements.ghost.ghostStates.HunterState;
import org.DesignPattProject.model.elements.ghost.ghostStrategies.BlinkyStrategy;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class PacManControllerTest {

    private PacmanController controller;
    private Pacman pacman;
    private Arena arena;

    private GUI gui;

    private Game game;
    private KeyStroke keyStroke;

    @BeforeEach
    void setUp() {
        arena = new Arena();
        arena.setSize(20, 20);

        pacman = new Pacman(new Position(10, 10), '0', "src/main/resources/scores/highscore.txt");
        arena.setPacman(pacman);

        controller = new PacmanController(arena);
        keyStroke = mock(KeyStroke.class);

        gui = mock(GUI.class);
        game = mock(Game.class);
    }


    @Test
    public void upDirectionFailed(){
        arena.addWall(new Wall(10, 11));

        controller.movePacman(new Position(10, 11));

        Assertions.assertEquals(new Position(10, 10), arena.getPacman().getPosition());

    }

    @Test
    public void upDirectionDone(){

        controller.movePacman(new Position(10, 11));

        Assertions.assertEquals(new Position(10, 11), arena.getPacman().getPosition());

    }

    @Test
    public void upDirectionTestDone() throws IOException{
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('u', pacman.getDirection());

    }

    @Test
    public void upDirectionTestFailed() throws IOException{
        arena.addWall(new Wall(10, 9));
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('0', pacman.getDirection());
    }

    @Test
    public void downDirectionTestDone() throws IOException{
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('d', pacman.getDirection());

    }

    @Test
    public void downDirectionTestFailed() throws IOException{
        arena.addWall(new Wall(10, 11));
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('0', pacman.getDirection());
    }

    @Test
    public void leftDirectionTestDone() throws IOException{
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.LEFT);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('l', pacman.getDirection());

    }

    @Test
    public void leftDirectionTestFailed() throws IOException{
        arena.addWall(new Wall(9, 10));
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.LEFT);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('0', pacman.getDirection());
    }

    @Test
    public void rightDirectionTestDone() throws IOException{
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.RIGHT);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('r', pacman.getDirection());

    }

    @Test
    public void rightDirectionTestFailed() throws IOException{
        arena.addWall(new Wall(11, 10));
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.RIGHT);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('0', pacman.getDirection());
    }

    @Test
    public void nothingHappens() throws IOException{
        arena.addWall(new Wall(11, 10));
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals('0', pacman.getDirection());
    }

    @Test
    public void ghostCollision() throws IOException {
        Blinky blinky = new Blinky(10, 10, new BlinkyStrategy());
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);

        blinky.setState(new HunterState(blinky));
        pacman.setPosition(new Position(9, 10));
        pacman.setDirection('r');

        arena.setBlinky(blinky);

        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);
        Assertions.assertEquals(pacman.getPosition(), blinky.getPosition());


    }

    @Test
    public void isGhostTest() throws IOException {
        Blinky blinky = new Blinky(10, 10, new BlinkyStrategy());
        Mockito.when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        blinky.setState(new HunterState(blinky));
        pacman.setPosition(new Position(9, 10));
        pacman.setDirection('r');
        arena.setBlinky(blinky);

        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, 500);

        Assertions.assertEquals(blinky, arena.isGhost(pacman.getPosition()));

    }
}
