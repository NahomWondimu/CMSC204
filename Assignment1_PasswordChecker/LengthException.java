
public class LengthException extends RuntimeException {
	public LengthException() {
		super(Exception_Messages.INVALID_LENGTH_EXCEPTION_MSG);
	}
	
	public LengthException(String message) {
		super(message);
	}
	

}
