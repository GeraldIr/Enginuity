package BigPhysics;

import java.math.BigDecimal;

public interface BigPose2D {
	BigDecimal getPosX();
	BigDecimal getPosY();
	double getAngle();
	BigPose2D setPos(BigDecimal x, BigDecimal y);
	BigPose2D setPos(BigPose2D p);
	BigPose2D setAngle(double a);
}
