package actors.business;

import akka.actor.UntypedActor;
import akka.event.DiagnosticLoggingAdapter;
import akka.event.Logging;

/**
 * Created by zua on 21.09.16.
 */
public abstract class AkkaActor extends UntypedActor {
    protected final DiagnosticLoggingAdapter log;

    public AkkaActor() {
        log = Logging.getLogger(this);
    }
}
