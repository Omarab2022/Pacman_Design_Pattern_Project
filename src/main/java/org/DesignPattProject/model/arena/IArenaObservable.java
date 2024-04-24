package org.DesignPattProject.model.arena;

import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.model.elements.ghost.Ghost;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface IArenaObservable {

    void powerPelletEaten();

    Ghost isGhost(Position position) throws UnsupportedAudioFileException, LineUnavailableException, IOException;
}
