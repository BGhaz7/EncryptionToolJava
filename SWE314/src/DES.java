import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DES {
	public ArrayList<Result> resultHistoryDES = new ArrayList<Result>();
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
	
	public void encryptDES() throws IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String plain = recievePlainText();
		byte[] plainB = plain.getBytes(); //CONVERT TO BYTES
		
		KeyGenerator KGen = KeyGenerator.getInstance("DES");
        SecretKey DESKEY = KGen.generateKey(); //Generate Key
        
        Cipher myCipher = Cipher.getInstance("DES"); // Set DES algo
        
        //Encryption
        myCipher.init(Cipher.ENCRYPT_MODE, DESKEY);
        byte[] myEncryptedBytes = myCipher.doFinal(plainB);
        String cipher = new String(myEncryptedBytes);
        
		Result r = new Result("Encryption", "DES", cipher, null, true);
		resultHistoryDES.add(r);
	}
	
}
