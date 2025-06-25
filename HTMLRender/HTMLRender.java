import java.util.Scanner;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author	Ryan Shen
 *	@version
 *	@since 11/21/23
 */
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	private HTMLUtilities util;			// HTMLUtilities used in tester

		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
		util = new HTMLUtilities();
	}
	
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		hf.run(args);
	}
	
	private HTMLUtilities.TokenState curState = HTMLUtilities.TokenState.NONE;
	private int lineLength = 0; // the length of the line(helps with wrapping around)
	private boolean bold = false; // boolean controlling whether or not we bold
	private boolean italic = false; // boolean controlling whether or not we italicize
	private boolean quotation = false; // boolean controlling whether or not we use quotes
	private int heading = 0;
	
	private void render() {
		for (int i = 0; i < tokens.length; ++i) {
			String tagLower = tokens[i].toLowerCase();
			if (tokens[i].length() == 0) continue;

			if (curState == HTMLUtilities.TokenState.PREFORMAT) {
				if (!tagLower.equals("<pre>") && !tagLower.equals("</pre>")) {
					browser.printPreformattedText(tokens[i]);
					browser.println();
				} 
				else {
					browser.printBreak();
				}
			} 
			else {
				if (tagLower.equals("<pre>") || 
				tagLower.equals("</pre>") || 
				tagLower.equals("<!doctype html>") || 
				tagLower.equals("<html>") || tagLower.equals("</html>") || 
				tagLower.equals("<body>") || tagLower.equals("</body>")) {
				} // makes sure not to print these tokens
				else if ((tagLower.equals("<q>") || tagLower.equals("</q>")) && !bold && !italic && heading == 0) {
					if (lineLength > 0 && tagLower.length() == 3) {
						if (lineLength + 2 < 80) {
							browser.print(" ");
						} else {
							browser.println();
						}
					}
					browser.print("\"");
					++lineLength;
					quotation = !quotation;
				} 
				else if (tagLower.equals("<p>")) {
					lineLength = 0;
					browser.println();
					browser.println();
				} 
				else if (tagLower.equals("</p>")) {
					lineLength = 0;
					browser.println();
					browser.println();
				} 
				else if (tagLower.equals("<b>") && heading == 0 && !italic && !quotation) {
					bold = true;
				} 
				else if (tagLower.equals("</b>") && bold) {
					bold = false;
				} 
				else if (tagLower.equals("<i>") && heading == 0 && !bold && !quotation) {
					italic = true;
				} 
				else if (tagLower.equals("</i>") && italic) {
					italic = false;
				} 
				else if (tagLower.equals("<hr>")) {
					browser.printHorizontalRule();
					lineLength = 0;
				} 
				else if (tagLower.indexOf("<h") == 0 && !bold && !italic && !quotation) {
					lineLength = 0;
					heading = Integer.parseInt(tokens[i].substring(2, 3));
				} 
				else if (tagLower.indexOf("</h") == 0 && heading > 0) {
					lineLength = 0;
					browser.printBreak();
					heading = 0;
				} 
				else if (tagLower.equals("<br>")) {
					browser.printBreak();
					lineLength = 0;
				} 
				else {
					if (!(util.isPunctuation(tokens[i].charAt(0)) && tokens[i].length() == 1) && lineLength > 0) {
						tokens[i] = " " + tokens[i];
					}
					if (heading > 0) {
						if (heading == 1) {
							if (lineLength + tokens[i].length() > 40) {
								if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
								browser.println();
								lineLength = 0;
							}
							browser.printHeading1(tokens[i]);
							lineLength += tokens[i].length();
						} 
						else if (heading == 2) {
							if (lineLength + tokens[i].length() > 50) {
								if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
								browser.println();
								lineLength = 0;
							}
							browser.printHeading2(tokens[i]);
							lineLength += tokens[i].length();
						} 
						else if (heading == 3) {
							if (lineLength + tokens[i].length() > 60) {
								if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
								browser.println();
								lineLength = 0;
							}
							browser.printHeading3(tokens[i]);
							lineLength += tokens[i].length();
						} 
						else if (heading == 4) {
							if (lineLength + tokens[i].length() > 80) {
								if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
								browser.println();
								lineLength = 0;
							}
							browser.printHeading4(tokens[i]);
							lineLength += tokens[i].length();
						} 
						else if (heading == 5) {
							if (lineLength + tokens[i].length() > 100) {
								if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
								browser.println();
								lineLength = 0;
							}
							browser.printHeading5(tokens[i]);
							lineLength += tokens[i].length();
						} 
						else if (heading == 6) {
							if (lineLength + tokens[i].length() > 120) {
								if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
								browser.println();
								lineLength = 0;
							}
							browser.printHeading6(tokens[i]);
							lineLength += tokens[i].length();
						}
					} 
					else if (bold) {
						if (lineLength + tokens[i].length() > 80) {
							if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
							browser.println();
							lineLength = 0;
						}
						browser.printBold(tokens[i]);
						lineLength += tokens[i].length();
					} 
					else if (italic) {
						if (lineLength + tokens[i].length() > 80) {
							if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
							browser.println();
							lineLength = 0;
						}
						browser.printItalic(tokens[i]);
						lineLength += tokens[i].length();
					} 
					else {
						if (lineLength + tokens[i].length() > 80) {
							if (tokens[i].charAt(0) == ' ') tokens[i] = tokens[i].substring(1);
							browser.println();
							lineLength = 0;
						}
						browser.print(tokens[i]);
						lineLength += tokens[i].length();
					}
				}
			}
		}
	}
	
	public void run(String[] args) {
		// Sample renderings from HtmlPrinter class
		Scanner input = null;
		String fileName = "";
		// if the command line contains the file name, then store it
		if (args.length > 0)
		fileName = args[0];
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		
		// Open the HTML file
		input = FileUtils.openToRead(fileName);

		while (input.hasNext()) {
			String line = input.nextLine();
			System.out.println("\n" + line);
			tokens = util.tokenizeHTMLString(line);
			render();
			curState = util.getState();
		}
		input.close();

		// // Print plain text without line feed at end
		// browser.print("First line");
		
		// // Print line feed
		// browser.println();
		
		// // Print bold words and plain space without line feed at end
		// browser.printBold("bold words");
		// browser.print(" ");
		
		// // Print italic words without line feed at end
		// browser.printItalic("italic words");
		
		// // Print horizontal rule across window (includes line feed before and after)
		// browser.printHorizontalRule();
		
		// // Print words, then line feed (printBreak)
		// browser.print("A couple of words");
		// browser.printBreak();
		// browser.printBreak();
		
		// // Print a double quote
		// browser.print("\"");
		
		// // Print Headings 1 through 6 (Largest to smallest)
		// browser.printHeading1("Heading1");
		// browser.printHeading2("Heading2");
		// browser.printHeading3("Heading3");
		// browser.printHeading4("Heading4");
		// browser.printHeading5("Heading5");
		// browser.printHeading6("Heading6");
		
		// // Print pre-formatted text (optional)
		// browser.printPreformattedText("Preformat Monospace\tfont");
		// browser.printBreak();
		// browser.print("The end");
		
	}
	
	
}