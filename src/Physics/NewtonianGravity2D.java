package Physics;

import java.util.ArrayList;
import java.util.List;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import at.fhooe.mtd.sgl.math.Vector2d;

public class NewtonianGravity2D extends Component implements PhysicsObject {

	double GRAVITATIONAL_CONSTANT = 6.67408 * Math.pow(10, 0);

	
	
	Body2D body;
	
	public NewtonianGravity2D(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		body = this.getEntity().getComponent(Body2D.class);
		ServiceLocator.getInstance().get(PhysicsSystem.class).addPhysicsObject(this);
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		Vector2d gravity = new Vector2d();
		List<PhysicsObject> pl =  ServiceLocator.getInstance().get(PhysicsSystem.class).physicsObjectList;
		List<Entity> el = new ArrayList<Entity>();
		
		//Gets all the Entities
		for(PhysicsObject p : pl)
			if(!el.contains(((Component)p).getEntity()))
				el.add(((Component)p).getEntity());
		
		for(Entity e : el) {
			
			NewtonianGravity2D other = e.getComponent(NewtonianGravity2D.class);
			if(other != this && other != null) {
				double gravitationalForce = GRAVITATIONAL_CONSTANT * ((this.body.getMass() * other.body.getMass()) / Math.pow(body.distanceToPoint(other.body.getPosX(), other.body.getPosY()), 2));
				double angleFromOther = other.body.angleToPoint(this.body.position.x, this.body.position.y);
				gravity.x = -Math.cos(angleFromOther) * gravitationalForce;
				gravity.y = -Math.sin(angleFromOther) * gravitationalForce;
				other.body.applyForce(gravity);
			}
		}
		
	}
	
	

}
