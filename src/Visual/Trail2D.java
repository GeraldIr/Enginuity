package Visual;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import EntityManagement.Component;
import EntityManagement.Entity;
import Physics.UpdateSystem;
import Physics.Updateable;
import Services.ServiceLocator;

public class Trail2D extends Component implements Updateable {

	double oldX;
	double oldY;
	int framesPerLine = 3;
	private int currentFrame = 1;
	int maxLines = 0; //0 (and less) means infinite lines
	Color color = Color.CYAN;
	List<Line2DVisual> lineList;
	
	public Trail2D(Entity entity) {
		super(entity);
		lineList = new LinkedList<Line2DVisual>();
	}
	
	

	@Override
	public void update(double dt) {
		if(currentFrame >= framesPerLine) {
			Line2DVisual newLine = new Line2DVisual(oldX, oldY, this.getEntity().getComponent(Pose2D.class).getPosX(), this.getEntity().getComponent(Pose2D.class).getPosY());
			newLine.setColor(color);
			ServiceLocator.getInstance().get(VisualSystem.class).addVisual(newLine);
			lineList.add(newLine);
			oldX = this.getEntity().getComponent(Pose2D.class).getPosX();
			oldY = this.getEntity().getComponent(Pose2D.class).getPosY();
			currentFrame = 0;
			if(!(maxLines <= 0) && lineList.size() > maxLines) {
				ServiceLocator.getInstance().get(VisualSystem.class).removeVisual(lineList.get(0));
				lineList.remove(0);
			}
		}
		currentFrame++;
	}
	
	public Trail2D setColor(Color color) {
		this.color = color;
		return this;
	}
	
	public Trail2D setMaxLines(int maxLines) {
		this.maxLines = maxLines;
		return this;
	}

	@Override
	public void activate() {
		super.activate();
		ServiceLocator.getInstance().get(UpdateSystem.class).addUpdateable(this);
	}
	
	

}
