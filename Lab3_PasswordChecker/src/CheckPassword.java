import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out) {
        boolean containsUpperCaseLetter = false;
        boolean containsLowerCaseLetter = false;
        boolean isValidDigit = false;
        if (passwordCandidate.length() >= 8) {
            if (containsUpperCaseLetter(passwordCandidate)) {
                containsUpperCaseLetter = true;
            }
            if (containsLowerCaseLetter(passwordCandidate)) {
                containsLowerCaseLetter = true;
            }
            if (isValidDigit(passwordCandidate)) {
                isValidDigit = true;
            }
            out.println("8 char: valid contains upper case: "
                    + containsUpperCaseLetter + " contains lower case: "
                    + containsLowerCaseLetter + " contains a digit: "
                    + isValidDigit);
        } else {
            out.println("Not 8 CHARACTERS");
        }
    }

    /**
     * private static boolean isSpecialCharcter (String str) { String
     * specialCharcters = "!@#$%^&*()_-+={}[]:;,.?"; char[] strChar = new
     * char[str.length()]; for (int i = 0; i < str.length(); i++) { strChar[i] =
     * str.charAt(i); } boolean value = false; int j = 0; while (j <
     * str.length() && value == false) { if
     * (Character.isUpperCase(str.charAt(j))) { value = true; } j++; } return
     * value; }
     */

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        boolean value = false;
        int i = 0;
        while (i < str.length() && value == false) {
            if (Character.isUpperCase(str.charAt(i))) {
                value = true;
            }
            i++;
        }
        return value;
    }

    private static boolean containsLowerCaseLetter(String str) {
        boolean value = false;
        int i = 0;
        while (i < str.length() && value == false) {
            if (Character.isLowerCase(str.charAt(i))) {
                value = true;
            }
            i++;
        }
        return value;
    }

    private static boolean isValidDigit(String str) {
        boolean value = false;
        int i = 0;
        while (i < str.length() && value == false) {
            if (Character.isDigit(str.charAt(i))) {
                value = true;
            }
            i++;
        }
        return value;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        out.println("Enter a valid password: ");
        String password = in.nextLine();
        checkPassword(password, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
