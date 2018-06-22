package Collision;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import Visual.Pose2D;

//Circular 2D collider with a radius
public class Collider2D extends Component {
	
	public double radius;
	Pose2D pose;

	public Collider2D(Entity entity, double radius) {
		super(entity);
		this.radius = radius;
	}
	
	public boolean isColliding(Collider2D other) {
		if(distanceToPoint(other.pose.getPosX(), other.pose.getPosY()) <= (this.radius + other.radius))
			return true;
		return false;
	}
	
	public double distanceToPoint(double x, double y) {	
		return Math.sqrt(Math.pow(x - pose.getPosX(), 2) + Math.pow(y- pose.getPosY(), 2));
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		pose = this.getEntity().getComponent(Pose2D.class);
		ServiceLocator.getInstance().get(CollisionSystem2D.class).addCollider(this);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		super.deactivate();
		pose = null;
		ServiceLocator.getInstance().get(CollisionSystem2D.class).removeCollider(this);

	}
	
	
	
	

}
