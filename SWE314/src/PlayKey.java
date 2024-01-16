import java.util.Random;
import java.util.*;

public class PlayKey extends Key{
	char[][] matrix = new char[5][5];
	boolean encrypt = true;
	int currentRandom;
	char[] options = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	char[] optionsTemp;
	
	public PlayKey() {
		Random r = new Random();
		for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
            	currentRandom = r.nextInt(options.length);
                matrix[row][col] = options[currentRandom];
                optionsTemp = new char[options.length - 1];
                for(int i = 0, k = 0; i < options.length; i++) {
                	if(i == currentRandom) continue;
                	optionsTemp[k++] = options[i];
                }
                options = optionsTemp; 
            }
        }
	}
	
	public PlayKey(char[][] matrix) {
		this.matrix = matrix;
	}
	
	public void setEncrypt() {
		if (encrypt == false) {
		    for (int i = 0; i < 5; i++) {

		        int start = 0;
		        int end = 5 - 1;
		        while (start < end) {
		            char temp = matrix[i][start];
		            matrix[i][start] = matrix[i][end];
		            matrix[i][end] = temp;
		  
		            start++;
		            end--;
		        }
		    }
	}
		
		encrypt = true;
	}
	
	public void setDecrypt() {
		if (encrypt == true) {

			    for (int i = 0; i < 5; i++) {

			        int start = 0;
			        int end = 5 - 1;
			  
			        // Till start < end, swap the element
			        // at start and end index
			        while (start < end) {
			  
			            // Swap the element
			            char temp = matrix[i][start];
			            matrix[i][start] = matrix[i][end];
			            matrix[i][end] = temp;
			  
			            // Increment start and decrement
			            // end for next pair of swapping
			            start++;
			            end--;
			        }
			    }
		}
		encrypt = false;
	}
	
	public char[][] getKeyValue(){
		return matrix;
	}
	
	public String toString() {
		String result = "\n";
		for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                result += (matrix[row][col] + "\t");
            }
            result += "\n";
		}
		return result;
	}
}
