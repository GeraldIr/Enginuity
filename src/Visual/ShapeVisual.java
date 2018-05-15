package Visual;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;

public class ShapeVisual extends Component implements Visual {
	Shape shape;
	Color color;	
	Pose2D pose;
	
	public ShapeVisual(Entity entity, Shape shape, Color color) {
		super(entity);
		this.shape = shape;
		this.color = color;
	}

	
	public ShapeVisual setShape(Shape shape) {
		this.shape = shape;
		return this;
	}
	
	public ShapeVisual setColor(Color color) {
		this.color = color;
		return this;
	}
	
	@Override
	public void render(Graphics2D g) {
		g.translate(pose.getPosX(), pose.getPosY());
		g.rotate(pose.getAngle());
		
		g.setColor(color);
		g.fill(shape);
	}

	@Override
	public void activate() {
		this.pose = getEntity().getComponent(Pose2D.class);
		ServiceLocator.getInstance().get(VisualSystem.class).addVisual(this);
	}
	
	
}
