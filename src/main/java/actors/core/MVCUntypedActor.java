package actors.core;

import actors.core.exceptions.UnexpectedException;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.DiagnosticLoggingAdapter;
import akka.event.Logging;
import graphs.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;
import views.actors.ActorView;

/**
 * Created by zua on 28.08.16.
 */

public abstract class MVCUntypedActor extends UntypedActor {
    protected final DiagnosticLoggingAdapter log;
    private ActorView view;

    /**
     * Default constructor initializes the logging adapter.
     */
    public MVCUntypedActor() {
        log = Logging.getLogger(this);
    }

    @Override
    public void onReceive(Object message) {
        log.info("Received message " + message);
        if (message instanceof ActorView) {
            view = (ActorView) message;
        }
        else {
            unhandled(message);
        }
    }

    protected ActorRef createChildActor(Class<?> actorClass) {
        return getContext().actorOf(Props.create(actorClass), actorClass.getSimpleName());
    }

    protected Session getNeo4jSession() {
        try {
            return Neo4jSessionFactory.getInstance().getNeo4jSession();
        } catch (Exception e) {
            throw new UnexpectedException("Problems creating a Neo4j Session", e);
        }
    }

    public ActorView getView() {
        return view;
    }
}
