package sharedObject;

import javafx.scene.canvas.GraphicsContext;
public interface Tickable {
	
	void tick(long now, GraphicsContext gc);
	
}
