import java.lang.NumberFormatException;

/**
 *	Utilities for handling HTML
 *
 *	@author	Ryan Shen
 *	@since	11/09/2023
 */
public class HTMLUtilities {

	private char[] punctuations = new char[] {'.', ',', ';', ':', '(', ')', '?', '!', '=', '&', '~', '+', '-' };

	// Current state in tokenization
	public enum TokenState { NONE, COMMENT, PREFORMAT };
	
	// tokenization state
	private TokenState state = TokenState.NONE;
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		boolean inTag = false;
		boolean inDigit = false;
		int falseDigit = -1;
		int curPos = 0;
		int i = 0;
		while (i < str.length()) {
			char c = str.charAt(i);
			if (result[curPos] == null) {
				result[curPos] = "";
			}
			if (state == TokenState.COMMENT) {
				if (c == '-' || result[curPos].length() > 0) {
					result[curPos] += c;
					if (result[curPos].length() == 3 && result[curPos].equals("-->")) {
						state = TokenState.NONE;
						result[curPos] = null;
					} else if (result[curPos].length() == 2 && !result[curPos].equals("--")) {
						result[curPos] = null;
					}
				} else {
					result[curPos] = null;
				}
			} else if (inTag) {
				if (result[curPos] == null) {
					result[curPos] = "";
				}
				result[curPos] += c;
				if (c == '>') {
					if (result[curPos].toLowerCase().equals("<pre>") && state != TokenState.PREFORMAT) {
						state = TokenState.PREFORMAT;
						inTag = true;
						break;
					} else if (result[curPos].toLowerCase().equals("</pre>") && state == TokenState.PREFORMAT) {
						state = TokenState.NONE;
						inTag = true;
						break;
					} else {
						inTag = false;
						++curPos;
					}
				} else if (result[curPos].equals("<!--") && state != TokenState.COMMENT) {
					result[curPos] = null;
					state = TokenState.COMMENT;
					inTag = false;
				}
			} else if (inDigit) {
				boolean endNum = true;
				if (Character.isDigit(c) || c == 'e' || c == '-' || c == '.') {
					result[curPos] += c;
					endNum = (i == str.length()-1);
					if (endNum) ++i;
				}
				if (endNum) {
					inDigit = false;
					try {
						Double.parseDouble(result[curPos]);
					} catch (NumberFormatException e) {
						// Shouldn't every happen
						// System.err.println("[FAIL] Assertion not met, numbers should be correctly formatted");
						int resL = result[curPos].length();
						result[curPos] = "";
						falseDigit = i;
						i = i - resL;
					}
					if (result[curPos].length() > 0) ++curPos;
					--i;
				}
			} else {
				if (c == '<') {
					inTag = true;
					if (result[curPos] != "" && result[curPos] != null) {
						++curPos;
						result[curPos] = "";
					}
					result[curPos] += c;
				} else if (Character.isWhitespace(c)) {
					if (result[curPos] != "" && result[curPos] != null) ++curPos;
					if (result[curPos] == null) {
						result[curPos] = "";
					}
				} else if (isPunctuation(c)) {
					boolean surrounded = false;
					if (c != '-' || !(i < str.length()-1 && !Character.isWhitespace(str.charAt(i+1)) && i > 0 && !Character.isWhitespace(str.charAt(i-1)))) {
						if (result[curPos] != "" && result[curPos] != null) ++curPos;
					} else {
						surrounded = c == '-';
					}
					if (result[curPos] == null) {
						result[curPos] = "";
					}
					result[curPos] += c;
					if (!surrounded) ++curPos;
				} else if (Character.isLetter(c) || ((falseDigit - i > 0 || result[curPos].length() > 0) && Character.isDigit(c))) {
					result[curPos] += c;
				} else if (Character.isDigit(c)) {
					inDigit = true;
					if (result[curPos] != "" && result[curPos] != null) curPos++;
					--i;
					if (i >= 0 && (str.charAt(i) == '-' || str.charAt(i) == '.')) {
						--curPos;
						--i;
					}
					result[curPos] = "";
				}
			}
			++i;
		}
		
		if (state == TokenState.PREFORMAT && !inTag) {
			String[] singleRes = new String[] {str};;
			return singleRes;
		}
		
		if (result[curPos] != null) ++curPos;
		String[] realRes = new String[curPos];
		for (int j = 0; j < curPos; ++j) {
			realRes[j] = result[j];
		}
		
		// return the correctly sized array
		return realRes;
	}
	
	public boolean isPunctuation(char c) {
		for (int i = 0; i < punctuations.length; ++i) {
			if (c == punctuations[i]) {
				return true;
			}
		}
		return false;
	}
	
	public TokenState getState() {
		return state;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (tokens[a] == null) break;
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}

}