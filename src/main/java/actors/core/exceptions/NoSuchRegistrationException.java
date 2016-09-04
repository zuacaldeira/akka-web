package actors.core.exceptions;

/**
 * Created by zua on 04.09.16.
 */
public class NoSuchRegistrationException extends IllegalLoginException {
    public NoSuchRegistrationException(String username) {
        super("Registration not found for user " + username);
    }
}
