package org.DesignPattProject.states;

import org.DesignPattProject.Game;
import org.DesignPattProject.controller.Controller;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.viewer.Viewer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public abstract class State<T> {

    private final T model;
    private final Viewer<T> viewer;
    private final Controller<T> controller;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Controller<T> getController();

    public T getModel() {
        return this.model;
    }

    public abstract Viewer<T> getViewer();

    public void step(Game game, GUI gui, long time)
            throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GUI.ACTION action = gui.getNextAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}
