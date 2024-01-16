import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MonoBit {
	final int blockLength = 8;
	MonoBitKey key = new MonoBitKey();
	char keyValue = key.getKeyValue();
	public ArrayList<Result> resultHistoryMonoBit = new ArrayList<Result>();
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
	
	
	public void encryptMonoBit() {
		String plainText = recievePlainText();
		String cipher = "";
		for(int i = 0; i < plainText.length(); i++) {
			cipher += Character.toString((char)(plainText.charAt(i)^keyValue));
		}
		Result<MonoBitKey> r = new Result<MonoBitKey>("Encryption", "Monoalphabetic Bit Level", cipher, key, true);
		resultHistoryMonoBit.add(r);
	}
	
	
	
}
