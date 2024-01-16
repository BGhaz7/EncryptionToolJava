import java.util.*;
public class Combination {
	Monoalphabetic M = new Monoalphabetic();
	KeyedTranspo K = new KeyedTranspo();
	
	public void encryptCombo(){
		M.EncryptMonoalphabetic();
		String cipher1 = M.resultHistoryMono.get(0).cText;
		String cipher2 = K.encryptKeyedTranspo(cipher1).cText;
	}
}
