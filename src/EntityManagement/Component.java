package EntityManagement;


public class Component {
	public boolean isActive;
	private Entity entity;
	
	public Component(Entity entity) {
		this.entity = entity;
	}
	
	public void activate() {
		isActive = true;
	}
	
	public void deactivate() {
		isActive = false;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public final <T> T getComponent(Class<T> type) {
		return entity.getComponent(type);
	}
	
	public String toString() {
		return getClass().getEnclosingClass().getName();
	}
	
	
}
