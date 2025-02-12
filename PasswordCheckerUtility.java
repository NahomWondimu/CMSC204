import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility {
    public PasswordCheckerUtility() {
        // Default constructor
    }

    public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
        if (!password.equals(passwordConfirm)) {
            throw new UnmatchedException();
        }
    }

    public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();
        for (String password : passwords) {
            try {
                isValidPassword(password);
            } catch (Exception e) {
                invalidPasswords.add(password + " -> " + e.getMessage());
            }
        }
        return invalidPasswords;
    }

    public static boolean hasBetweenSixAndNineChars(String password) {
        return password.length() >= 6 && password.length() <= 9;
    }

    public static boolean hasDigit(String password) throws NoDigitException {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        throw new NoDigitException();
    }

    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        throw new NoLowerAlphaException();
    }

    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        throw new NoUpperAlphaException();
    }

    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
    	Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(password);
        
        if (!matcher.matches()) {
            return true;
        }
        
        throw new NoSpecialCharacterException();
    }

    public static boolean isValidLength(String password) throws LengthException {
        if (password.length() >= 6) {
            return true;
        }
        throw new LengthException();
    }

    public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                throw new InvalidSequenceException();
            }
        }
        return true;
    }

    public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        isValidLength(password);
        hasUpperAlpha(password);
        hasLowerAlpha(password);
        hasDigit(password);
        hasSpecialChar(password);
        NoSameCharInSequence(password);
        return true;
    }

    public static boolean isWeakPassword(String password) throws WeakPasswordException {
        if (isValidPassword(password) && hasBetweenSixAndNineChars(password)) {
            throw new WeakPasswordException();
        }
        return false;
    }
}
