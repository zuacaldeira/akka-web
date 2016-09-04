package actors.core.exceptions;

/**
 * Created by zua on 04.09.16.
 */
public class UnexpectedException extends RuntimeException {

    /**
     * Wrapps a non business exception into an unexpected exception.
     *
     * @param message A detailed message to explain the exception
     * @param ex The error or exception we're wrapping
     */
    public UnexpectedException(String message, Throwable ex) {
        super(message, ex);
    }
}
