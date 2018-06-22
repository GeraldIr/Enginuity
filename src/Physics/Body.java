package Physics;


import at.fhooe.mtd.sgl.math.Vector2d;

public interface Body {
	void applyForce(Vector2d v);
	void applyTorque(double t);
}
