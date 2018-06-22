package Physics;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import at.fhooe.mtd.sgl.math.Vector2d;

public class NewtonianSpring2D extends Component implements PhysicsObject {

	private double equilibriumLength;
	private double springConstant;
	
	private Body2D obj1;
	private Body2D obj2;
	
	
	public NewtonianSpring2D(Entity entity) {
		super(entity);
	}
	
	public NewtonianSpring2D(Entity entity, double equilibriumLength, double springConstant, Body2D obj1, Body2D obj2) {
		super(entity);
		setSpringConstant(springConstant).setEquilibriumLength(equilibriumLength);
		setObject1(obj1).setObject2(obj2);
		
	}
	
	public NewtonianSpring2D setSpringConstant(double springConstant) {
		this.springConstant = springConstant;
		return this;
		
	}
	
	public NewtonianSpring2D setObject1(Body2D obj1) {
		this.obj1 = obj1;
		return this;	
	}
	
	public NewtonianSpring2D setObject2(Body2D obj2) {
		this.obj2 = obj2;
		return this;	
	}
	
	public NewtonianSpring2D setEquilibriumLength(double equilibriumLength) {
		this.equilibriumLength = equilibriumLength;
		return this;
		
	}

	@Override
	public void update(double dt) {	
		Vector2d forceObj1 = new Vector2d();
		Vector2d forceObj2 = new Vector2d();
		
		
		double forceMagnitude = -springConstant * (equilibriumLength - this.getSpringLength());
		
		
		forceObj1.x = -Math.cos(obj1.angleToPoint(obj2.getPosX(), obj2.getPosY())) * forceMagnitude;
		forceObj1.y = -Math.sin(obj1.angleToPoint(obj2.getPosX(), obj2.getPosY())) * forceMagnitude;
		
		forceObj2.x = -Math.cos(obj2.angleToPoint(obj1.getPosX(), obj1.getPosY())) * forceMagnitude;
		forceObj2.y = -Math.sin(obj2.angleToPoint(obj1.getPosX(), obj1.getPosY())) * forceMagnitude;

		obj1.applyForce(forceObj1);
		obj2.applyForce(forceObj2);
	}
	
	public double getSpringLength() {
		return obj1.distanceToPoint(obj2.getPosX(), obj2.getPosY());
	}
	
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		ServiceLocator.getInstance().get(PhysicsSystem.class).addPhysicsObject(this);
	}

}
