
public class Result<T> {
	public String mode;
	public String cTech;
	public String pText;
	public String cText;
	public Key<T> key;
	
	public Result(String mode, String cTech, String cText, Key<T> key, boolean print) {
		this.mode = mode;
		this.cTech = cTech;
		this.cText = cText;
		this.key = key;
		if((mode.equals("Encryption") || mode.equals("")) && print)
		System.out.println("-----------------------------------------------------\nTechnique: " + mode +"\nCipher technique: "+cTech+"\nCipher-text: "+cText.toUpperCase()+"\n-----------------------------------------------------");
		else if (mode.equals("Decryption"))System.out.println("-----------------------------------------------------\nTechnique: " + mode +"\nCipher technique: "+cTech+"\nPlain-text: "+cText.toLowerCase()+"\n-----------------------------------------------------");
	}
}
