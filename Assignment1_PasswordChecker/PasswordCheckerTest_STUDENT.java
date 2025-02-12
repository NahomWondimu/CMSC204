
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {

	private ArrayList<String> invalidPwords;
	
	@Before
	public void setUp() throws Exception {
		invalidPwords = new ArrayList<>(Arrays.asList(
	            "abc",            // Too short
	            "abcdef",         // No uppercase, no digit, no special char
	            "ABCDEF",         // No lowercase, no digit, no special char
	            "validP@ss",         // No uppercase, no lowercase, no special char
	            "password",       // No uppercase, no digit, no special char
	            "P@ssword",       // No digit
	            "P@ss1",          // Too short
	            "PPPass1!",       // Repeating sequence
	            "validP@ss1"      // Valid password
	    ));
	}

	@After
	public void tearDown() throws Exception {
		invalidPwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			PasswordCheckerUtility.isValidLength("cat");
			fail("Expected LengthException");
		}catch (LengthException e) {
			assertEquals(Exception_Messages.INVALID_LENGTH_EXCEPTION_MSG, e.getMessage());
			System.out.println(Exception_Messages.SUCCESSFUL_LENGTH_EXCEPTION_MSG);
		}catch (Exception e) {
			fail(Exception_Messages.OTHER_LENGTH_EXCEPTION_MSG);
		}
		
		try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcdef1!")); // Valid case
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
    public void testIsValidPasswordNoUpperAlpha() {
        try {
            PasswordCheckerUtility.hasUpperAlpha("abcdef1!");
            fail("Expected NoUpperAlphaException");
        } catch (NoUpperAlphaException e) {
            assertEquals(Exception_Messages.UPPER_ALPHA_EXCEPTION_MSG, e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.hasUpperAlpha("Abcdef1!")); // Valid case
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        try {
            PasswordCheckerUtility.hasLowerAlpha("ABCDEFG1!");
            fail("Expected NoLowerAlphaException");
        } catch (NoLowerAlphaException e) {
            assertEquals(Exception_Messages.LOWER_ALPHA_EXCEPTION_MSG, e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.hasLowerAlpha("Abcdef1!")); // Valid case
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testIsValidPasswordNoDigit() {
        try {
            PasswordCheckerUtility.hasDigit("Abcdef!");
            fail("Expected NoDigitException");
        } catch (NoDigitException e) {
            assertEquals(Exception_Messages.DIGIT_EXCEPTION_MSG, e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.hasDigit("Abcdef1!")); // Valid case
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testIsValidPasswordNoSpecialChar() {
        try {
            PasswordCheckerUtility.hasSpecialChar("Abcdef1");
            fail("Expected NoSpecialCharacterException");
        } catch (NoSpecialCharacterException e) {
            assertEquals(Exception_Messages.SPECIAL_CHAR_EXCEPTION_MSG, e.getMessage());
            System.out.println(Exception_Messages.SUCCESSFUL_SPEC_CHAR_EXCEPTION_MSG);
        }

        try {
            assertTrue(PasswordCheckerUtility.hasSpecialChar("Abcdef1!")); // Valid case
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testIsValidPasswordInvalidSequence() {
        try {
            PasswordCheckerUtility.NoSameCharInSequence("AAAabc1!");
            fail("Expected InvalidSequenceException");
        } catch (InvalidSequenceException e) {
            assertEquals(Exception_Messages.INVALID_SEQUENCE_EXCEPTION_MSG, e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.NoSameCharInSequence("Abcdef1!")); // Valid case
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testIsWeakPassword() {
        try {
            PasswordCheckerUtility.isWeakPassword("Abc1!23");
            fail("Expected WeakPasswordException");
        } catch (WeakPasswordException e) {
            assertEquals(Exception_Messages.WEAK_PWD_MSG, e.getMessage());
        }

        try {
            assertFalse(PasswordCheckerUtility.isWeakPassword("Abcdef1!2345")); // Valid case
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("ValidP@ss1"));
        } catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }

    @Test
    public void testInvalidPasswords() {
        ArrayList<String> results = PasswordCheckerUtility.getInvalidPasswords(invalidPwords);
        assertEquals(8, results.size());
        assertTrue(results.get(0).contains(Exception_Messages.INVALID_LENGTH_EXCEPTION_MSG));
        assertTrue(results.get(1).contains(Exception_Messages.UPPER_ALPHA_EXCEPTION_MSG));
        assertTrue(results.get(2).contains(Exception_Messages.LOWER_ALPHA_EXCEPTION_MSG));
        assertTrue(results.get(3).contains(Exception_Messages.DIGIT_EXCEPTION_MSG));
        assertTrue(results.get(4).contains(Exception_Messages.UPPER_ALPHA_EXCEPTION_MSG));
        assertTrue(results.get(5).contains(Exception_Messages.DIGIT_EXCEPTION_MSG));
        assertTrue(results.get(6).contains(Exception_Messages.INVALID_LENGTH_EXCEPTION_MSG));
        assertTrue(results.get(7).contains(Exception_Messages.REPEATING_CHARS_EXCEPTION_MSG));
    }
}
