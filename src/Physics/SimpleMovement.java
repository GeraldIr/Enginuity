package Physics;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import Visual.Static2DPose;

public class SimpleMovement extends Component implements Updateable {

	double direction;
	double velocity;
	
	public SimpleMovement(Entity entity, double dir, double vel) {
		super(entity);
		direction = dir;
		velocity = vel;
	}

	@Override
	public void activate() {
		ServiceLocator.getInstance().get(UpdateSystem.class).addUpdateable(this);
	}

	@Override
	public void update(double dt) {
		Static2DPose oldPos = getEntity().getComponent(Static2DPose.class);
		double x = oldPos.getPosX();
		double y = oldPos.getPosY();

		x += velocity*Math.sin(direction)*dt;
		y += velocity*Math.cos(direction)*dt;
		
		getEntity().getComponent(Static2DPose.class).setPos(x, y);
		
	}
	
	
	
	
	

	
}
