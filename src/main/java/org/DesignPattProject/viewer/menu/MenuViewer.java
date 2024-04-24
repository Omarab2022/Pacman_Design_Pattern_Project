package org.DesignPattProject.viewer.menu;

import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.menu.Menu;
import org.DesignPattProject.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Menu model) {
        super(model);
    }

    private static final String[] TITLE_TEXT = {
            " _     _  \n",
            "|_)/\\ /  _ |\\/| /\\ |\\ |\n",
            "| /--\\\\_   |  |/--\\| \\|\n"
    };

    public void drawElements(GUI gui) {
        for (int i = 0; i < TITLE_TEXT.length; i++) {
            gui.drawText(new Position(2, 3 + i), TITLE_TEXT[i], "#f5e72c");
        }

        for (int i = 0; i < getModel().getEntriesLen(); i++) {
            gui.drawText(new Position(11, 10 + 2*i), getModel().getEntry(i),
                    getModel().isSelected(i) ? "#34bbc9" : "#FFFFFF");
        }
    }

}
