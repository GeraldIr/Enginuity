package Physics;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.ServiceLocator;
import Visual.Pose2D;
import at.fhooe.mtd.sgl.math.Vector2d;

public class Body2D extends Component implements Pose2D, Body, PhysicsObject {

	////////
	//LINEAR
	////////
	Vector2d position = new Vector2d();
	Vector2d acceleration = new Vector2d();
	Vector2d velocity = new Vector2d();
	Vector2d dampening = new Vector2d(1,1);
	
	/////////
	//ANGULAR
	/////////
	double angle;
	double torque;
	double angularVelocity;
	double MOI=1;
	double angularDampening = 1;
	
	//////////////////////
	//NEWTONIAN PROPERTIES
	//////////////////////
	double invMass;
	
	
	
	public Body2D(Entity entity, Vector2d initialVelocity) {
		super(entity);
		velocity = initialVelocity;
		
	}
	
	public Body2D setDampening(Vector2d dmp) {
		dampening = dmp;
		return this;
	}
	
	public Body2D setAngularDampening(double dmp) {
		angularDampening = dmp;
		return this;
	}
	
	public Body2D setPos(Vector2d position) {
		this.position = position;
		return this;
	}
	
	public Body2D setPos(double x, double y) {
		position.x = x;
		position.y = y;
		return this;
	}
	
	public Body2D setRotation(double a) {
		this.angle = a;
		return this;
	}
	
	
	public Body2D setMass(double mass) {
		
		invMass = (mass == 0) ? 0 : 1 / mass;
		return this;
	}
	
	
	public double getMass() {
		return (invMass == 0) ? 0 : 1 / invMass;
	}
	
	@Override
	public Body2D setAngle(double a) {
		this.angle = a;
		return this;
	}

	@Override
	public double getPosX() {
		return position.x;
	}

	@Override
	public double getPosY() {
		return position.y;
	}
	
	public Vector2d getPos() {
		return position;
	}

	@Override
	public double getAngle() {
		return angle;
	}
	
	public Vector2d getVelocity() {
		return velocity;
	}

	@Override
	public void applyForce(Vector2d force) {
		acceleration.mulAdd(force, invMass);
	}

	@Override
	public void applyTorque(double t) {	
		torque += t / MOI;
	}

	@Override
	public void update(double dt) {	
		velocity.mulAdd(acceleration, dt);
		angularVelocity += torque * dt;
		
		position.mulAdd(velocity, dt);
		angle += angularVelocity * dt;	
		
		acceleration.scale(0);	
		torque = 0;
		
		angularVelocity *= angularDampening;
		velocity.x *= dampening.x;
		velocity.y *= dampening.y;		
	}
	
	
	public double distanceToPoint(double x, double y) {	
		return Math.sqrt(Math.pow(x - this.getPosX(), 2) + Math.pow(y - this.getPosY(), 2));
	}
	
	public double angleToPoint(double x, double y) {
		double delta_x = this.getPosX() - x;
		double delta_y = this.getPosY() - y;
		return Math.atan2(delta_y, delta_x);
	}
	
	public static Vector2d getDirectionalForce(double magnitude, double angle) {
		Vector2d directionalForce = new Vector2d();
		directionalForce.x = Math.cos(angle) * magnitude;
		directionalForce.y = Math.sin(angle) * magnitude;
		return directionalForce;
	}

	@Override
	public void activate() {
		super.activate();
		ServiceLocator.getInstance().get(PhysicsSystem.class).addPhysicsObject(this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		ServiceLocator.getInstance().get(PhysicsSystem.class).removePhysicsObject(this);
	}

	@Override
	public Pose2D setPos(Pose2D p) {
		this.position.x = p.getPosX();
		this.position.y = p.getPosY();
		return this;
	}


}
