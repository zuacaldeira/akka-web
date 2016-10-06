package actors.business;

/**
 * Created by zua on 06/10/16.
 */
public class InvalidUsernameException extends AkkarianException {
    public InvalidUsernameException(String username) {
        super(username);
    }
}
