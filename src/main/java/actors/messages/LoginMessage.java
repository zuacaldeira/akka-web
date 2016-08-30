package actors.messages;

/**
 * Created by zua on 28.08.16.
 */
public class LoginMessage {
    private final String username;
    private final String password;

    public LoginMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}
