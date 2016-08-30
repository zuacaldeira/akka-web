package actors.core;

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
}
