package Collision;

public class Collision2D {
	Collider2D other;
	CollisionLogic cl;
	
	public Collision2D(Collider2D other, CollisionLogic cl) {
		this.other = other;
		this.cl = cl;
	}
	
	public void resolveCollision() {
		cl.resolveCollision(other);
	}
}
