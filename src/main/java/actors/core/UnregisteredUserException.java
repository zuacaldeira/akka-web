package actors.core;

import actors.core.exceptions.IllegalLoginException;

/**
 * Created by zua on 04.09.16.
 */
public class UnregisteredUserException extends IllegalLoginException {

    /**
     * Creates a new exception reporting an unregistered user
     * @param username  The username
     */
    public UnregisteredUserException(String username) {
        super("Unregistered user: " + normalyzeUsername(username));
    }

    private static String normalyzeUsername(String username) {
        if(username == null) {
            return "null";
        }
        else return username.trim();
    }
}
