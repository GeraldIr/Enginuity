package Visual;

public interface Pose2D {
	double getPosX();
	double getPosY();
	double getAngle();
	Pose2D setPos(double x, double y);
	Pose2D setAngle(double a);
}
