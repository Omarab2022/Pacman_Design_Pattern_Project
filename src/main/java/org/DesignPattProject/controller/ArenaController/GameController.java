package org.DesignPattProject.controller.ArenaController;

import org.DesignPattProject.model.arena.Arena;
import org.DesignPattProject.controller.Controller;

public abstract class GameController extends Controller<Arena> {
    public GameController(Arena arena) {
        super(arena);
    }
}
