package org.DesignPattProject.model.elements.ghost;

import org.DesignPattProject.model.elements.ghost.ghostStrategies.IGhostStrategy;

public class Pinky extends Ghost {

    public Pinky(int x, int y, IGhostStrategy strategy) {
        super(x, y, strategy);
    }
}
