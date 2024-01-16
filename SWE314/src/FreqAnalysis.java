import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FreqAnalysis {
	public ArrayList<Result> resultHistoryFreq = new ArrayList<Result>();
	public Monoalphabetic M = new Monoalphabetic();
	char[] options = {'0','A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	private char findMaxOccuringChar(String cipher) {
		int N = 256;
		int ctr[] = new int[N];
		int l = cipher.length();
			for (int i = 0; i < l; i++)
				ctr[cipher.charAt(i)]++;
			   	int max = -1;
			   	char result = ' ';

			for (int i = 0; i < l; i++) {
				if (max < ctr[cipher.charAt(i)]) {
			    max = ctr[cipher.charAt(i)];
			    result = cipher.charAt(i);
			   }
			 }

			  return result;
	}
	private String recieveCipherText() {
		System.out.print("Enter cipher-text: ");
		Scanner scan = new Scanner(System.in);
		String cipher = scan.nextLine();
		cipher = cipher.replaceAll("\\s", "");
		Pattern p = Pattern.compile("\\d+");
	    Matcher m = p.matcher(cipher);
	    while(m.matches() == true) {
			System.out.print("Invalid input!\nEnter cipher-text: ");
			cipher = scan.nextLine();
			cipher = cipher.replaceAll("\\s", "");
			m = p.matcher(cipher);
	    }
	    return cipher;
	}
	
	public void attemptDecrypt() {
		String cipher = recieveCipherText();
		char culprit = findMaxOccuringChar(cipher);
		int key = 0;
		switch(Character.toUpperCase(culprit)) {
		case ('A'): key = 4;
		break;
		case 'B': key = 3;
		break;
		case 'C': key = 2;
		break;
		case 'D': key = 1;
		break;
		case 'E': key = 0;
		break;
		default:
			for(int i = 5; i < options.length; i++) {
				if(options[i] == Character.toUpperCase(culprit)) {
					key = i - 5;
					break;
				}
			}
			break;
		}
		
		String attemptDecrypted = M.DecryptMonoalphabeticKeyed(cipher, key);
		Result r = new Result("Decryption", "Frequency Analysis", attemptDecrypted, null, true);
		
	}
}
