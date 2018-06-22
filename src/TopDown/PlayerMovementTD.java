package TopDown;

import EntityManagement.Component;
import EntityManagement.Entity;
import Physics.UpdateSystem;
import Physics.Updateable;
import Services.ServiceLocator;
import Visual.Pose2D;
import at.fhooe.mtd.sgl.input.Keyboard;
import at.fhooe.mtd.sgl.math.Vector2d;

public class PlayerMovementTD extends Component implements Updateable, Pose2D  {

	
	///////////////////////
	// Properties
	///////////////////////
	private double accelerationTime;
	private double decelerationTime;
	private double speed;
	
	////////////////////////
	// Pose
	////////////////////////
	private Vector2d position = new Vector2d(0, 0);
	private double rotation;
	
	
	///////////////////////
	// Controls
	///////////////////////
	Keyboard keyboard = new Keyboard();
	
	
	public PlayerMovementTD setAccelerationTime(double accelerationTime){
		this.accelerationTime = accelerationTime;
		return this;
	}
	
	public PlayerMovementTD setSpeed(double speed){
		this.speed = speed;
		return this;
	}
	
	public PlayerMovementTD setDecelerationTime(double decelerationTime){
		this.decelerationTime = decelerationTime;
		return this;
	}
	
	public PlayerMovementTD(Entity entity) {
		super(entity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activate() {
		super.activate();
		ServiceLocator.getInstance().get(UpdateSystem.class).addUpdateable(this);
	}

	@Override
	public double getPosX() {
		return this.position.x;
	}

	@Override
	public double getPosY() {
		return this.position.y;
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return rotation;
	}

	@Override
	public Pose2D setPos(double x, double y) {
		this.position.x = x;
		this.position.y = y;
		return null;
	}

	@Override
	public Pose2D setPos(Pose2D p) {
		// TODO Auto-generated method stub
		this.position.x = p.getPosX();
		this.position.y = p.getPosY();
		return this;
	}

	@Override
	public Pose2D setAngle(double a) {
		this.rotation = a;
		return this;
	}
	
	

}
