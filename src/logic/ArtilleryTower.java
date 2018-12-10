package logic;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class ArtilleryTower extends Tower {
	
	private static double width = 70;
	private static double height = 90;
	
	private static int prefiringDelay = 120;
	private static int postfiringDelay = 120;

	private static double radius = 150;
	private static double radiusX = Math.sqrt(1.5) * radius;
	private static double radiusY = Math.sqrt(0.5) * radius;
	
	private static double projectileShift = 75;
	

	public ArtilleryTower(Field field, Point2D position, int direction) {
		super(field, position, direction, prefiringDelay, postfiringDelay, radiusX, radiusY);
	}

	
	@Override
	protected void fire() {
		System.out.println("DEBUG : Artillery.fire from to : " + position + " -> " + projectileDestination);
		Point2D projectileInitial = new Point2D(position.getX(), position.getY() - projectileShift);
		PrimedTnt primedTnt = new PrimedTnt(field, projectileInitial, projectileDestination);
		field.addProjectile(primedTnt);
	}
	
	@Override
	protected void graphicUpdate(GraphicsContext gc) {
		
		double drawX = position.getX() - width / 2;
		double drawY = position.getY();
		
		if (isSearching) {
			gc.drawImage(RenderableHolder.artileryIdleTower[direction], drawX, drawY);
		}
		
		if (isPrefiring || isPostfiring) {
			if (lifeCycle >= 0 && lifeCycle < 60) {
				gc.drawImage(RenderableHolder.artileryFireTower[direction], drawX, drawY);
			}
			else {
				gc.drawImage(RenderableHolder.artileryIdleTower[direction], drawX, drawY);
			}
		}
		
	}


	@Override
	public boolean hover(Point2D hoverPosition) {
		return false;
	}

	@Override
	public boolean click(Point2D pressPosition, Point2D releasePosition) {
		return false;
	}
	
	@Override
	public void unclick() {
		
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
