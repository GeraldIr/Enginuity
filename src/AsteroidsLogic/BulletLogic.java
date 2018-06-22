package AsteroidsLogic;

import Collision.Collider2D;
import Collision.CollisionLogic;
import EntityManagement.Entity;
import Services.KeyValueService;
import Services.ServiceLocator;

public class BulletLogic extends Collider2D implements CollisionLogic  {

	public BulletLogic(Entity entity, double radius) {
		super(entity, radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void resolveCollision(Collider2D other) {
		
		if((other.getEntity().name == "asteroid")) {
			((KeyValueService<Integer>)ServiceLocator.getInstance().get(KeyValueService.class)).setValue("score", ((KeyValueService<Integer>)ServiceLocator.getInstance().get(KeyValueService.class)).getValue("score")+1);
			this.getEntity().deactivate();
		}
	}

}
