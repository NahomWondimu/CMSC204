
public class NoSpecialCharacterException extends RuntimeException {
	public NoSpecialCharacterException() {
		super(Exception_Messages.SPECIAL_CHAR_EXCEPTION_MSG);
	}
	
	public NoSpecialCharacterException(String message) {
		super(message);
	}

}
