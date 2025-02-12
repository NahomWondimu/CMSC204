
public class WeakPasswordException extends RuntimeException {
	public WeakPasswordException() {
		super(Exception_Messages.WEAK_PWD_MSG);
	}
		
	public WeakPasswordException(String message) {
		super(message);
	}

}
