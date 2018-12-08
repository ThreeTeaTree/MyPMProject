package drawing;

import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.GameLogic.STATE;
import sharedObject.RenderableHolder;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Menu extends Canvas{
	
	private GameLogic gameLogic;
	
	public Menu(double width , double height,GameLogic gameLogic) {
		super(width,height);
		this.gameLogic = gameLogic;
		addListerner();
	}
	
	public void tick() {
		if (InputUtility.isLeftClickRelease() && OnHelpButton()) {
			gameLogic.setGameState(STATE.Help);
		}
		
		InputUtility.updateInputState();
	}
	
	public void paintComponent() {
		
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.menuBg, 0, 0);
		
		gc.setFont(RenderableHolder.headerFont);
		

		gc.setFill(Color.WHITE);
		gc.fillText("OVERWORLD GUARDIAN", 268, 120);
		
		//GraphicsContext gc2 = this.getGraphicsContext2D();
		
		gc.setFont(RenderableHolder.btnFont);
		
		if (OnPlayButton()) {
			gc.setFill(Color.ORANGE);
			gc.fillText(">Play", 590, 500);
		} else {
			gc.setFill(Color.WHITE);
			gc.fillText("Play", 600, 500);
		}
		
		if (OnHelpButton()) {
			gc.setFill(Color.ORANGE);
			gc.fillText(">Help", 595, 575);
		} else {
			gc.setFill(Color.WHITE);
			gc.fillText("Help", 605, 575);
		}
		/*FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = 0 ;
		for (char c : "Play".toCharArray()) {
			font_width += fontLoader.getCharWidth(c, gc2.getFont()) ;
		}*/
		
		//System.out.println(font_width);
		
		
		
	}
	
	public boolean OnPlayButton() {
		return (InputUtility.mouseX > 600 && InputUtility.mouseX < 672.5)&&(InputUtility.mouseY >= 472 && InputUtility.mouseY <= 525);
	}
	
	public boolean OnHelpButton() {
		return (InputUtility.mouseX > 540 && InputUtility.mouseX < 748)&&(InputUtility.mouseY >= 555 && InputUtility.mouseY <= 575);
	}
	
	
	public void addListerner() {
		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				System.out.println("Click");
				InputUtility.mouseLeftDown();
			}else if (event.getButton() == MouseButton.SECONDARY) {
				System.out.println("Click");
				InputUtility.mouseRightDown();
			}
		});

		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				System.out.println("Release");
				InputUtility.mouseLeftRelease();
			}else if (event.getButton() == MouseButton.SECONDARY) {
				System.out.println("Release");
				InputUtility.mouseRightRelease();
			}
		});

		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnMenu = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnMenu = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnMenu) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
				//System.out.print(event.getX());
				//System.out.println(" "+event.getY());
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnMenu) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
	}
	
}
