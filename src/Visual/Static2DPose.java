package Visual;

import EntityManagement.Component;
import EntityManagement.Entity;

public class Static2DPose extends Component implements Pose2D {

	double x;
	double y;
	double a;
	
	public Static2DPose(Entity entity) {
		super(entity);
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

	@Override
	public Pose2D setPos(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public Pose2D setAngle(double a) {
		this.a = a;
		return this;
	}

	@Override
	public Pose2D setPos(Pose2D p) {
		this.x = p.getPosX();
		this.y = p.getPosY();
		return this;
	}

}
