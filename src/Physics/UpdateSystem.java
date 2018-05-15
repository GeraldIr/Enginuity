package Physics;

import java.util.ArrayList;
import java.util.List;

import Services.Service;

public class UpdateSystem implements Service {

	List<Updateable> physicsObjectList = new ArrayList<Updateable>();
	
	
	public void addPhysicsObject(Updateable newPhysicsObject) {
		physicsObjectList.add(newPhysicsObject);
	}
	@Override
	public void update(double dt) {
		for(Updateable p : physicsObjectList)
			p.update(dt);
		
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
