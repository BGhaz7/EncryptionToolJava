import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playfair {
	PlayKey key = new PlayKey();
	char[][] keyValue = key.getKeyValue();
	String plain;
	String plainOriginal;
	public ArrayList<Result> resultHistoryPlay = new ArrayList<Result>();
	
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
	
	
	private String formatPlainText(String text) {
		String[] strAr = text.split("");
		List<String> al = new ArrayList<String>(Arrays.asList(strAr));
		for(int j = 0; j < al.size(); j++) {
			if(al.get(j).equalsIgnoreCase("J")) {
				al.set(j, "i");
			}
		}
		for(int i = 0; i < al.size(); i++) {
			if(i != al.size() - 1) {
				if(al.get(i).equalsIgnoreCase(al.get(i+1))) {
					al.add(i+1, "x");
				}
			}
		}
		if(al.size() % 2 != 0) {
			al.add("z");
		}
		 String[] str = new String[al.size()];
	     for (int i = 0; i < al.size(); i++) {
	           str[i] = al.get(i);
	        }
	     
	     StringBuffer sb = new StringBuffer();
	      for(int i = 0; i < str.length; i++) {
	         sb.append(str[i]);
	      }
	      String str2 = sb.toString();
		return str2;
	}
	
	private String traverse(String pair) {
		String result = "";
		char a = pair.charAt(0); char b = pair.charAt(1);
		int[] aPosition = getCharacterPosition(a); int[] bPosition = getCharacterPosition(b);
		int[] newAPosition = aPosition; int[] newBPosition = bPosition;
		//If both letters occur on the same row
		if(aPosition[0] == bPosition[0]) {
			newAPosition[1] = (aPosition[1] + 1) % 5;
			newBPosition[1] = (bPosition[1] + 1) % 5;
			result += Character.toString(keyValue[aPosition[0]][newAPosition[1]]) + Character.toString(keyValue[bPosition[0]][newBPosition[1]]);
			return result;
		} else if(aPosition[1] == bPosition[1]) {
			if(key.encrypt == true) {
				newAPosition[0] = (aPosition[0] + 1) % 5;
				newBPosition[0] = (bPosition[0] + 1) % 5;
				result += Character.toString(keyValue[newAPosition[0]][aPosition[1]]) + Character.toString(keyValue[newBPosition[0]][bPosition[1]]);
			} else {
				newAPosition[0] = (aPosition[0] - 1) % 5;
				newBPosition[0] = (bPosition[0] - 1) % 5;
				if(newAPosition[0] < 0) newAPosition[0] = 4;
				if(newBPosition[0] < 0) newBPosition[0] = 4;
				result += Character.toString(keyValue[newAPosition[0]][aPosition[1]]) + Character.toString(keyValue[newBPosition[0]][bPosition[1]]);
				
			}
			return result;
		} else {
			result += Character.toString(keyValue[aPosition[0]][bPosition[1]]) + Character.toString(keyValue[bPosition[0]][aPosition[1]]);
			return result;
		}
	}
	
	private int[] getCharacterPosition(char a) {
		int[] keyPosition = new int[2];
		for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
            	if(keyValue[row][col] == Character.toUpperCase(a)) {
            		keyPosition[0] = row;
            		keyPosition[1] = col;
            		break;
            	}
            }
        }
		
		return keyPosition;
	}
	
	private String[] pair() {
		String[] paired = new String[plain.length()/2];
		for(int i = 0, counter = 0; i < plain.length()/2; i++) {
			paired[i] = plain.substring(counter,counter+=2);

		}
		return paired;
	}
	
	public void encryptPlayfair() {
		key.setEncrypt();
		plain = formatPlainText(recievePlainText());
		String cipher = "";
		String[] pairs = pair();
		for(int i = 0; i < pairs.length; i++) {
			cipher += traverse(pairs[i]);
		}
	      
	      Result<PlayKey> r = new Result<PlayKey>("Encryption","Playfair", cipher, key, true);
	      resultHistoryPlay.add(r);
	}
	
	public void decryptPlayfair(int indexChosen) {
		key.setDecrypt();
		plain = formatPlainText(resultHistoryPlay.get(indexChosen).cText);
		String cipher = "";
		String[] pairs = pair();
		for(int i = 0; i < pairs.length; i++) {
			cipher += traverse(pairs[i]);
		}
	      
	      Result<PlayKey> r = new Result<PlayKey>("Decryption","Playfair", cipher, key, true);	
	}
	
	
	

}
