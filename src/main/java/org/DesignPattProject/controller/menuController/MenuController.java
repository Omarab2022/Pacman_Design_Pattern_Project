package org.DesignPattProject.controller.menuController;

import org.DesignPattProject.Game;
import org.DesignPattProject.model.arena.Arena;
import org.DesignPattProject.model.arena.ArenaFactory;
import org.DesignPattProject.controller.Controller;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.menu.Menu;
import org.DesignPattProject.sound.SoundFX;
import org.DesignPattProject.states.GameState;
import java.io.IOException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        SoundFX.loopMenuTheme();
        switch (action) {
            case UP -> this.getModel().previousEntry();
            case DOWN -> this.getModel().nextEntry();
            case SELECT -> {
                if (getModel().exitSelected()) {
                    SoundFX.stopMenuSounds();
                    game.setState(null);
                }
                if (getModel().startSelected()) {
                    SoundFX.stopMenuSounds();
                    ArenaFactory temp = new ArenaFactory();
                    Arena arena = temp.createArena("maps/map.txt");
                    game.setState(new GameState(arena));
                }
            }
        }
    }
}
