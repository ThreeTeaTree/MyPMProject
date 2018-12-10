package logic;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;
import sharedObject.IRenderable;

public class PrimedTnt extends Projectile{

	private static double width = 19;
	private static double height = 22;
	
	private static double particleSize = 100;
	
	private static double verticalTrajectory = 125;
	private static int maxLifeTime = 60;
	
	public PrimedTnt(Field field, Point2D position, Point2D destination) {
		super(field, position, destination, verticalTrajectory, maxLifeTime);
	}
	

	@Override
	protected void hit() {
		field.pushDamage(new Damage(Damage.EXPLOSION, field, position));
		StaticParticle sp = new StaticParticle(field, RenderableHolder.explosion, position, particleSize, particleSize, 2);
		field.addRender(sp);
	}
	
	@Override
	protected void graphicUpdate(GraphicsContext gc) {
		double drawX = position.getX() - width / 2;
		double drawY = position.getY() - height / 2;
		
		if (lifeTime / 10 % 2 == 0) gc.drawImage(RenderableHolder.unlit, drawX, drawY);
		else gc.drawImage(RenderableHolder.lit, drawX, drawY);
	}

	
	@Override
	public double getRenderPriority() {
		return position.getY() + 10 * (maxLifeTime - lifeTime);
	}

	@Override
	public int compareTo(IRenderable other) {
		return Double.compare(getRenderPriority(), other.getRenderPriority());
	}

}
