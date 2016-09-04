package actors.core.exceptions;

/**
 * Created by zua on 03.09.16.
 */
public class IllegalLoginException extends IllegalArgumentException {
    /**
     * Creates a new exception to report an illegal login attempt.
     *
     * @param message   The exception message
     */
    public IllegalLoginException(String message) {
        super(message);
    }
}
