package org.DesignPattProject.controller;

import org.DesignPattProject.Game;
import org.DesignPattProject.gui.GUI;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

        public T getModel() {
        return model;
    }


        public abstract void step(Game game, GUI.ACTION action, long time)
                throws IOException, UnsupportedAudioFileException, LineUnavailableException;
}

