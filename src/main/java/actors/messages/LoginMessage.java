package actors.messages;

/**
 * Created by zua on 28.08.16.
 */
public class LoginMessage {
    private final String username;
    private final String password;

    /**
     * Creates a login message to be passed to a login actor.
     *
     * @param username A username
     * @param password A password
     */
    public LoginMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "LoginMessage{" +
                "username='" + username + '\'' +
                ", password='" + "???????" + '\'' +
                '}';
    }
}
