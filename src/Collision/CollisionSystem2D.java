package Collision;

import java.util.ArrayList;

import Services.Service;

public class CollisionSystem2D implements Service {

	ArrayList<Collider2D> colliders = new ArrayList<Collider2D>();
	
	
	public CollisionSystem2D addCollider(Collider2D collider) {
		colliders.add(collider);
		return this;
	}
	
	public CollisionSystem2D removeCollider(Collider2D collider) {
		colliders.remove(collider);
		return this;
	}
	
	@Override
	public void update(double dt) {
		ArrayList<Collision2D> collisionList = new ArrayList<Collision2D>();
		
		for(int i = 0; i < colliders.size(); i++) {
			for(int j = 0; j < colliders.size(); j++) {
				if(colliders.get(i) != colliders.get(j) && colliders.get(i).isColliding(colliders.get(j))) {
					CollisionLogic logic = colliders.get(i).getComponent(CollisionLogic.class);
					if(logic != null)
						collisionList.add(new Collision2D(colliders.get(j),logic));
				}
			}
		}
		
		for(int x = 0; x < collisionList.size(); x++) {
			collisionList.get(x).resolveCollision();
		}
		
		
		
//		for(Collider2D collider1 : colliders) {
//			for(Collider2D collider2 : colliders) {
//				if(collider1.isColliding(collider2)) {
//					CollisionLogic logic = collider1.getComponent(CollisionLogic.class);
//					if(logic != null)
//						logic.resolveCollision(collider2);
//					System.out.println("Collision detected!");
//				}
//			}
//		}
		
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
