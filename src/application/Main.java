package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.GameLogic.STATE;
import sharedObject.RenderableHolder;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import drawing.Instruction;
import drawing.Menu ;
import input.AudioUtility;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GameLogic gameLogic = new GameLogic() ;
			
			StackPane menu = new StackPane();
			Scene menuScene = new Scene(menu,1280,720);
			Menu menu1 = new Menu(1280,720,gameLogic) ;
			menu.getChildren().add(menu1);
			
			StackPane help = new StackPane();
			Scene helpScene = new Scene(help, 1280,720);
			Instruction instruction = new Instruction(1280, 720, gameLogic) ;
			help.getChildren().add(instruction);
			
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
						menu1.tick();
						menu1.paintComponent();
					}
					if (gameLogic.getGameState() == STATE.Help) {
						primaryStage.setScene(helpScene);
						instruction.tick();
						instruction.paintComponent();
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
