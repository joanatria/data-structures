/**
 * Runtime exception when one tries to push on a stack that
 * is already full.
 * 
 * @author Richard Bryann Chua
 *
 */
public class StackFullException extends RuntimeException {
	public StackFullException(String err){
		super(err);
	}
}
