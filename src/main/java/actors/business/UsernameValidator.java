package actors.business;

import java.util.regex.Pattern;

/**
 * Created by zua on 21.09.16.
 */
public class UsernameValidator extends AkkarianValidator<String> {
    private static final String USERNAME_PATTERN = "^[a-z,A-Z]{1,}\\w*@\\w*\\.\\p{L}{2,3}$";

    public boolean isValid(String username) {
        boolean isValid = Pattern.compile(USERNAME_PATTERN).matcher(username).matches();
        if(! isValid) {
            throw new InvalidUsernameException(username);
        }
        return isValid;
    }

}
