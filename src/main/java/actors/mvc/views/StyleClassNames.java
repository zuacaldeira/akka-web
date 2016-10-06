package actors.mvc.views;

/**
 * Created by zua on 05.09.16.
 */
public enum StyleClassNames {
    /* ACTORS */
    ACTOR("actor"),
    WELCOME_ACTOR("welcomeActor"),
    REGISTER_ACTOR("registerActor"),
    LOGIN_ACTOR("loginActor"),
    USER_ACTOR("loginActor"),
    PROJECT_ACTOR("projectActor"),
    PROJECT_CREATOR("projectCreator"),
    PROFILE_ACTOR("profileActor"),
    MESSAGE("message"),
    EMAIL("email"),
    FULLNAME("fullname"),
    PASSWORD("password"),
    PASSWORD_CONFIRMATION("passwordConfirmation"),
    TITLE("title"),
    DESCRIPTION("description"),
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
