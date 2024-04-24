package org.DesignPattProject.model.elements.ghost.ghostStrategies;

import org.DesignPattProject.model.elements.Pacman;
import org.DesignPattProject.model.elements.Position;

import java.util.List;

public interface IGhostStrategy {
    Position nextTargetMove(Pacman pacman, List<Position> possibles);
    Position nextScatorMove(Pacman pacman, List<Position> possibles);
}
