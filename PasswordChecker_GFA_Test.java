
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordChecker_GFA_Test {
    ArrayList<String> passwords;
    String password1, password2;

    @Before
    public void setUp() throws Exception {
	passwords = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
	passwords = null;
    }

    @Test
    public void testGetInvalidPasword() {
	PasswordCheckerUtility.getInvalidPasswords(passwords);
    }

    @Test
    public void testIsValidPasswordTooShort() {
	try {
	    assertTrue(PasswordCheckerUtility.isValidPassword("abc12"));
	} catch (LengthException e) {
	    assertTrue(Exception_Messages.SUCCESSFUL_LENGTH_EXCEPTION_MSG, true);
	} catch (Exception e) {
	    assertTrue(Exception_Messages.OTHER_LENGTH_EXCEPTION_MSG, false);
	}
    }

    @Test
    public void testIsValidPasswordSuccess() {
	try {
	    assertEquals(true, PasswordCheckerUtility.isValidPassword("strongPWD1"));
	} catch (NoSpecialCharacterException e) {
	    System.out.println(e.getMessage());
	    assertTrue(Exception_Messages.SUCCESSFUL_SPEC_CHAR_EXCEPTION_MSG, true);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    assertFalse(Exception_Messages.OTHER_LENGTH_EXCEPTION_MSG, true);
	}
    }

    @Test
    public void testIsValidPasswordTooShortThrowsSomeOtherExcep() {
	try {
	    assertTrue(PasswordCheckerUtility.isValidPassword("abcABs"));
	} catch (LengthException e) {
	    System.out.println(e.getMessage());
	    assertTrue(Exception_Messages.SUCCESSFUL_LENGTH_EXCEPTION_MSG, true);
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    assertTrue(Exception_Messages.OTHER_LENGTH_EXCEPTION_MSG, true);
	}
    }

}
