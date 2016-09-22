package actors.business;

import actors.messages.LoginMessage;

import java.util.regex.Pattern;

/**
 * Created by zua on 20.09.16.
 */
public class LoginValidator {

    private static final String usernamePattern = "^[a-z,A-Z]{1,}\\w*@\\w*\\.\\p{L}{2,3}$";
    private static final String containsUpperCasePattern = "^.*[A-Z].*$";
    private static final String containsLowerCasePattern = "^.*[a-z].*$";
    private static final String containsDigit = "^.*\\d.*$";

    public boolean isValid(LoginMessage message) {
        return isValidUsername(message.getUsername()) && isValidPassword(message.getPassword());
    }

    protected boolean isValidPassword(String password) {
        return password.length() >= 6 && isStrong(password);
    }

    protected boolean isStrong(String password) {
        return hasUpperCase(password)
                && hasLowerCase(password)
                && hasDigit(password);
    }

    protected boolean hasDigit(String password) {
        return Pattern.compile(containsDigit).matcher(password).matches();
    }

    protected boolean hasLowerCase(String password) {
        return Pattern.compile(containsLowerCasePattern).matcher(password).matches();
    }

    protected boolean hasUpperCase(String password) {
        return Pattern.compile(containsUpperCasePattern).matcher(password).matches();
    }

    protected boolean isValidUsername(String username) {
        return Pattern.compile(usernamePattern).matcher(username).matches();
    }
}
