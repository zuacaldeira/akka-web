package actors.core;

/**
 * Created by zua on 01.09.16.
 */
public enum ActorSystemsNames {
    ACTOR_SYSTEM_MODEL("ActorSystem_Model"),
    ACTOR_SYSTEM_VIEW("ActorSystem_View"),
    ACTOR_SYSTEM_CONTROLLER("ActorSystem_Controller");

    private final String alias;

    private ActorSystemsNames(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
