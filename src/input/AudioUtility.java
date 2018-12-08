package input;

import sharedObject.RenderableHolder;
import javafx.scene.media.AudioClip;

public class AudioUtility {
	
	public static void playSoundEffect(String sound) {
		
	}
	
	public static void playThemeSong() {
		Thread songThread = new Thread(() -> {
			try {
				RenderableHolder.themeSong.setCycleCount(AudioClip.INDEFINITE);
				RenderableHolder.themeSong.play(0.7);
			}catch (Exception e) {
				e.printStackTrace();
			}
		});songThread.start();
		
	}
	


}
