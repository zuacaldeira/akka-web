package utils;

/**
 * Created by zua on 04.09.16.
 */
public class NotYetImplementedException extends RuntimeException  {
    /**
     * Creates an exception to mark missing implementation.
     *
     * @param message
     */
    public NotYetImplementedException(String message) {
        super(message);
    }
}
