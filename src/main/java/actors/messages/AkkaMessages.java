package actors.messages;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zua on 29.08.16.
 */
public class AkkaMessages {
    public static final String UNKNOWN = "Unknown";
    public static final String DONE = "Done";
    public static final String REGISTER = "Register";
    public static final String LOGIN = "Login";
    public static final String VIEW = "View";
    public static final String DO_NOT_HANDLE = "Do not handle";
    public static final List<String> WELCOME_ACTOR_MESSAGES = getMessages(new String[]{REGISTER, LOGIN});
    public static final List<String> REGISTER_ACTOR_MESSAGES = getMessages(new String[]{REGISTER});
    public static final List<String> LOGIN_ACTOR_MESSAGES = getMessages(new String[]{LOGIN});

    private static List<String> getMessages(String... messages) {
        return Arrays.asList(messages);
    }
}
