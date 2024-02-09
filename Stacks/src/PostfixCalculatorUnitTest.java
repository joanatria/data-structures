import junit.framework.TestCase;


public class PostfixCalculatorUnitTest extends TestCase {
	public void testBasicCase() {
		PostfixCalculator postfix1 = new PostfixCalculator("8 4 +");
		assertEquals(postfix1.evaluatePostfixExpression(), 12);
		
		postfix1 = new PostfixCalculator("8 4 -");
		assertEquals(postfix1.evaluatePostfixExpression(), 4);
		
		postfix1 = new PostfixCalculator("8 4 *");
		assertEquals(postfix1.evaluatePostfixExpression(), 32);
		
		postfix1 = new PostfixCalculator("8 4 /");
		assertEquals(postfix1.evaluatePostfixExpression(), 1);
		System.out.println(countTestCases());
	}
	
	public void testLongerCase() {
		PostfixCalculator postfix1 = new PostfixCalculator("2 3 8 * + 4 48 4 2 + / 6 * + - ");
		assertEquals(postfix1.evaluatePostfixExpression(), -26);
		
		postfix1 = new PostfixCalculator("14 15 + 16 *");
		assertEquals(postfix1.evaluatePostfixExpression(), 464);
	}
}
