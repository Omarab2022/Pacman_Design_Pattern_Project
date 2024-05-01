package org.DesignPattProject.viewer.bridge;

import org.DesignPattProject.gui.GUI;
import org.DesignPattProject.model.elements.ghost.*;

public class GhostViewerBridge {
	private GhostViewer ghostViewer;

	public GhostViewerBridge(GhostViewer ghostViewer) {
		this.ghostViewer = ghostViewer;
	}

	public void draw(GUI gui) {
		ghostViewer.draw(gui);
	}
}
