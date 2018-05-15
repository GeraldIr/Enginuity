package Physics;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import Visual.Pose2D;
import Visual.VisualSystem;

public class Moveable extends Component implements PhysicsObject {

	Body2D body;
	
	public Moveable(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void activate() {
		this.body = getEntity().getComponent(Body2D.class);
		ServiceLocator.getInstance().get(PhysicsSystem.class).addPhysicsObject(this);
	}

}
