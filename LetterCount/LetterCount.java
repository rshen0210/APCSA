/**
 *	Counts the frequency of letters in a file and produces a histogram.
 *
 *	@author Ryan Shen
 *	@since	9/14/23
 */
import java.util.Scanner;


public class LetterCount {
	
	// Fields go here, all must be private
	private int [] chCount; // this array will keep track of all the char occurrences for each char
	/* Constructor */							
	public LetterCount() {
		chCount = new int[26]; //This array is size 26 because there are 26 letters in alphabet
	}
	
	/* Main routine */
	public static void main(String[] args) {
		LetterCount lc = new LetterCount(); // creating an instance of the class
		if (args.length != 1)
			System.out.println("Usage: java LetterCount <inputFile>");
		else
			lc.run(args);
	}
	
	/**	The core program of the class, it
	 *	- opens the input file
	 *	- reads the file and counts the letters
	 *	- closes the input file
	 *	- prints the histogram of the letter count
	 */
	public void run(String[] args) {
		String fileName = args[0];
		Scanner infile = FileUtils.openToRead(fileName); // creates a scanner to read from the file
		while (infile.hasNextLine()) {
			String str = infile.nextLine(); // gets the next line from the file (I'm counting chars line by line)
			str = str.toLowerCase(); // converts all to lowercase because program is case insensitive
			for (int i = 0;i<str.length();i++) {
				for (int k = 97;k<=122;k++) {
					if ((int)(str.charAt(i)) == k) {
						chCount[k-(int)('a')]++;
					}
				}
			}
		}
		// this for loop finds the char with the most occurrences
		int max = chCount[0];
		for (int b = 0;b<26;b++) {
			if (max < chCount[b])
			{
				max = chCount[b];
			}
		}
		/** this for loop uses the previous max and finds the ratio of the character occurrences
		 *	to the largest character occurrences. With this knowledge, it prints out 
		 *	the number of plusses for each character.
		 */
		for (int j = 0;j<26;j++) {
			double ratio = chCount[j]/(max*1.0); // finds the ratio of a certain char to the largest char
			int plusCount = (int)(60*ratio); // number of plusses for that specific char
			System.out.print(" "+(char)(j+97));
			System.out.printf("%8s%s", chCount[j], " ");
			for (int b = 0;b<plusCount;b++) {
				System.out.print("+");
			}
			System.out.println("");
		}
		
	}
	
}