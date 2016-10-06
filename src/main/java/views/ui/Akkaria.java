package views.ui;

import actors.mvc.MVCActor;
import akka.actor.ActorRef;

/**
 * Created by zua on 24.09.16.
 */
public interface Akkaria {

    void enter(ActorRef actor, Class<? extends MVCActor> actorClass);
    void leave(ActorRef actor);
}
