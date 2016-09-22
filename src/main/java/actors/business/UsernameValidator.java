package actors.business;

import java.util.regex.Pattern;

/**
 * Created by zua on 21.09.16.
 */
public class UsernameValidator {
    private static final String usernamePattern = "^[a-z,A-Z]{1,}\\w*@\\w*\\.\\p{L}{2,3}$";

    public boolean isValid(String username) {
        return Pattern.compile(usernamePattern).matcher(username).matches();
    }
}
