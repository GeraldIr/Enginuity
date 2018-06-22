package EntityManagement;

//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import Services.ServiceLocator;


public class Entity {
	
	public String name;
	public boolean isActive;
	List<Component> componentList = new ArrayList<Component>();
	
	
	public Entity setName(String name) {
		this.name = name;
		return this;
	}
	
	public void addComponent(Component newComponent) {
		componentList.add(newComponent);
	}
	
	public void removeComponent(Component oldComponent) {
		componentList.remove(oldComponent);
	}
	
	public <T> T getComponent(Class<T> type) {
		for (Component c: componentList) {
			if(type.isInstance(c)) {
				return type.cast(c);		
			}
		}
		return null;
	}
	
	public <T> T findComponent(Class<T> type) throws ComponentNotFoundException {
		for (Component c: componentList) {
			if(type.isInstance(c)) {
				return type.cast(c);		
			}
		}
		throw new ComponentNotFoundException();
	}
	
	public <T> boolean hasComponent(Class<T> type) {
		for (Component c: componentList) {
			if(type.isInstance(c)) {
				return true;		
			}
		}
		return false;
	}
	
	public void activate() {
		isActive = true;
		for(Component c : componentList)
			c.activate();
	}
	
	public void deactivate() {
		isActive = false;
		ServiceLocator.getInstance().get(EntityManager.class).removeEntity(this);
		for(Component c : componentList)
			c.deactivate();
	}
	
//	public Entity initializeWithString(String entityString) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		this.deactivate();
//		componentList.clear();
//		String[] components = entityString.split(";");
//		for(String s : components) {
//			String[] componentParams = s.split(",");
//			Class<?> clazz = Class.forName(componentParams[0]);
//			Constructor<?> constructor = clazz.getConstructor();
//			Object instance = constructor.newInstance();
//			//this.addComponent(Class.forName(s));
//		}
//		
//		this.activate();
//		return this;
//	}
	
	
	public String toString() {
		String entityString = name + ";";
		for(Component c: componentList)	
			entityString += c;
		return entityString;
	}

	
	

	
}
