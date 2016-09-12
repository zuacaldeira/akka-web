package actors.core;

/**
 * Created by zua on 01.09.16.
 */
public enum ActorSystems {
    ACTOR_SYSTEM("ActorSystem_View"),
    ACTOR_SYSTEM_TEST("ActorSystem_Test");

    private final String alias;

    private ActorSystems(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
