package actors.messages;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zua on 29.08.16.
 */
public class AkkaMessages {
    public static final String UNKNOWN = "Unknown";
    public static final String CANCEL = "Cancel";
    public static final String DONE = "Done";
    public static final String REGISTER = "Register";
    public static final String LOGIN = "Login";
    public static final String VIEW = "View";
    public static final String DO_NOT_HANDLE = "Do not handle";
    public static final String SEND = "Send";
    public static final String ERROR = "Error";

    private AkkaMessages() {}

    private static List<String> getMessages(String... messages) {
        return Arrays.asList(messages);
    }

    public static List<String> getWelcomeActorMessages() {
        return getMessages(REGISTER, LOGIN);
    }

    public static List<String> getRegisterActorMessages() {
        return getMessages(REGISTER);
    }

    public static List<String> getLoginActorMessages() {
        return getMessages(LOGIN);
    }

}
