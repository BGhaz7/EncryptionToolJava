
public class VigeKey extends Key {
	final int n = 8;
	String key;
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	StringBuilder sb = new StringBuilder(n);
	
	public VigeKey() {
		key = generateRandomKey();
	}
	
	public String generateRandomKey() {
		for(int i = 0; i < 8; i++) {
			int index = (int)(alphabet.length() * Math.random());
			sb.append(alphabet.charAt(index));
		}
		return sb.toString();
	}
	
	public char[][] generateVigenereTable() {
	    char[][] table = new char[26][26];
	    for (int i = 0; i < 26; i++) {
	      for (int j = 0; j < 26; j++) {
	        table[i][j] = (char)('A' + (i + j) % 26);
	      }
	    }
	    return table;
	}
	
	public String getKeyValue() {
		return key;
	}
	
	public String toString() {
		return key;
	}
}
