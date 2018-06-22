package AsteroidsLogic;

import Collision.Collider2D;
import Collision.CollisionLogic;
import EntityManagement.Entity;

public class ShipCollider extends Collider2D implements CollisionLogic {

	public ShipCollider(Entity entity, double radius) {
		super(entity, radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resolveCollision(Collider2D other) {
		if(other.getEntity().name == "asteroid") {
			this.getEntity().deactivate();
			System.out.println("GAME OVER!");
		}
		
	}

}
