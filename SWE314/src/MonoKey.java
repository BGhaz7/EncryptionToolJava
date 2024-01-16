
public class MonoKey extends Key{
	int value;
	
	//When called without parameters, we will set random key.
	public MonoKey() {
		value = (int)Math.floor(Math.random()*(100-1+1)+1);
	}
	
	public MonoKey(int number) {
		value = number;
	}
	
	public String toString() {
		return Integer.toString(value);
	}
	
	public Integer getKeyValue() {
		return value;
	}
}
