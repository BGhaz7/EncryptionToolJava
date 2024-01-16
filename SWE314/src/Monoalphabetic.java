import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Monoalphabetic {
	
		public char alphabet[] = {'0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		int outOfBoundNumber;
		public ArrayList<Result> resultHistoryMono = new ArrayList<Result>();
		private char[] recievePlainText() {
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter plain-text: ");
			String plain = scan.nextLine();
			plain = plain.replaceAll("\\s", "");
			Pattern p = Pattern.compile("\\d+");
		    Matcher m = p.matcher(plain);
		    while(m.matches() == true) {
				System.out.print("Invalid input!\nEnter plain-text: ");
				plain = scan.nextLine();
				plain = plain.replaceAll("\\s", "");
				m = p.matcher(plain);
		    }
			return plain.toCharArray();
		}
		
		
		
		public void EncryptMonoalphabetic() {
			MonoKey key = new MonoKey();
			int keyValue = key.getKeyValue();
			char[] plaintext = recievePlainText();
			char[] ciphertext = new char[plaintext.length];
			for(int i = 0; i < plaintext.length; i++) {
				for(int k = 1; k < alphabet.length; k++) {
					try {
						if(Character.toUpperCase(plaintext[i]) == alphabet[k]) {
							ciphertext[i] = alphabet[k + keyValue];
							if(ciphertext[i] == '0') ciphertext[i] = alphabet[k + keyValue + 1];
						}
					} catch(ArrayIndexOutOfBoundsException E) {
						outOfBoundNumber = (k + keyValue);
						while(outOfBoundNumber >= 26) {
							outOfBoundNumber -= 26;
						}
						ciphertext[i] = alphabet[outOfBoundNumber];
						if(ciphertext[i] == '0') ciphertext[i] = alphabet[outOfBoundNumber + 1];
					}
				}
			}
			String str = String.valueOf(ciphertext);
			Result<MonoKey> r = new Result<MonoKey>("Encryption","Monoalphabetic", str, key, true);
			resultHistoryMono.add(r);
		}
		
		public void DecryptMonoalphabetic(int indexChosen){
			char[] cipherText = resultHistoryMono.get(indexChosen).cText.toCharArray();
			char[] plainText = new char[cipherText.length];
			MonoKey key = (MonoKey) resultHistoryMono.get(indexChosen).key;
			int keyValue = key.getKeyValue();
			for(int i = 0; i < cipherText.length; i++) {
				for(int k = 1; k < alphabet.length; k++) {
					try {
						if(Character.toUpperCase(cipherText[i]) == alphabet[k]) {
							plainText[i] = alphabet[k - keyValue];
							if(plainText[i] == '0') plainText[i] = alphabet[k - (keyValue + 1)];
						}
					} catch(ArrayIndexOutOfBoundsException E) {
						outOfBoundNumber = (k - keyValue);
						while(outOfBoundNumber < 0) {
							outOfBoundNumber += 26;
						}
						plainText[i] = alphabet[outOfBoundNumber];
						if(plainText[i] == '0') plainText[i] = alphabet[26];
					}	
				}
		}
			
			String str = String.valueOf(plainText);
			Result<MonoKey> r = new Result<MonoKey>("Decryption","Monoalphabetic", str, key, true);
	}
		
		public String DecryptMonoalphabeticKeyed(String cipher, int key){
			char[] cipherText = cipher.toCharArray();
			char[] plainText = new char[cipherText.length];
			int keyValue = key;
			for(int i = 0; i < cipherText.length; i++) {
				for(int k = 1; k < alphabet.length; k++) {
					try {
						if(Character.toUpperCase(cipherText[i]) == alphabet[k]) {
							plainText[i] = alphabet[k - keyValue];
							if(plainText[i] == '0') plainText[i] = alphabet[k - (keyValue + 1)];
						}
					} catch(ArrayIndexOutOfBoundsException E) {
						outOfBoundNumber = (k - keyValue);
						while(outOfBoundNumber < 0) {
							outOfBoundNumber += 26;
						}
						plainText[i] = alphabet[outOfBoundNumber];
						if(plainText[i] == '0') plainText[i] = alphabet[26];
					}
				}
			
			}
			return String.valueOf(plainText);
		}
}

