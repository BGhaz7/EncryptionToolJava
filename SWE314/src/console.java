import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class console {
	static Monoalphabetic M = new Monoalphabetic();
	static Playfair P = new Playfair();
	static Vigenere V = new Vigenere();
	static KeyedTranspo K = new KeyedTranspo();
	static Combination C = new Combination();
	static MonoBit Mb = new MonoBit();
	static DES D = new DES();
	static FreqAnalysis F = new FreqAnalysis();

	public static void main(String[] args) {
		System.out.println("Welcome to the Encryptor/Decryptor tool!");
		System.out.println("1. Encryption\n2. Decryption\n-1.Exit");
		int choice;
		while(true) {
			choice = getInput(2);
			switch(choice) {
			case 1: 
			{
				displayEncryptOptions();
				System.out.println("Welcome to the Encryptor/Decryptor tool!");
				System.out.println("1. Encryption\n2. Decryption\n-1.Exit");
				break;
			}
			case 2: 
			{
				displayDecryptOptions();
				System.out.println("Welcome to the Encryptor/Decryptor tool!");
				System.out.println("1. Encryption\n2. Decryption\n-1.Exit");
				break;
			}
			case -1: 
			{
				System.out.println("Goodbye! :^)");
				System.exit(0);
			}
			default:
			{
				System.out.println("Invalid input");
				break;
			}
			}
		}

	}
	
	
	public static void displayEncryptOptions() {
		int choiceEncrypt;
		while(true) {
			System.out.println("1. By Monoalphabetic\n2. By Playfair\n3. By Vigenere\n4. By Keyed Transposition\n5. Combination of monoalphabetic and Keyed transposition\n6. By XOR Encryption (bit-level)\n7. By DES\n-1. Exit Encryption");
			choiceEncrypt = getInput(7);
			switch(choiceEncrypt) {
			case 1:
			{
				M.EncryptMonoalphabetic();
				break;
			}
			case 2: 
			{
				P.encryptPlayfair();
				break;
			}
			case 3: 
			{
				V.encryptVigenere();
				break;
			}
			case 4: 
			{
				K.encryptKeyedTranspo();
				break;
			}
			case 5: 
			{
				C.encryptCombo();
				break;
			}
			case 6: 
			{
				Mb.encryptMonoBit();
				break;
			}
			case 7: 
			{
				try {
					D.encryptDES();
				} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException | IOException e) {
					e.printStackTrace();
				}
				break;
			}
			case -1: return;
			default:
			{
				System.out.println("Invalid input");
				break;
			}
		}
		}

}
	
	public static void displayDecryptOptions() {
		int choiceDecrypt;
		int pastEncrypt;
		int size;
		while(true) {
			System.out.println("1. By Monoalphabetic\n2. By Playfair\n3. By Vigenere\n4. By Frequency Analysis\n-1. Exit Decryption");
			choiceDecrypt = getInput(4);
			switch(choiceDecrypt) {
			case 1:
			{
				size = M.resultHistoryMono.size();
				if(size == 0) {
					System.out.println("You havent encrypted yet!");
					break;
				} else {
					System.out.println("Choose past encryption:");
					for(int i = 0; i < size; i++) {
						System.out.println(i+1 + ". " + M.resultHistoryMono.get(i).cText);
					}
					pastEncrypt = getInput(M.resultHistoryMono.size()-1);
					M.DecryptMonoalphabetic(pastEncrypt-1);
					break;
				}
			}
			case 2: 
			{
				size = P.resultHistoryPlay.size();
				if(size == 0) {
					System.out.println("You havent encrypted yet!");
					break;
				} else {
					System.out.println("Choose past encryption:");
					for(int i = 0; i < size; i++) {
						System.out.println(i+1 + ". " + P.resultHistoryPlay.get(i).cText);
					}
					pastEncrypt = getInput(P.resultHistoryPlay.size()-1);
					P.decryptPlayfair(pastEncrypt-1);
					break;
				}
			}
			case 3: 
			{
				size = V.resultHistoryVige.size();
				if(size == 0) {
					System.out.println("You havent encrypted yet!");
					break;
				} else {
					System.out.println("Choose past encryption:");
					for(int i = 0; i < size; i++) {
						System.out.println(i+1 + ". " + V.resultHistoryVige.get(i).cText);
					}
					pastEncrypt = getInput(V.resultHistoryVige.size()-1);
					V.decryptVigenere(pastEncrypt-1);
					break;
				}
			}
			case 4: 
			{
				F.attemptDecrypt();
				break;
			}
			case -1: return;
			default:
			{
				System.out.println("Invalid input");
				break;
			}
		}
		}
	}
	
	public static int getInput(int max) {
		Scanner input = new Scanner(System.in);
		int choice = -1;
		boolean flag;
		do
	    {
	      try
	      {
	    	input = new Scanner(System.in);
	        System.out.print("Enter: ");
	        choice = input.nextInt();
	        flag=false;
	      }
	      catch(Exception e)
	      {
	        System.out.print("!Invalid input! || ");
	        flag=true;
	      }
	    }
	    while(flag && choice <= max);
		return choice;
	}

}