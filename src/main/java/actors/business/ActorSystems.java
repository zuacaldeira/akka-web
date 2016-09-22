package actors.business;

/**
 * Created by zua on 01.09.16.
 */
public enum ActorSystems {
    ACTOR_SYSTEM("akkaria"),
    ACTOR_SYSTEM_TEST("test");

    private final String alias;

    private ActorSystems(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
