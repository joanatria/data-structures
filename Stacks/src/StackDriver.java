import java.util.*;

public class StackDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		Stack s;
		
		Scanner keyboardInput = new Scanner(System.in);
		System.out.println("Type of stack, (1) Array Stack (2) Linked Stack:");
		int choice = keyboardInput.nextInt();
		if (choice == 1) {
			s = new ArrayStack();
		}
		else {
			s = new LinkedStack();
		}
				
		for(int i = 0; i < 50; i++) {
			s.push(i);
		}
		
		for(int i = 0; i < 25; i++) {
			System.out.println(s.pop());
		}
		
		for(int i = 0 ; i < 20; i++) {
			s.push(100 * i);
		}
		
		for(int i = 0; i < 25; i++) {
			System.out.println(s.pop());
		}
		
		s.push("Hello World");
		s.push("H");
		
		keyboardInput.close();

	}

}
