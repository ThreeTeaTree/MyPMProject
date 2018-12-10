package sharedObject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public interface IRenderable extends Tickable, Comparable<IRenderable> {
	
	double getRenderPriority();
	
	int compareTo(IRenderable other);
	
	
}
