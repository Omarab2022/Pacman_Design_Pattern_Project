package org.DesignPattProject.controller.ArenaController;

import org.DesignPattProject.Game;
import org.DesignPattProject.model.arena.Arena;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.menu.Menu;
import org.DesignPattProject.sound.SoundFX;
import org.DesignPattProject.states.MenuState;
import java.io.IOException;

public class ArenaController extends GameController{

    private final GhostController ghostController;
    private final PacmanController pacmanController;

    public ArenaController(Arena arena) {
        super(arena);
        this.ghostController = new GhostController(arena);
        this.pacmanController = new PacmanController(arena);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT) {
            SoundFX.stopGameSounds();
            game.setState(new MenuState(new Menu()));
        } else {
            ghostController.step(game, action, time);
            pacmanController.step(game, action, time);
        }
    }
}
