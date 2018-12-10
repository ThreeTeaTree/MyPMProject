package logic;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class NullTower extends Tower {
	
	private static double width = 70;
	private static double height = 90;
	
	private static int prefiringDelay = 2147483647;
	private static int postfiringDelay = 2147483647;
	
	private static double radiusX = 0;
	private static double radiusY = 0;
	
	private boolean isHover;
	
	private RunnableButton archeryButton;
	private RunnableButton witchButton;
	private RunnableButton artilleryButton;
	
	public NullTower(Field field, Point2D position, int direction) {
		super(field, position, direction, prefiringDelay, postfiringDelay, radiusX, radiusY);
		
		double x = position.getX();
		double y = position.getY();
		
		archeryButton = new RunnableButton(new Point2D(x - 48, y + 24), null, RenderableHolder.archeryIdle, RenderableHolder.archeryHover, 48, 48);
		archeryButton.setOnClicked(() -> {
			ArcheryTower tower = new ArcheryTower(field, position, direction);
			field.addTower(tower);
			field.removeTower(this);
			field.removeRender(archeryButton);
		});
		archeryButton.setOnUnclicked(() -> field.removeRender(archeryButton));
		
		witchButton = new RunnableButton(new Point2D(x, y - 24), null, RenderableHolder.witchIdle, RenderableHolder.witchHover, 48, 48);
		witchButton.setOnClicked(() -> {
			WitchTower tower = new WitchTower(field, position, direction);
			field.addTower(tower);
			field.removeTower(this);
			field.removeRender(witchButton);
		});
		witchButton.setOnUnclicked(() -> field.removeRender(witchButton));
		
		artilleryButton = new RunnableButton(new Point2D(x + 48, y + 24), null, RenderableHolder.artilleryIdle, RenderableHolder.artilleryHover, 48, 48);
		artilleryButton.setOnClicked(() -> {
			ArtilleryTower tower = new ArtilleryTower(field, position, direction);
			field.addTower(tower);
			field.removeTower(this);
			field.removeRender(artilleryButton);
		});
		artilleryButton.setOnUnclicked(() -> field.removeRender(artilleryButton));
	}

	
	@Override
	protected void graphicUpdate(GraphicsContext gc) {
		double drawX = position.getX() - width / 2;
		double drawY = position.getY();
		if (!isHover) gc.drawImage(RenderableHolder.nullTowerIdle, drawX, drawY);
		else gc.drawImage(RenderableHolder.nullTowerHover, drawX, drawY);
	}

	@Override
	protected void fire() {
		// nulltower won't fire
	}


	@Override
	public boolean hover(Point2D hoverPosition) {
		isHover = isMouseInRange(hoverPosition);
		return isHover;
	}

	@Override
	public boolean click(Point2D pressPosition, Point2D releasePosition) {
		if (!isMouseInRange(pressPosition) || !isMouseInRange(releasePosition)) {
			return false;
		}
		field.addRender(archeryButton);
		field.addRender(witchButton);
		field.addRender(artilleryButton);
		return true;
	}
	
	@Override
	public void unclick() {
		 // tooltip will remove itself
	}
	
	private boolean isMouseInRange(Point2D mousePosition) {
		double dx = mousePosition.getX() - position.getX();
		double dy = mousePosition.getY() - position.getY();
		return dx >= -width / 2 && dx <= width / 2 && dy >= height / 2 && dy <= height; // special detection for NullTower
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
