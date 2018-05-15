package Physics;

import java.util.ArrayList;
import java.util.List;

import Services.Service;

public class PhysicsSystem implements Service {

	private double updateTime = 0.001;
	private double timer = 0;
	
	
	List<PhysicsObject> physicsObjectList = new ArrayList<PhysicsObject>();
	
	
	public void addPhysicsObject(PhysicsObject newPhysicsObject) {
		physicsObjectList.add(newPhysicsObject);
	}
	
	@Override
	public void update(double dt) {
		timer += dt;
		
		while(timer >= updateTime) {
			for(PhysicsObject p : physicsObjectList)
				p.update(updateTime);
			//System.out.println("dt:" + dt);
			timer -= updateTime;
			
			System.out.println("Simulating " + physicsObjectList.size() + " Objects. \n Timer: " + timer);
		}

		
	}
	
	
	public PhysicsSystem setUpdateTime(double updateTime) {
		this.updateTime = updateTime;
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
