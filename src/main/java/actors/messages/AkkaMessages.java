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
    public static List<String> getMessages(String... messages) {
        return Arrays.asList(messages);
    }
}
