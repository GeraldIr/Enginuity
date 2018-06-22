package Physics;

import java.util.ArrayList;
import java.util.List;

import Services.Service;

public class UpdateSystem implements Service {

	List<Updateable> updateableList = new ArrayList<Updateable>();
	
	
	public void addUpdateable(Updateable newUpdateable) {
		updateableList.add(newUpdateable);
	}
	
	public void removeUpdateable(Updateable oldUpdateable) {
		updateableList.remove(oldUpdateable);
	}
	
	@Override
	public void update(double dt) {
		for(int x = 0; x < updateableList.size(); x++)
			updateableList.get(x).update(dt);
		
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
