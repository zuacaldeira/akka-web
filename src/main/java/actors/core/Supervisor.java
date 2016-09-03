package actors.core;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import scala.concurrent.duration.Duration;


/**
 * Created by zua on 31.08.16.
 */
public class Supervisor extends MVCUntypedActor {

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
    public void onReceive(Object o) {
        if (o instanceof Props) {
            getSender().tell(getContext().actorOf((Props) o), getSelf());
        } else {
            unhandled(o);
        }
    }
}

