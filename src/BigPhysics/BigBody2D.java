package BigPhysics;

import java.math.BigDecimal;

import EntityManagement.Component;
import EntityManagement.Entity;
import Physics.PhysicsObject;
import Physics.PhysicsSystem;
import Services.ServiceLocator;

public class BigBody2D extends Component implements BigPose2D, BigBody, PhysicsObject {

	////////
	//LINEAR
	////////
	BigVector2d position = new BigVector2d();
	BigVector2d acceleration = new BigVector2d();
	BigVector2d velocity = new BigVector2d();
	
	/////////
	//ANGULAR
	/////////
	double angle;
	double torque;
	double MOI=1;
	
	//////////////////////
	//NEWTONIAN PROPERTIES
	//////////////////////
	BigDecimal invMass;
	
	
	
	public BigBody2D(Entity entity, BigVector2d initialVelocity) {
		super(entity);
		velocity = initialVelocity;
		
	}
	
	public BigBody2D setPos(BigVector2d position) {
		this.position = position;
		return this;
	}
	
	public BigBody2D setPos(BigDecimal x, BigDecimal y) {
		position.x = x;
		position.y = y;
		return this;
	}
	
	public BigBody2D setRotation(double a) {
		this.angle = a;
		return this;
	}
	
	public BigBody2D setMass(BigDecimal mass) {
		
		invMass = (mass == BigDecimal.ZERO) ? BigDecimal.ZERO : BigDecimal.ONE.divide(mass);
		return this;
	}
	
	public BigBody2D setMass(String mass) {
		
		invMass = (mass == "0") ? BigDecimal.ZERO :  BigDecimal.ONE.divide(new BigDecimal(mass));
		return this;
	}
	
	public BigDecimal getMass() {
		return (invMass == BigDecimal.ZERO) ? BigDecimal.ZERO : (BigDecimal.ONE.divide(invMass));
	}
	
	@Override
	public BigBody2D setAngle(double a) {
		this.angle = a;
		return this;
	}

	@Override
	public BigDecimal getPosX() {
		return position.x;
	}

	@Override
	public BigDecimal getPosY() {
		return position.y;
	}
	
	public BigVector2d getPos() {
		return position;
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public void applyForce(BigVector2d force) {
		acceleration.mulAdd(force, invMass.doubleValue());
	}

	@Override
	public void applyTorque(double t) {	
		torque += t / MOI;
	}

	@Override
	public void update(double dt) {	
		velocity.mulAdd(acceleration, dt);
		
		position.mulAdd(velocity, dt);
				
		acceleration.scale(0);		
	}
	
	
//	public double distanceToPoint(double x, double y) {	
//		return Math.sqrt(Math.pow(this.getPosX() - x, 2) + Math.pow(this.getPosY() + y, 2));
//	}
//	
//	public double angleToPoint(double x, double y) {
//		double delta_x = this.getPosX() - x;
//		double delta_y = this.getPosY() - y;
//		return Math.atan2(delta_y, delta_x);
//	}

	@Override
	public void activate() {
		super.activate();
		ServiceLocator.getInstance().get(PhysicsSystem.class).addPhysicsObject(this);
	}



	@Override
	public BigPose2D setPos(BigPose2D p) {
		// TODO Auto-generated method stub
		return null;
	}


}

