package logic;

import java.util.ArrayList;
import java.util.Random;

import logic.Enemy;
import logic.Field;
import logic.PointOperations;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;

public abstract class Tower implements IRenderable , MouseInteractable{
	
private static Random random = new Random(3);
	
	protected Field field;
	protected Point2D position;
	protected int direction;
	protected double radiusX;
	protected double radiusY;
	
	protected int lifeCycle;
	protected int prefiringDelay;
	protected int postfiringDelay;
	
	protected Point2D projectileDestination;
	
	protected boolean isSearching = false;
	protected boolean isPrefiring = false;
	protected boolean isPostfiring = true;
	
	
	/*** Constructor ***/
	
	public Tower(Field field, Point2D position, int direction,
			int prefiringDelay, int postfiringDelay, double radiusX, double radiusY) {
		this.field = field;
		this.position = position;
		this.direction = direction;
		this.prefiringDelay = prefiringDelay;
		this.postfiringDelay = postfiringDelay;
		this.radiusX = radiusX;
		this.radiusY = radiusY;
		
		lifeCycle = 1;
	}
	
	
	/*** Tick ***/
	
	public void tick(long now, GraphicsContext gc) {
		logicUpdate(now);
		graphicUpdate(gc);
		lifeCycle++;
	}
	
	protected void logicUpdate(long now) {
		
		if (isSearching) {
			boolean isFound = search();
			if (isFound) {
				isSearching = false;
				isPrefiring = true;
				lifeCycle = -prefiringDelay;
			}
			return;
		}
		
		if (lifeCycle == 0) {
			fire();
			isPrefiring = false;
			isPostfiring = true;
			return;
		}
		
		if (lifeCycle > postfiringDelay) {
			isPostfiring = false;
			isSearching = true;
			return;
		}
		
	}
	
	protected boolean search() {
		boolean isFound = false;
		ArrayList<Enemy> enemiesInRange = new ArrayList<>();
		for (Enemy enemy : field.getEnemyOnField()) {
			if (isInFiringRange(enemy.getPosition())) {
				enemiesInRange.add(enemy);
				isFound = true;
			}
		}
		if (isFound) {
			int index = random.nextInt(enemiesInRange.size());
			Enemy enemy = enemiesInRange.get(index);
			Point2D scaledMomentum = PointOperations.scale(enemy.getMomentum(), prefiringDelay);
			projectileDestination = PointOperations.add(enemy.getPosition(), scaledMomentum);
		}
		return isFound;
	}
	
	protected boolean isInFiringRange(Point2D target) {
		Point2D different = PointOperations.different(position, target);
		double dx = different.getX();
		double dy = different.getY();
		return radiusY * radiusY * dx * dx + radiusX * radiusX * dy * dy < 
				radiusX * radiusX * radiusY * radiusY;
	}
	
	protected abstract void fire();
	
	protected abstract void graphicUpdate(GraphicsContext gc);
	
	
	public Point2D getPosition() {
		return position;
	}
	
	public int getDirection() {
		return direction;
	}

}

