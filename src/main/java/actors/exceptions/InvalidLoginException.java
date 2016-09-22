package actors.exceptions;

/**
 * Created by zua on 03.09.16.
 */
public class InvalidLoginException extends IllegalArgumentException {
    /**
     * Creates a new exception to report an illegal login attempt.
     *
     * @param message   The exception message
     */
    public InvalidLoginException(String message) {
        super(message);
    }
}
