package actors.business;

import java.util.regex.Pattern;

/**
 * Created by zua on 21.09.16.
 */
public class PasswordValidator {
    private static final String containsUpperCasePattern = "^.*[A-Z].*$";
    private static final String containsLowerCasePattern = "^.*[a-z].*$";
    private static final String containsDigit = "^.*\\d.*$";

    public boolean isValid(String username) {
        return username != null && Pattern.compile(containsUpperCasePattern).matcher(username).matches()
                && Pattern.compile(containsLowerCasePattern).matcher(username).matches()
                && Pattern.compile(containsDigit).matcher(username).matches();
    }
}
