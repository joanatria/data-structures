import static org.junit.Assert.*;

import org.junit.Test;


public class StackUnitTest {

	@Test
	public void test() {
		Stack s = new LinkedStack();
		
		for(int i = 0; i < 50; i++) {
			s.push(i);
			assertEquals(i, s.top());
		}
		
		for(int i = 49; i >= 0; i--) {
			assertEquals(i, s.pop());
		}
	}

}
