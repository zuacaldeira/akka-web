package actors.core;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.DiagnosticLoggingAdapter;
import akka.event.Logging;

/**
 * Created by zua on 28.08.16.
 */

public abstract class MVCUntypedActor extends UntypedActor {
    protected final DiagnosticLoggingAdapter log;

    public MVCUntypedActor() {
        log = Logging.getLogger(this);
    }

    protected ActorRef createChildActor(Class<?> actorClass) {
        return getContext().actorOf(Props.create(actorClass), actorClass.getSimpleName());
    }
}
