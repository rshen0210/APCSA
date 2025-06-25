import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
/**
 *	MVCipher - This program encrypts or decrypts a txt file
 *	
 *	@author	Ryan Shen
 *	@since 9/25/23
 */
public class MVCipher {
	
	private String cypher;	//the key that the encrypter will use
	private String inFileName;	//name of input file
	private String outFileName;	//name of output file
	private File outFile; //output file
	private File inFile; // input file
	private int whatToDo; //whether to encrypt to decrypt
		
	/** Constructor */
	public MVCipher() 
	{ 
		cypher = new String("");
		inFileName = new String("");
		outFileName = new String("");
		whatToDo = 0;
	}
	/**
	 * Main
	 */
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Run will ask the user for a key, to encrypt or decrypt, the 
	 * 	input and output files. then calls methods to encrypt to decrypt
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		do {
			cypher = Prompt.getString("Please input a word to use as a key" 
			+ " (letters only)");
		} while(!isString(cypher));
		cypher = cypher.toUpperCase();
		whatToDo = Prompt.getInt("\n\nEncrypt or decrypt?",1,2);
		if(whatToDo == 1)
			inFileName = Prompt.getString("\nName of file to encrypt");
		else
			inFileName = Prompt.getString("\nName of file to decrypt");
		outFileName = Prompt.getString("\nName of output file");
		readFiles();
		if(whatToDo == 1) {
			System.out.println("The encrypted file " + inFileName + " has been " +
					"created using the keyword -> " + cypher);
		}
		else {
			System.out.println("The decrypted file " + inFileName + " has been " +
					"created using the keyword -> " + cypher);
		}
	}
	/**
	 *Creates a scanner to read from the given input file ( a field)
	 *Goes through the entire text file and calls encryptUpper and
	 *encryptLower to encrypt/decrypt alpha characters a given amount. The
	 * output is printed to a file with the user's preferred name
	 * then closes the printwriter object. 
	 */
	public void readFiles() {
		Scanner input = FileUtils.openToRead(inFileName);
		PrintWriter output = FileUtils.openToWrite(outFileName);
		char whichKey = '!';
		int keyLength = cypher.length();
		int c = 0;
		String outLine = new String("");
		boolean eOrD = false;
		if(whatToDo == 2)
			eOrD = false;
		else
			eOrD = true;
		
		while(input.hasNextLine()) {
			String line = input.nextLine();

			for(int i = 0; i < line.length(); i++) {
				char key = '!';
				c++;
				if(c == cypher.length())
					c = 0;
				whichKey = cypher.charAt(c);
				if(line.charAt(i) <= 'Z'&& line.charAt(i) >= 'A') {
					key = encryptUpper(line.charAt(i),whichKey,eOrD);
				}
				else if(line.charAt(i) <= 'z' && line.charAt(i) >= 'a')
					key = encryptLower(line.charAt(i),whichKey,eOrD);
				else 
					key = line.charAt(i);
				outLine += key;
			}
			output.println(outLine);
			outLine = "";
		}
		output.close();
	}
	
	/**
	 * Shifts the uppercase letter over the given amount. Unshifts the letter
	 * over the given amount given whether you want to decrypt or not. 
	 * Precondition: The next character in the text file is uppercase
	 * @param letter  The letter that you want to shift
	 * @param key 	  The amount you want to shift the letter by
	 * @param encrypt Whether you want to encrypt or decrypt
	 * @return the shifted char
	 */
	public char encryptUpper(char letter, char key, boolean encrypt) {
		int shift = 0;
		char shiftedLetter = '!';
		if(encrypt)
			shift = key - '@';
		else
			shift = (-1)*(key-'@');
		shiftedLetter = (char)((int)letter + shift);
		if(shiftedLetter > 'Z')
			shiftedLetter -= 26;
		if(shiftedLetter < 'A')
			shiftedLetter += 26;
		return shiftedLetter;
	}
	/**
	 * Shift the lowercase letter over the given amount. Unshifts the letter
	 * over the given amount given whether you want to decrypt or not. 
	 * Precondition: The next character in the text file is lowercase
	 * @param letter  The letter that you want to shift
	 * @param key 	  The amount you want to shift the letter by
	 * @param encrypt Whether you want to encrypt or decrypt
	 * @return the shifted char
	 */
	public char encryptLower(char letter, char key, boolean encrypt) {
		int shift = 0;
		char shiftedLetter = '!';
		if(encrypt)
			shift = key - '@';
		else
			shift = (-1)*(key-'@');
		shiftedLetter = (char)((int)letter + shift);
		if(shiftedLetter > 'z')
			shiftedLetter -=26;
		if(shiftedLetter < 'a')
			shiftedLetter += 26;

		return shiftedLetter;
	}
	/**
	 * Returns true if the string is made of only letters and more than
	 * three letters long. Returns false when the string has other chars
	 * or is less than 3 letters long
	 * Precondition: The user entered something when prompted
	 * @param str The string that is passed in 
	 * @return If the string has only letters and is more than three chars
	 */
	public boolean isString(String str) {
		int l = str.length();
		char c = '1';
		if(l < 3)
			return false;
		for(int i = 0; i < l; i++) {
			c = str.charAt(i);
			if( c < 'A')
				return false;
			else if(c > 'Z' && c < 'a')
				return false;
			else if(c > 'z')
				return false;
		}
		return true;
	}
		
}
