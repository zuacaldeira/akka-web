package views.factories;

import akka.actor.ActorRef;

import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class ActorsViewFactory {

    public static ActorView getActorView(ActorRef actor, List<String> messages) {
        return new InactiveActorView(actor, messages);
    }
}
