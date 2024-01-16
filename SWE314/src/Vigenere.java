import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vigenere {
	public ArrayList<Result> resultHistoryVige = new ArrayList<Result>();
	VigeKey key = new VigeKey();
	String keyValue = key.getKeyValue();
	
	private String recievePlainText() {
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
		return plain;
	}
	
	
	public void encryptVigenere() {
	    String cipherText = "";
	    String message = recievePlainText();
	    for (int i = 0, j = 0; i < message.length(); i++) {
	      char c = message.toUpperCase().charAt(i);
	      if (c < 'A' || c > 'Z') continue;
	      cipherText += (char)((c + keyValue.charAt(j) - 2 * 'A') % 26 + 'A');
	      j = ++j % keyValue.length();
	    }
	    Result<VigeKey> r = new Result<VigeKey>("Encryption", "Vigenere", cipherText, key, true);
	    resultHistoryVige.add(r);
	  }
	
	public void decryptVigenere(int indexChosen) {
		String cipherText = resultHistoryVige.get(indexChosen).cText;
		String keyValue = (String) resultHistoryVige.get(indexChosen).key.getKeyValue();
		String decryptedMessage = "";
	    for (int i = 0, j = 0; i < cipherText.length(); i++) {
	      char c = cipherText.toUpperCase().charAt(i);
	      if (c < 'A' || c > 'Z') continue;
	      decryptedMessage += (char)((c - keyValue.charAt(j) + 26) % 26 + 'A');
	      j = ++j % keyValue.length();
	    }
	    Result<VigeKey> r = new Result<VigeKey>("Decryption", "Vigenere", decryptedMessage, key, true);
	}
}
