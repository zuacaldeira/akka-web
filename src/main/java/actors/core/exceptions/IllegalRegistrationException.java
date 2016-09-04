package actors.core.exceptions;

/**
 * Created by zua on 03.09.16.
 */
public class IllegalRegistrationException extends IllegalArgumentException {
    /**
     * Creates a new exception to report an illegal registration attempt.
     *
     * @param message   The exception message
     */
    public IllegalRegistrationException(String message) {
        super(message);
    }
}
