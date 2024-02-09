import java.util.*;

/**
 * Postfix Calculator
 * 
 * An application that accepts a postfix expression
 * and evaluates it 
 * 
 * @author Richard Bryann Chua
 *
 */
public class PostfixCalculator {
	private String postfixExpression;	
	
	public PostfixCalculator(String postfixExpression) {
		this.postfixExpression = postfixExpression;
	}
	
	public int evaluatePostfixExpression() {
		StringTokenizer tokens = new StringTokenizer(postfixExpression);
		Stack postfixValuesStack = new LinkedStack();
		
		while(tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			if (isOperator(token)){
				int secondOperand = ((Integer) postfixValuesStack.pop()).intValue();
				int firstOperand = ((Integer) postfixValuesStack.pop()).intValue();
				int answer = 0;
				if (token.equals("+")) {
					answer = firstOperand + secondOperand;
				}
				else if (token.equals("-")) {
					answer = firstOperand - secondOperand;
				}
				else if (token.equals("*")) {
					answer = firstOperand * secondOperand;
				}
				else if (token.equals("/")) {
					answer = firstOperand / secondOperand;
				}
				postfixValuesStack.push(new Integer(answer));
			}
			else {
				postfixValuesStack.push(new Integer(token));
			}
		}
		
		return ((Integer) postfixValuesStack.pop()).intValue();
	}
	
	private boolean isOperator(String token) {
		return (token.equals("+") || 
				token.equals("-") || 
				token.equals("*") || 
				token.equals("/"));			
	}
	
	public static void main(String[] args) {			
		System.out.println("Input the postfix expression " 
				+ "(operands, operators are separated by spaces):");
		Scanner input = new Scanner(System.in);
		String postfixExpression = input.nextLine();
		PostfixCalculator calculator = new PostfixCalculator(postfixExpression);
		System.out.println("The value of the expression is " + 
				calculator.evaluatePostfixExpression());
		input.close();
	}
}
