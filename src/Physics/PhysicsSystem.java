package Physics;

import java.util.ArrayList;
import java.util.List;

import EntityManagement.Component;
import EntityManagement.Entity;
import Services.Service;
import at.fhooe.mtd.sgl.math.Vector2d;

public class PhysicsSystem implements Service {

	private double timeStep = 0.001;
	private double timer = 0;
	private Vector2d globalGravity = new Vector2d(0,0); // Force that acts on all PhysicsObjects
	
	
	List<PhysicsObject> physicsObjectList = new ArrayList<PhysicsObject>();
	
	
	public void addPhysicsObject(PhysicsObject newPhysicsObject) {
		physicsObjectList.add(newPhysicsObject);
	}
	
	public void removePhysicsObject(PhysicsObject newPhysicsObject) {
		physicsObjectList.remove(newPhysicsObject);
	}
	
	public PhysicsSystem setGlobalGravity(Vector2d gravity) {
		globalGravity = gravity;
		return this;
	}
	
	
	@Override
	public void update(double dt) {
		timer += dt;
		
		while(timer >= timeStep) {
			for(PhysicsObject p : physicsObjectList) {
				for(Entity e : getEntityList()) {
					if(e.hasComponent(Body2D.class)) {
						e.getComponent(Body2D.class).applyForce(globalGravity.scale(e.getComponent(Body2D.class).getMass()));
					}					
				}
				p.update(timeStep);
			}
				
			timer -= timeStep;			
		}		
	}
	
	public ArrayList<Entity> getEntityList() {
		List<Entity> entityList = new ArrayList<Entity>();
		for(PhysicsObject p : physicsObjectList) {
			if(!entityList.contains(((Component)p).getEntity())) {
				entityList.add(((Component)p).getEntity());
			}
		}
		return (ArrayList<Entity>) entityList;
	}
	
	
	public PhysicsSystem setUpdateTime(double updateTime) {
		this.timeStep = updateTime;
		return this;
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
