package logic;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Zombie extends Enemy{
	
	
	private static int walkingLength = 21;
	private static int walkingHold = 6;
	
	private static double width = 24;
	private static double height = 40;
	
	public static String interpretDirection(int direction) {
		if (direction == 0 || direction == 4) return "side";
		if (direction == 1 || direction == 3) return "left";
		if (direction == 2) return "front";
		if (direction == 5 || direction == 7) return "right";
		if (direction == 6) return "back";
		return "WHAT THE FUCK";
	}
	
	private static boolean needFlip(int direction) {
		if (direction == 0 || direction == 3 || direction == 5) return true;
		return false;
	}
	
	private static int maxHealth = 50;
	private static double speed = 10;
	private static int cost = 1;
	private static int reward = 20;

	public Zombie(Field field, Path path, double pathShift, int spawnTime) {
		super(field, path, pathShift, maxHealth, speed, cost, reward, spawnTime);
	}

	@Override
	protected void graphicUpdate(GraphicsContext gc) {
		
		if (lifeTime < spawnTime) return;
		
		double drawX = position.getX();
		double drawY = position.getY() - height;
		
		int walkFrameIndex = (int) lifeTime / walkingHold % walkingLength;
		
		if (needFlip(direction)) {
			drawX += width / 2;
			gc.drawImage(RenderableHolder.walkingZombie[direction][walkFrameIndex], 0, 0, width, height, drawX, drawY, -width, height);
		}
		else {
			drawX -= width / 2;
			gc.drawImage(RenderableHolder.walkingZombie[direction][walkFrameIndex], drawX, drawY);
		}
	
	}

	@Override
	public void damage(Damage damage, double amount) {
		health -= amount;
		System.out.println("DEBUG : Zombie.damage health : " + health);
	}

	
	@Override
	public double getRenderPriority() {
		return position.getY();
	}

	@Override
	public int compareTo(IRenderable other) {
		return Double.compare(getRenderPriority(), other.getRenderPriority());
	}
}
