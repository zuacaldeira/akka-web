package actors.mvc.views;

/**
 * Created by zua on 05.09.16.
 */
public enum StyleClassNames {
    ACTOR("actor"),
    WELCOME_ACTOR("welcomeActor"),
    REGISTER_ACTOR("registerActor"),
    LOGIN_ACTOR("loginActor"),
    USER_ACTOR("loginActor"),
    MESSAGE("message"),
    EMAIL("email"),
    FULLNAME("fullname"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("passwordConfirmation"),
    ACTOR_NAME("actorName"),
    ENABLED("enabled");

    private final String style;

    private StyleClassNames(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
