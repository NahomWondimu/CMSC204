
public class NoLowerAlphaException extends RuntimeException {
	public NoLowerAlphaException() {
		super(Exception_Messages.LOWER_ALPHA_EXCEPTION_MSG);
	}
	
	public NoLowerAlphaException(String message) {
		super(message);
	}

}
