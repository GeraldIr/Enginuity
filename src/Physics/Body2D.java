package Physics;

import java.util.Vector;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import Visual.Pose2D;
import Visual.VisualSystem;
import at.fhooe.mtd.sgl.math.Vector2d;
import java.lang.Math;

public class Body2D extends Component implements Pose2D, Body, PhysicsObject {

	Vector2d velocity;
	double accelerationX = 0;
	double accelerationY = 0;
	
	boolean hasGravity;
	
	double GRAVITATIONAL_CONSTANT = 6.67408 * Math.pow(10, -3);
	
	
	double invMass;
	double x,y,a;
	
	public Body2D(Entity entity, Vector2d startVelocity) {
		super(entity);
		velocity = startVelocity;
	}
	
	public Body2D setPos(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Body2D setRotation(double a) {
		this.a = a;
		return this;
	}
	
	public Body2D setMass(double mass) {
		this.invMass = (mass == 0) ? 0 : 1/mass;
		return this;
	}
	
	public double getMass() {
		return (invMass == 0) ? 0 : 1 / invMass;
	}
	
	@Override
	public Body2D setAngle(double a) {
		this.a = a;
		return this;
	}

	@Override
	public double getPosX() {
		return x;
	}

	@Override
	public double getPosY() {
		return y;
	}

	@Override
	public double getAngle() {
		return a;
	}

	//F=m*a   a = m/F
	@Override
	public void applyForce(Vector2d v) {
		accelerationX = v.x / getMass();
		accelerationY = v.y / getMass();
		
	}

	@Override
	public void applyTorque(double t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double dt) {
		
		velocity.x += accelerationX * dt;
		velocity.y += accelerationY * dt;
		
		System.out.println("UpdateA: " + accelerationX + ";" + accelerationY);
		System.out.println("UpdateV: " + velocity.x + ";" + velocity.y);

		
		setPos(getPosX() + (velocity.x * dt), getPosY() + (velocity.y * dt));
		
		if(hasGravity) {
			for(PhysicsObject p : ServiceLocator.getInstance().get(PhysicsSystem.class).physicsObjectList) {
				Body2D other = ((Component)p).getEntity().getComponent(Body2D.class);
				if(other != this) {
					double gravitationalForce = GRAVITATIONAL_CONSTANT * (this.getMass() * other.getMass() / Math.pow(distanceToPoint(other.getPosX(), other.getPosY()), 2));
					double angleFromOther = other.angleToPoint(this.x, this.y);
					Vector2d gravity = new Vector2d();
					gravity.x = -Math.cos(angleFromOther) * gravitationalForce;
					gravity.y = -Math.sin(angleFromOther) * gravitationalForce;
					System.out.println("gravity: " + gravity.x + ";"+ gravity.y);
					other.applyForce(gravity);
				}
			}
		}
		
	}
	
	public Body2D setHasGravity(boolean b) {
		hasGravity = b;
		return this;
	}
	
	public double distanceToPoint(double x, double y) {	
		return Math.sqrt(Math.pow(this.getPosX() - x, 2) + Math.pow(this.getPosY() + y, 2));
	}
	
	public double angleToPoint(double x, double y) {
		double delta_x = this.getPosX() - x;
		double delta_y = this.getPosY() - y;
		return Math.atan2(delta_y, delta_x);
	}

	@Override
	public void activate() {
		super.activate();
		ServiceLocator.getInstance().get(PhysicsSystem.class).addPhysicsObject(this);
	}


}
