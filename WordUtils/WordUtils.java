import java.io.File;
import java.util.Scanner;
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Ryan Shen
 *	@since	10/23/23
 */
 
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE =  "wordList.txt";
	
	/* Constructor */
	public WordUtils() { 
		words = new String[90934];
		loadWords();
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () { 
		Scanner input = FileUtils.openToRead(WORD_FILE); // scanner which reads file
		int i = 0; // index through words array
		while (input.hasNextLine()) {
			String line = input.nextLine();
			words[i] = line;
			++i;
		}
		input.close();
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return validWords		array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{		
		String [] validWords; // array of words that satisfy criteria
		int count = 0; // the count for amount of valid words
		String str = ""; // this string is just set equal to each word in words
		for (int i = 0;i<words.length;i++) {
			str = words[i];
			if (isValidWord(str, letters)) {
				count++;
			}
		}
		validWords = new String[count];
		int j = 0; // used for indexing through validWords array
		for (int i = 0;i<words.length;i++) {
			str = words[i];
			if (isValidWord(str, letters)) {
				validWords[j] = str;
				j++;
			}
		}
		return validWords;
	}
	/** finds out if the passed in word is a valid word
	 * 	according to if it uses only letters from the 
	 * 	string letters
	 * 	@param letters	string containing valid letters
	 * 	@param str		string containing the word to check if valid
	 * 	@return 	a boolean that returns true if word is valid
	 */
	public boolean isValidWord (String word, String letters) {
		for (int a = 0;a<word.length();a++) {
			char c = word.charAt(a);
			if (letters.indexOf(c) > -1)
				letters = letters.substring(0,letters.indexOf(c)) + 
							letters.substring(letters.indexOf(c)+1);
			else
				return false;
		}
		return true;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) { 
		System.out.println("");
		for (int i = 1;i<=wordList.length;i++) {
			System.out.printf("%-15s", wordList[i-1]);
			if (i%5 == 0)
			System.out.println("");
		}
		System.out.println("");
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param wordList  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return biggestString  			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		int max = 0; // the current word worth the most points
		String biggestString = ""; // the string in wordList that's worth most
		String str = ""; // the specific word at a certain index of wordList []
		for (int i = 0;i<wordList.length;i++) {
			str = wordList[i];
			if (getScore(str,scoreTable) > max) {
				max = getScore(str,scoreTable);
				biggestString = str;
			}
		}
		return biggestString;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		int wordTotal = 0; // specific int value of a word
		for (int j = 0;j<word.length();j++) {
			wordTotal += scoreTable[word.charAt(j)-'a'];
		}
		return wordTotal;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
}