package actors.mvc;

import actors.business.Supervisor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Created by zua on 28.08.16.
 */

public abstract class MVCActor extends Supervisor {
    protected ActorRef createChildActor(Class<?> actorClass) {
        return getContext().actorOf(Props.create(actorClass), actorClass.getSimpleName());
    }

}
