package actors.messages;

/**
 * Created by zua on 28.08.16.
 */
public class RegisterMessage {
    private final String email;
    private final String password;
    private String fullname;

    public RegisterMessage(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }
}
