package org.DesignPattProject.model.elements.ghost;

import org.DesignPattProject.model.elements.ghost.ghostStrategies.IGhostStrategy;

public class Clyde extends Ghost {

    public Clyde(int x, int y, IGhostStrategy strategy) {
        super(x, y, strategy);
    }
}
