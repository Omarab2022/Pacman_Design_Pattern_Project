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
            "  VVV VVVVV V   V  VVVVV \n",
            "    V V   V V   V  V   V \n",
            "    V VVVVV V   V  VVVVV \n",
            "V   V V   V  V V   V   V \n",
            "VVVVV V   V   V    V   V \n"
    };

    public void drawElements(GUI gui) {
        for (int i = 0; i < TITLE_TEXT.length; i++) {
            gui.drawText(new Position(2, 3 + i), TITLE_TEXT[i], "#ffa500"); // Couleur orange
        }

        for (int i = 0; i < getModel().getEntriesLen(); i++) {
            gui.drawText(new Position(11, 10 + 2*i), getModel().getEntry(i),
                    getModel().isSelected(i) ? "#ff0000" : "#FFFFFF"); // Couleur bleue ou blanc
        }
    }
}
