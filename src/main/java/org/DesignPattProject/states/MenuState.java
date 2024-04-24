package org.DesignPattProject.states;

import org.DesignPattProject.controller.Controller;
import org.DesignPattProject.controller.menuController.MenuController;
import org.DesignPattProject.menu.Menu;
import org.DesignPattProject.viewer.Viewer;
import org.DesignPattProject.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }

    public Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }
}
