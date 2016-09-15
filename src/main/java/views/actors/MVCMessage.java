package views.actors;

/**
 * Created by zua on 12.09.16.
 */
public class MVCMessage<T> {
    private final ActorView actorView;
    private final T message;

    public MVCMessage(ActorView actorView, T message) {
        this.actorView = actorView;
        this.message = message;
    }

    public ActorView getActorView() {
        return actorView;
    }

    public T getMessage() {
        return message;
    }
}
