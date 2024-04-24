package org.DesignPattProject.model.elements.ghost;

import org.DesignPattProject.model.elements.ghost.ghostStrategies.IGhostStrategy;

public class Inky extends Ghost {

    public Inky(int x, int y, IGhostStrategy strategy) {
        super(x, y, strategy);
    }
}
