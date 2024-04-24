package org.DesignPattProject.states;

import org.DesignPattProject.model.arena.Arena;
import org.DesignPattProject.controller.Controller;
import org.DesignPattProject.controller.ArenaController.ArenaController;
import org.DesignPattProject.viewer.Viewer;
import org.DesignPattProject.viewer.game.GameViewer;

public class GameState extends State<Arena> {

    public GameState(Arena model) {
        super(model);
    }

    @Override
    protected Controller<Arena> getController() {
        return new ArenaController(getModel());
    }

    @Override
    public Viewer<Arena> getViewer() {
        return new GameViewer(getModel());
    }
}
