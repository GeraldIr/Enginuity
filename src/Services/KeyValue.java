package Services;

public class KeyValue<T> {
	
	String key;
	T value;
	
	public KeyValue(String key, T value){
		this.key = key;
		this.value = value;
	}
	
	public boolean checkKey(String key) {
		return this.key == key;
	}
}
