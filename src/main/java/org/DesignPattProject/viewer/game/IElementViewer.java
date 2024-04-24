package org.DesignPattProject.viewer.game;

import org.DesignPattProject.model.elements.Element;
import org.DesignPattProject.gui.GUI;

public interface IElementViewer<T extends Element> {

    void draw(T element, GUI gui);
}
