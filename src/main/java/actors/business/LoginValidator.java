package actors.business;

import actors.messages.LoginMessage;

import java.util.regex.Pattern;

/**
 * Created by zua on 20.09.16.
 */
public class LoginValidator implements AkkarianValidator<LoginMessage> {

    private static final String USERNAME_PATTERN = "^[a-z,A-Z]{1,}\\w*@\\w*\\.\\p{L}{2,3}$";
    private static final String CONTAINS_UPPER_CASE_PATTERN = "^.*[A-Z].*$";
    private static final String CONTAINS_LOWER_CASE_PATTERN = "^.*[a-z].*$";
    private static final String CONTAINS_DIGIT = "^.*\\d.*$";

    @Override
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
        return Pattern.compile(CONTAINS_DIGIT).matcher(password).matches();
    }

    protected boolean hasLowerCase(String password) {
        return Pattern.compile(CONTAINS_LOWER_CASE_PATTERN).matcher(password).matches();
    }

    protected boolean hasUpperCase(String password) {
        return Pattern.compile(CONTAINS_UPPER_CASE_PATTERN).matcher(password).matches();
    }

    protected boolean isValidUsername(String username) {
        return Pattern.compile(USERNAME_PATTERN).matcher(username).matches();
    }
}
