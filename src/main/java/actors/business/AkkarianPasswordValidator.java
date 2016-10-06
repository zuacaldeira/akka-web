package actors.business;

import java.util.regex.Pattern;

/**
 * Created by zua on 21.09.16.
 */
public class AkkarianPasswordValidator extends AkkarianValidator<String> {
    private static final String CONTAINS_UPPER_CASE_PATTERN = "^.*[A-Z].*$";
    private static final String CONTAINS_LOWER_CASE_PATTERN = "^.*[a-z].*$";
    private static final String CONTAINS_DIGIT = "^.*\\d.*$";

    public boolean isValid(String password) {
        if(password == null) {
            throw new InvalidPasswordException("Null password");
        }
        boolean hasUpperCase = Pattern.compile(CONTAINS_UPPER_CASE_PATTERN).matcher(password).matches();
        if(!hasUpperCase) {
            throw new InvalidPasswordException("Upper case character missing");
        }
        boolean hasLowerCase = Pattern.compile(CONTAINS_LOWER_CASE_PATTERN).matcher(password).matches();
        if(!hasLowerCase) {
            throw new InvalidPasswordException("Lower case character missing");
        }
        boolean hasDigit = Pattern.compile(CONTAINS_DIGIT).matcher(password).matches();
        if(!hasDigit) {
            throw new InvalidPasswordException("Digit missing");
        }
        return hasUpperCase && hasLowerCase && hasDigit;
    }
}
