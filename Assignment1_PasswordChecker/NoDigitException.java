
public class NoDigitException extends RuntimeException {
	public NoDigitException() {
		super(Exception_Messages.DIGIT_EXCEPTION_MSG);
	}
	
	public NoDigitException(String message) {
		super(message);
	}

}
