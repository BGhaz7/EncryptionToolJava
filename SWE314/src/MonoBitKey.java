
public class MonoBitKey extends Key {
	final int n = 8;
	char[] options = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	char key = generateRandomKey();
	
	public MonoBitKey() {
		generateRandomKey();
	}
	
	public char generateRandomKey() {
			int index = (int)(options.length * Math.random());
			char value = options[index];
			return value;
	}
	
	public String toString() {
		return Character.toString(key);
	}
	
	public Character getKeyValue() {
		return key;
	}

}
