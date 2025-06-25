import java.util.List;
import java.util.ArrayList;
/**
 *	This is a simple calculator involving the use of stacks 
 *  to compute math expressions. 
 *
 *	@author	Ryan Shen
 *	@since	3/7/24
 */


public class SimpleCalc {
	
	private ExprUtils utils;	// Expression utilities
	private ArrayStack<Double> valueStack;		// Value stack
	private ArrayStack<String> operatorStack;	// Operator stack
	private ArrayList<Identifier> variables;    // Database for variables

	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		variables = new ArrayList<>();
		variables.add(new Identifier("e", Math.E));
		variables.add(new Identifier("pi", Math.PI));
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	/**
	 * Initializes and starts the calculator, displaying a welcome message, handling user input, and closing with a goodbye message.
	 */
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!\n");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**
	 * Processes user input continuously until the 'q' command is entered. Handles help, listing variables, and evaluating expressions or assignments.
	 */
	public void runCalc() {
		String in = "";
		while (!in.trim().equals("q")) {
			in = Prompt.getString("");
			if (in.trim().equals("h")) 
				printHelp();
			else if (in.trim().equals("l"))
				listVariables();
			else if (!in.trim().equals("q")) {
				if (in.contains("=")) {
					handleAssignment(in);
				} 
                else {
					double val = evaluateVariableExpression(utils.tokenizeExpression(in));
					System.out.println(val);
				}
			}	
		}
	}
	
	/**
	 * Prints help information including available commands and expression formats.
	 */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  l - list variables\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers, variables, and special constants (pi, e)");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'\n");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {	
		for(int i = 0; i < tokens.size(); i++){
			String str = tokens.get(i);
			if (Character.isDigit(str.charAt(0)))
				valueStack.push(Double.parseDouble(str));
			else if (str.equals("("))
				operatorStack.push(str);
			else if (str.equals(")")) {
				while (!operatorStack.peek().equals("(")) {
					valueStack.push(evalTopTwo());
				}
				operatorStack.pop();
			}
			else if (operatorStack.isEmpty() || !operatorStack.isEmpty() && hasPrecedence(operatorStack.peek(), str))
				operatorStack.push(str);
			else  {
				valueStack.push(evalTopTwo());
				operatorStack.push(str);
			}
		}
		while (!operatorStack.isEmpty()) {
			valueStack.push(evalTopTwo());
		}
		
		return valueStack.pop();
	}
	
	/**
	 * pops two from operator stack and performs the top of operator stack operation
     * @param none
	 * @return a double value of the evaluated expression
	 */
	public double evalTopTwo(){
		String operation = operatorStack.pop();
		double top = valueStack.pop();
        double bottom = valueStack.pop();
		switch(operation){
			case "+":
				return bottom + top;
			case "-":
				return bottom - top;
			case "*":
				return bottom * top;
			case "/":
				return bottom / top;
			case "%":
				return bottom % top;			
			case "^":
				return Math.pow(bottom, top);		
		}
		return 0.0;
	}
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher precedence; false otherwise
	 *	Algorithm:
	 * 		if op1 is either left or right parenthesis, then true
	 *		if op2 is exponent, and op1 is not exponent then true
	 *		if op1 is addition or subtraction and 
	 *				op2 is multiplication or division or modulus, then true
	 *		otherwise false
	 */
	public boolean hasPrecedence(String op1, String op2) {
		if (op1.equals("(") || op1.equals(")")) return true;
		if (op2.equals("^") && !op1.equals("^")) return true;	
		if ((op1.equals("+") || op1.equals("-"))
				&& (op2.equals("*") || op2.equals("/") || op2.equals("%")))
			return true;	
		return false;
	}
	
	/**
	 * replaces variables with their actual value
     * then evaluates the expression
	 * @param tokens 
	 * @return the evaluated expression in the type, double
	 */
	public double evaluateVariableExpression(List<String> tokens) {
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			if (!Character.isDigit(token.charAt(0)) && (token.length() == 1 && !utils.isOperator(token.charAt(0)))) {
				tokens.set(i, String.valueOf(getVariableValue(token)));
			} else if (!Character.isDigit(token.charAt(0)) && token.length() > 1) {
				tokens.set(i, String.valueOf(getVariableValue(token))); 
			}
		}
		return evaluateExpression(tokens);
	}

	 /**
	 * Retrieves the value of a variable by its name.
	 * @param name the name of the variable
	 * @return the double value of the variable or 0.0 if it does not exist
	 */
	public double getVariableValue(String name) {
		for (Identifier var : variables) {
			if (var.getKey().equals(name)) {
				return var.getValue();
			}
		}
		return 0.0; 
	}
	
	/**
	 * Processes an assignment operation, updates existing variables or adds new ones with the assigned value.
	 * @param input the user input containing an assignment expression
	 */
	public void handleAssignment(String input) {
		String[] parts = input.split("=", 2);
		String varName = parts[0].trim();
		double value = evaluateVariableExpression(utils.tokenizeExpression(parts[1]));
		updateOrAddVariable(varName, value);
		System.out.printf("%s = %.7f\n", varName, value);
	}

	/**
	 * Updates the value of an existing variable or adds a new variable to the list if it does not exist.
	 * @param name the name of the variable
	 * @param value the new value to be assigned to the variable
	 */
	public void updateOrAddVariable(String name, double value) {
		for (Identifier var : variables) {
			if (var.getKey().equals(name)) {
				var.setValue(value);
				return;
			}
		}
		variables.add(new Identifier(name, value));
	}

	/**
	 * Lists all variables along with their current values.
	 */
	public void listVariables() {
		System.out.println("Variables:");
		for (int i = 0;i<variables.size();i++) {
			System.out.println(variables.get(i));
		}
	}
}