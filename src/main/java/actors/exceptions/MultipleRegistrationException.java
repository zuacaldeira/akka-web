package actors.exceptions;

/**
 * Created by zua on 04.09.16.
 */
public class MultipleRegistrationException extends BusinessViolation {
    /**
     * Creates a new exception to mark attempts to register a user more than once.
     * @param username The already registered user
     */
    public MultipleRegistrationException(String username) {
        super("Found multiple registration for user: " + username);
    }
}
