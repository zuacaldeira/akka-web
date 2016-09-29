package actors.exceptions;

/**
 * Created by zua on 28.09.16.
 */
public class SystemFailure extends RuntimeException {

    public SystemFailure(String message) {
        super(message);
    }
}
