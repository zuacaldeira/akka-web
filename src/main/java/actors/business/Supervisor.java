package actors.business;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import scala.concurrent.duration.Duration;


/**
 * Created by zua on 31.08.16.
 */
public class Supervisor extends AkkaActor {

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(10, Duration.create("1 minute"),
                    t -> {
                        if (t instanceof IllegalArgumentException) {
                            return SupervisorStrategy.stop();
                        } else {
                            return SupervisorStrategy.escalate();
                        }
                    });


    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) {
        log.info("Received message " + message);
        if (message instanceof Props) {
            getSender().tell(getContext().actorOf((Props) message), getSelf());
        }
        else {
            unhandled(message);
        }
    }
}

