package actors.core;

/**
 * Created by zua on 03.09.16.
 */
public class IllegalLoginException extends IllegalArgumentException {
    public IllegalLoginException(String username) {
        super("Invalid user " + username);
    }
}
