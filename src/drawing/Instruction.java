package drawing;


import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.GameLogic.STATE;
import sharedObject.RenderableHolder;
import javafx.scene.media.AudioClip;

public class Instruction extends Canvas{
	
	private GameLogic gameLogic ;
	
	public Instruction(double width , double height , GameLogic gameLogic) {
		super(width,height);
		this.gameLogic = gameLogic ;
		addListerner();
	}
	
	public void tick() {
		if (onBackButton() && InputUtility.isLeftClickRelease()) {
			gameLogic.setGameState(STATE.Menu);
		}
		InputUtility.updateInputState();
	}
	
	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		
		gc.setFont(RenderableHolder.btnFont);
		
		if (onBackButton()) {
			gc.setFill(Color.ORANGE);
			gc.fillText(">Back", 1130, 680);
		} else {
			gc.setFill(Color.WHITE);
			gc.fillText("Back", 1150, 680);
		}
		/*gc.setFont(RenderableHolder.btnFont);
		gc.setFill(Color.ORANGE);
		gc.fillText("Back", , arg2);*/
	}
	
	private boolean onBackButton() {
		return (InputUtility.mouseX > 1150 && InputUtility.mouseX < 1250)&&(InputUtility.mouseY >= 650 && InputUtility.mouseY <= 680);
	}
	
	//add later
	private boolean onPrevButton() {
		return true ;
	}
	
	//add later
	private boolean onNextButton() {
		return true ;
	}
	
	
	//add later
	private boolean onPlayButton() {
		return true ;
	}
	
	
	
	private void addListerner() {
		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				System.out.println("Click");
				System.out.print(event.getX());
				System.out.println(" "+event.getY());
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
			InputUtility.mouseOnHelp = true;
		});

		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnHelp = false;
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnHelp) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
				//System.out.print(event.getX());
				//System.out.println(" "+event.getY());
			}
		});

		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnHelp) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
	}
	
	
}
