package AsteroidsLogic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import Visual.Pose2D;
import Visual.Visual;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.math.Vector2d;

public class PolygonVisual extends Component implements Visual {

	Pose2D pose;
	ArrayList<Vector2d> points = new ArrayList<Vector2d>();
	
	public PolygonVisual(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}
	
	public PolygonVisual addPoint(Vector2d point) {
		points.add(point);
		return this;
	}
	
	public PolygonVisual removePoint(Vector2d point) {
		points.remove(point);
		return this;
	}

	@Override
	public void render(Graphics2D g) {
		g.translate(pose.getPosX(), pose.getPosY());
		g.rotate(pose.getAngle());
		
		g.setColor(Color.WHITE);
		
		g.drawLine((int)points.get(points.size()-1).x, (int)points.get(points.size()-1).y, (int)points.get(0).x, (int)points.get(0).y);
		for(int i = 0; i < points.size()-1; i++) {
			g.drawLine((int)points.get(i).x, (int)points.get(i).y, (int)points.get(i+1).x, (int)points.get(i+1).y);
		}
		
	}
	
	@Override
	public void activate() {
		super.activate();
		this.pose = getEntity().getComponent(Pose2D.class);
		ServiceLocator.getInstance().get(VisualSystem.class).addVisual(this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		ServiceLocator.getInstance().get(VisualSystem.class).removeVisual(this);
	}

}
