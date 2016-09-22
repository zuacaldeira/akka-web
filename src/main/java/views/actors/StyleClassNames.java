package views.actors;

/**
 * Created by zua on 05.09.16.
 */
public enum StyleClassNames {
    WELCOME_ACTOR("welcomeActor"),
    REGISTER_ACTOR("registerActor"),
    LOGIN_ACTOR("loginActor"),
    MESSAGE("message"),
    REGISTER_MESSAGE("registerMessage"),
    EMAIL("email"),
    FULLNAME("fullname"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("passwordConfirmation"),
    ACTOR_NAME("actorName"),
    REGISTER_FORM("registerForm"),
    ENABLED("enabled"),
    CANCEL("Camcel");

    private final String style;

    private StyleClassNames(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
