package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.GameLogic.STATE;
import sharedObject.RenderableHolder;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import drawing.Game;
import drawing.Instruction;
import drawing.Menu ;
import input.AudioUtility;


public class Main extends Application {
	
	private GameLogic gameLogic ;
	
	//Menu
	private StackPane menuPane ;
	private Scene menuScene ;
	private Menu menu ;
	
	//help
	private StackPane helpPane ;
	private Scene helpScene ;
	private Instruction instruction ;
	
	//Game
	private Pane gamePane ;
	private Scene gameScene ;
	private Game game ;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			gameLogic = new GameLogic() ;
			
			menuPane = new StackPane();
			menuScene = new Scene(menuPane,1280,720);
			menu = new Menu(1280,720,gameLogic) ;
			menuPane.getChildren().add(menu);
			
			helpPane = new StackPane();
			helpScene = new Scene(helpPane, 1280,720);
			instruction = new Instruction(1280, 720, gameLogic) ;
			helpPane.getChildren().add(instruction);
			

			gamePane = new Pane();
			gameScene = new Scene(gamePane,1280,720);
			game = new Game(1280,720,gameLogic) ;
			gamePane.getChildren().add(game) ;
			
			primaryStage.setScene(menuScene);
			primaryStage.setTitle("Overworld Guardian");
			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> {
				primaryStage.close();
				System.exit(0);
			});
			
			AudioUtility.playThemeSong();
			
			
			AnimationTimer animation = new AnimationTimer() {
				public void handle(long now) {
					if (gameLogic.getGameState() == STATE.Menu) {
						primaryStage.setScene(menuScene);
						menu.tick();
						menu.paintComponent();
					}
					if (gameLogic.getGameState() == STATE.Help) {
						primaryStage.setScene(helpScene);
						instruction.tick();
						instruction.paintComponent();
					}
					if (gameLogic.getGameState() == STATE.Game) {
						primaryStage.setScene(gameScene);
						game.tick(now);
					}
				}
			};animation.start();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
