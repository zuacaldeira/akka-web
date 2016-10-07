package actors.business;

import actors.exceptions.BusinessViolation;
import actors.exceptions.SystemFailure;
import actors.messages.world.LeaveAkkaria;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import scala.concurrent.duration.Duration;


/**
 * Created by zua on 31.08.16.
 */
public class Supervisor extends AkkaActor {

    private SupervisorStrategy strategy =
        new OneForOneStrategy(
            10,
            Duration.create("1 minute"),
            t -> {
                if (t instanceof BusinessViolation) {
                    Supervisor.this.getSender().tell(new LeaveAkkaria(null, (BusinessViolation)t), Supervisor.this.getSelf());
                    return SupervisorStrategy.stop();
                }
                if (t instanceof SystemFailure) {
                    Supervisor.this.getSender().tell(new LeaveAkkaria(null, (SystemFailure)t), Supervisor.this.getSelf());
                    return SupervisorStrategy.stop();
                }
                else {
                    return SupervisorStrategy.escalate();
                }
            }
        );


    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) {
        log.info("As Supervisor: Received message " + message);
        if (message instanceof Props) {
            getSender().tell(getContext().actorOf((Props) message), getSelf());
        }
        else {
            unhandled(message);
        }
    }

    protected final ActorRef createChildActor(Class<?> actorClass, Object... args) {
        return getContext().actorOf(Props.create(actorClass, args), actorClass.getSimpleName());
    }




}

