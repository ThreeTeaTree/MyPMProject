package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import logic.Zombie;

public class RenderableHolder {
	
	//UI Image
	public static Image menuBg ; 
	
	//Field
	public static Image fieldImage ;
	
	//TowerImage
	public static Image[] strayIdleTower = new Image[4] ;
	public static Image[][] strayFireTower = new Image[4][3] ;
	
	public static Image[] artileryIdleTower = new Image[4];
	public static Image[] artileryFireTower = new Image[4];
	
	public static Image nullTowerIdle;
	public static Image nullTowerHover;
	
	public static Image[] witchTowerIdle = new Image[4];
	
	//Tower Upgrade Component
	public static Image archeryHover ;
	public static Image archeryIdle ;
	public static Image witchIdle ;
	public static Image witchHover ;
	public static Image artilleryIdle;
	public static Image artilleryHover;
	
	//PrimedTnt
	public static Image lit;
	public static Image unlit;
	public static Image[] explosion = new Image[24];
	
	//ThrownPotion
	public static Image potion ;
	public static Image splash[] = new Image[24];
	
	
	//Arrow
	public static Image arrowImage ;
	
	//tooltip
	public static Image[] tooltip = new Image[4];
	
	//Enemy
	public static Image[][] walkingZombie = new Image[8][21] ;
	
	//wavecall
	public static Image waveCallButtonDisable;
	public static Image waveCallButtonIdle;
	public static Image waveCallButtonHover;
	
	
	
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
		
		//StrayTower
		String format = "tower/stray%d%d.png";
		for (int i = 0; i < 4; i++) {
			strayIdleTower[i] = new Image(
					ClassLoader.getSystemResource(String.format(format, i, 3)).toString(), 70, 90, true, false);
			for (int j = 0; j < 3; j++) {
				strayFireTower[i][j] = new Image(
						ClassLoader.getSystemResource(String.format(format, i, j)).toString(), 70, 90, true, false);
			}
		}
		
		//ArtilleryTower
		String format1 = "tower/tnt%d%d.png";
		for (int i = 0; i < 4; i++) {
			artileryIdleTower[i] = new Image(ClassLoader.getSystemResource(String.format(format1, i , 0)).toString(), 70, 90, true, false);
			artileryFireTower[i] = new Image(ClassLoader.getSystemResource(String.format(format1, i , 1)).toString(), 70, 90, true, false);
		}
		//Arrow?
		arrowImage = new Image(ClassLoader.getSystemResource("explosion/unlit.png").toString(), 59, 67 , true , false);
		
		//Field Image
		fieldImage = new Image(ClassLoader.getSystemResource("stage/stage.png").toString());
		
		//NullTower
		nullTowerIdle = new Image(ClassLoader.getSystemResource("tower/null0.png").toString(),70,90,true,false);
		nullTowerHover = new Image(ClassLoader.getSystemResource("tower/null1.png").toString(),70,90,true,false);
		
		//PrimedTnt
		lit = new Image(ClassLoader.getSystemResource("explosion/lit.png").toString(), 19, 22, true, false);
		unlit = new Image(ClassLoader.getSystemResource("explosion/unlit.png").toString(), 19, 22, true, false);
		for (int i = 0; i < 24; i++) {
			explosion[i] = new Image(ClassLoader.getSystemResource("explosion/explosion (" + (i + 1) + ").png").toString(), 100, 100, true, false);
		}
		
		//ThrownPotion
		potion = new Image(ClassLoader.getSystemResource("potion/potion.png").toString() , 9, 13, true, false);
		for (int i = 0; i < 24; i++) {
			splash[i] = new Image(ClassLoader.getSystemResource("potion/splash (" + (i + 1) + ").png").toString(), 200, 200, true, false);
		}
		
		//Tooltip
		String format3 = "tooltip/towerselect%d.png";
		for (int i = 0; i < 4; i++) {
			tooltip[i] = new Image(ClassLoader.getSystemResource(String.format(format3, i)).toString(), 150, 150, true, false);
		}
		
		//WitchTower
		String format4 = "tower/witch%d.png";
		for (int i = 0; i < 4; i++) {
			witchTowerIdle[i] = new Image(ClassLoader.getSystemResource(String.format(format4, i)).toString(), 70, 90, true, false);
		}
		
		//WalkingZombie
		String format5 = "zombie/%s (%d).png";
		for (int i = 0; i < 8; i++) {
			String d = Zombie.interpretDirection(i);
			for (int j = 0; j < 21; j++) {
				walkingZombie[i][j] = new Image(ClassLoader.getSystemResource
						(String.format(format5, d, j + 1)).toString(), 24, 40, true, false);
			}
		}
		
		//wavecall
		waveCallButtonDisable = new Image(ClassLoader.getSystemResource("button/call_disable.png").toString());
		waveCallButtonIdle  = new Image(ClassLoader.getSystemResource("button/call_idle.png").toString());
		waveCallButtonHover = new Image(ClassLoader.getSystemResource("button/call_disable.png").toString());
		
		//Tower Upgrade
		archeryIdle = new Image(ClassLoader.getSystemResource("gui/upgrade_archery_idle.png").toString());
		archeryHover = new Image(ClassLoader.getSystemResource(
				"gui/upgrade_archery_hover.png").toString());
		witchIdle = new Image(ClassLoader.getSystemResource(
				"gui/upgrade_witch_idle.png").toString());
		witchHover = new Image(ClassLoader.getSystemResource("gui/upgrade_witch_hover.png").toString());
		artilleryIdle = new Image(ClassLoader.getSystemResource("gui/upgrade_artillery_idle.png").toString());
		artilleryHover = new Image(ClassLoader.getSystemResource("gui/upgrade_artillery_hover.png").toString());
		
	}
	
	
	public static RenderableHolder getInstance() {
		return instance;
	}
	
}
