import java.util.Random;

public class KTranspoKey extends Key{
	final int n = 4;
	Random r = new Random();
	int currentRandom;
	int[] key = new int[n];
	int[] numbers = {0,1,2,3};
	int[] numbersTemp;
	
	public KTranspoKey() {
		generateRandomKey();
	}
	
	public void generateRandomKey() {
		for(int i = 0; i < n; i++) {
			currentRandom = r.nextInt(numbers.length);
			key[i] = numbers[currentRandom];
			numbersTemp = new int[numbers.length - 1];
			for(int j = 0, k = 0; j < numbers.length; j++) {
            	if(j == currentRandom) continue;
            	numbersTemp[k++] = numbers[j];
            }
            numbers = numbersTemp;
		}
	}
	
	public int[] getKeyValue() {
		return key;
	}
	
	public String toString() {
		String r = "";
		for(int i = 0; i < key.length; i++) {
			r += key[i];
		}
		return r;
		
	}
	
	
	
}
