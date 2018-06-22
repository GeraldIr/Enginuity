package Services;
import java.util.ArrayList;
import java.util.List;


public class ServiceLocator {
	
	private static ServiceLocator instance;
	
	List<Service> serviceList;
	
	private ServiceLocator() {
		serviceList = new ArrayList<Service>();
	}
	
	public static ServiceLocator getInstance() {
		if(instance == null)
			instance = new ServiceLocator();			
		return instance;
	}
	
	public void add(Service s) {
		serviceList.add(s);
	}
	
	public <T> T get(Class<T> type) {
		for (Service service : serviceList) {
			if(type.isInstance(service)) {
				return type.cast(service);		
			}
		}
		return null;
	}
	

	
	public <T> boolean hasService(Class<T> type) {
		for (Service service : serviceList) {
			if(type.isInstance(service)) {
				return true;		
			}
		}
		return false;
	}
	
	public void updateAll(double dt) {
		for(Service s : serviceList)
			s.update(dt);
	}
	
	public void startup() {
		for(Service s : serviceList)
			s.startup();
	}
	
	public void shutdown() {
		for(Service s : serviceList)
			s.shutdown();
	}
}
