package actors.exceptions;

/**
 * Created by zua on 28.09.16.
 */
public class BusinessViolation extends RuntimeException {
    public BusinessViolation(String message) {
        super(message);
    }
}
