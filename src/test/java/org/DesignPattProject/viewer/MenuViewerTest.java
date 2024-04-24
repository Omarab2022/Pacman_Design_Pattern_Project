package org.DesignPattProject.viewer;

import org.junit.jupiter.api.Test;
import org.DesignPattProject.gui.LanternaGUI;
import org.DesignPattProject.menu.Menu;
import org.DesignPattProject.model.elements.Position;
import org.DesignPattProject.viewer.menu.MenuViewer;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MenuViewerTest {

    @Test
    public void drawElementsTest() throws IOException {
        Menu menu = new Menu();
        LanternaGUI gui = mock(LanternaGUI.class);
        MenuViewer menuViewer = new MenuViewer(menu);
        menuViewer.draw(gui);
        verify(gui, times(1)).drawText(new Position(11, 10), "PLAY","#34bbc9");
    }
}
