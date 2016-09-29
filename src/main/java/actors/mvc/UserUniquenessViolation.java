package actors.mvc;

import actors.exceptions.BusinessViolation;

/**
 * Created by zua on 28.09.16.
 */
public class UserUniquenessViolation extends BusinessViolation {
    public UserUniquenessViolation(String username) {
        super("User already exists for username: " + username);
    }
}
