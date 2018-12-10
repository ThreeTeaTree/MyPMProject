package logic;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;



public class Arrow extends Projectile {
	
	private static double verticalTrajectory = 75;
	private static int maxLifeTime = 15;
	
	private static double width = 59;
	private static double height = 67;
	
	public Arrow(Field field, Point2D position, Point2D destination) {
		super(field, position, destination, verticalTrajectory, maxLifeTime);
	}
	
	@Override
	protected void hit() {
		field.pushDamage(new Damage(Damage.ARROW, field, position));
	}
	
	@Override
	protected void graphicUpdate(GraphicsContext gc) {
		double drawX = position.getX() - width / 2;
		double drawY = position.getY() - height / 2;
		
		gc.drawImage(RenderableHolder.arrowImage, drawX, drawY);
	}

	
	@Override
	public double getRenderPriority() {
		return position.getY() + maxLifeTime - lifeTime;
	}

	@Override
	public int compareTo(IRenderable other) {
		return Double.compare(getRenderPriority(), other.getRenderPriority());
	}
	
}
