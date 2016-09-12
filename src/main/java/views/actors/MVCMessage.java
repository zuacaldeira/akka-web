package views.actors;

/**
 * Created by zua on 12.09.16.
 */
public class MVCMessage {
    private final ActorView actorView;
    private final Object message;

    public MVCMessage(ActorView actorView, Object message) {
        this.actorView = actorView;
        this.message = message;
    }

    public ActorView getActorView() {
        return actorView;
    }

    public Object getMessage() {
        return message;
    }
}
