package actors.exceptions;

/**
 * Created by zua on 03.09.16.
 */
public class InvalidRegistrationException extends IllegalArgumentException {
    /**
     * Creates a new exception to report an illegal registration attempt.
     *
     * @param message   The exception message
     */
    public InvalidRegistrationException(String message) {
        super(message);
    }
}
