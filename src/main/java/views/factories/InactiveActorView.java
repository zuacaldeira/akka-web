package views.factories;

import akka.actor.ActorRef;

import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class InactiveActorView extends ActorView {
    public InactiveActorView(ActorRef actor, List<String> messages) {
        super(actor, ActorStatus.INACTIVE, messages);
    }
}
