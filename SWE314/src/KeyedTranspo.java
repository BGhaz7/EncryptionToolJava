import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyedTranspo {
	
	KTranspoKey key = new KTranspoKey();
	int[] keyValue = key.getKeyValue();
	public ArrayList<Result> resultHistoryKeyed = new ArrayList<Result>();
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
	
	private String[] format(String plain) {
		while(plain.length() % 4 != 0) {
			plain += "x";
		}
		String[] formatted = new String[plain.length()/4];
		for(int i = 0, counter = 0; i < plain.length()/4; i++) {
			formatted[i] = plain.substring(counter,counter+=4);
		}
		return formatted;
	}
	
	public void encryptKeyedTranspo() {
		String plain = recievePlainText();
		String[] plainArr = format(plain);
		char[] tempArr; char[] temp2;
		for(int i = 0; i < plainArr.length; i++) {
			tempArr = plainArr[i].toCharArray();
			temp2 = plainArr[i].toCharArray();
				for(int j = 0; j< keyValue.length; j++) {
					tempArr[keyValue[j]] = temp2[j];  
				}
			plainArr[i] = String.valueOf(tempArr);
		}
		
		StringBuffer sb = new StringBuffer();
	    for(int i = 0; i < plainArr.length; i++) {
	        sb.append(plainArr[i]);
	      }
	    String cipherText = sb.toString();
	    Result<KTranspoKey> r = new Result<KTranspoKey>("Encryption", "Keyed Transposition",cipherText, key, true);
	    resultHistoryKeyed.add(r);
	}
	
	public Result encryptKeyedTranspo(String plain) {
		String[] plainArr = format(plain);
		char temp; char[] tempArr; char[] temp2;
		for(int i = 0; i < plainArr.length; i++) {
			tempArr = plainArr[i].toCharArray();
			temp2 = plainArr[i].toCharArray();
			for(int k = 0; k < tempArr.length; k++) {
					for(int j = 0; j< keyValue.length; j++) {
						tempArr[keyValue[j]] = temp2[j];  
					}
			}
			plainArr[i] = String.valueOf(tempArr);
		}
		
		StringBuffer sb = new StringBuffer();
	    for(int i = 0; i < plainArr.length; i++) {
	        sb.append(plainArr[i]);
	      }
	    String cipherText = sb.toString();
	    Result<KTranspoKey> r = new Result<KTranspoKey>("Encryption", "Monoalphabetic + Keyed Transposition", cipherText, key, true);
	    return r;
	}
	
	
}
