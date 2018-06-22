package Services;

import java.util.ArrayList;

public class KeyValueService<T> implements Service {

	ArrayList<KeyValue<T>> keyValueList = new ArrayList<KeyValue<T>>();
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}
	
	public T getValue(String key) {
		for(int i = 0; i < keyValueList.size(); i++) {
			if(keyValueList.get(i).checkKey(key))
				return keyValueList.get(i).value;
		}
		return null;
	}
	
	public void addValue(String key, T value) {
		keyValueList.add(new KeyValue<T>(key, value));
	}
	
	public void setValue(String key, T value) {
		for(int i = 0; i < keyValueList.size(); i++) {
			if(keyValueList.get(i).checkKey(key))
				keyValueList.get(i).value = value;
		}
	}

}
