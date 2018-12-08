package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class RenderableHolder {
	
	public static Image menuBg ; 
	
	
	public static Font headerFont ;
	public static Font btnFont ;
	
	public static AudioClip themeSong ;
	
	
	private static final RenderableHolder instance = new RenderableHolder();
	
	static {
		loadResource();
	}
	
	public static void loadResource() {
		
		menuBg = new Image(ClassLoader.getSystemResource("menubackground.png").toString());
		headerFont = Font.loadFont(ClassLoader.getSystemResource("Minecrafter_3.ttf").toString(), 50);
		btnFont = Font.loadFont(ClassLoader.getSystemResource("Minecraftia.ttf").toString(), 30) ;
		themeSong = new AudioClip(ClassLoader.getSystemResource("gameThemeSong.wav").toString());
	}
	
	public RenderableHolder() {
		
	}
	
	public static RenderableHolder getInstance() {
		return instance;
	}
	
}
