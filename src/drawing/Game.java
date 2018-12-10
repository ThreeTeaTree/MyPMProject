package drawing;

import input.MouseListener;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.Field;
import logic.GameLogic;

public class Game extends Canvas{
	
	private GameLogic gameLogic;
	
	private MouseListener mouse ;
	
	private Field currentField ;
	
	public Game(int width , int height , GameLogic gameLogic ) {
		
		super(width,height);
		this.gameLogic = gameLogic ;
		mouse = new MouseListener();
		try {
			this.currentField = new Field(""); ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addListener();
		
	}
	
	public void tick(long now) {
		
		GraphicsContext gc = this.getGraphicsContext2D();
		
		boolean isPrimaryClicked = mouse.isPrimaryClicked();
		Point2D primaryPressPosition = null;
		Point2D primaryReleasePosition = null;
		if (isPrimaryClicked) {
			Point2D[] primaryClickInfo = mouse.retrievePrimaryClickInfo();
			primaryPressPosition = primaryClickInfo[0];
			primaryReleasePosition  = primaryClickInfo[1];
		}
		
		currentField.hover(mouse.getHoverPosition());
		if (isPrimaryClicked) currentField.click(primaryPressPosition, primaryReleasePosition);
		currentField.tick(now, gc);
		
		
		
	}
	
	/*public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
	}*/
	
	private void addListener() {
		this.setOnMouseMoved(mouse);
		this.setOnMousePressed(mouse);
		this.setOnMouseReleased(mouse);
	}
	

	
}
