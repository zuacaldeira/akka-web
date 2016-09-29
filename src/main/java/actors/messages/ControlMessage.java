package actors.messages;

/**
 * Created by zua on 29.08.16.
 */
public enum ControlMessage {
    // Welcome Area
    REGISTER,
    LOGIN,
    // User Area
    PROFILE,
    PROJECT,
    // Status
    SUCCESSFUL,
    FAILED,
    INVALID,
    CANCELLED,
    UNKNOWN, CREATE, READ, UPDATE, DELETE, SAVE;

    private final Object data;

    private ControlMessage() {
        this("");
    }

    private ControlMessage(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
