package actors.messages;

/**
 * Created by zua on 28.08.16.
 */
public class RegisterMessage {
    private final String email;
    private final String password;
    private final String fullname;

    /**
     * Creates a register message to be given to an register actor
     *
     * @param email
     * @param password
     * @param fullname
     */
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


    @Override
    public String toString() {
        return "RegisterMessage{" +
                "email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
