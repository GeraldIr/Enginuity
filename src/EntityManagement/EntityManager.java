package EntityManagement;

import java.util.ArrayList;
import java.util.List;

import Services.Service;

public class EntityManager implements Service {
	List<Entity> entityList = new ArrayList<Entity>();
	List<Command> commandList = new ArrayList<Command>();
	
	private static interface Command {
		public void execute();
	}
	
	public void addEntity(Entity newEntity) {
		commandList.add(() -> addEntityInternally(newEntity));
	}
	
	private void addEntityInternally(Entity newEntity) {
		entityList.add(newEntity);
		System.out.println("new entity added: " + newEntity.getClass().getName());
		newEntity.activate();
	}

	public void removeEntity(Entity e) {
		commandList.add(() -> removeEntityInternally(e));
	}
	
	private void removeEntityInternally(Entity e) {
		entityList.remove(e);
		
	}

	public void removeAll() {
		entityList.clear();
	}

	@Override
	public void update(double dt) {
		for(Command c : commandList) {
			c.execute();
		}
		commandList.clear();	
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
