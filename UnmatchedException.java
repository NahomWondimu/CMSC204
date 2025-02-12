
public class UnmatchedException extends RuntimeException{

	public UnmatchedException() {
		super(Exception_Messages.PWD_NO_MATCH_EXCEPTION_MSG);
	}
	
	public UnmatchedException(String message) {
		super(message);
	}
}
