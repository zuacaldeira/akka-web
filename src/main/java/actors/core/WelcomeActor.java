package actors.core;

import akka.actor.ActorRef;
import akka.actor.Props;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeActor  extends MVCUntypedActor {
    public static final akka.actor.Props PROPS = Props.create(WelcomeActor.class);
    public static final String NAME = WelcomeActor.class.getSimpleName();
    public static final List<String> MESSAGES = Arrays.asList(new String[]{AkkaMessages.REGISTER, AkkaMessages.LOGIN});


    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof RegisterMessage) {
            ActorRef ref = getContext().actorOf(RegisterActor.PROPS, RegisterActor.NAME);
            ref.tell(message, getSelf());
            log.info("Sent register message");
            if(! sender().path().equals(getSelf().path())) {
                getSender().tell(AkkaMessages.DONE, getSelf());
            }
        }

        else if(message instanceof LoginMessage) {
            ActorRef ref = getContext().actorOf(LoginActor.PROPS, LoginActor.NAME);
            ref.tell(message, getSelf());
            log.info("Sent login message");
            if(! sender().path().equals(getSelf().path())) {
                getSender().tell(AkkaMessages.DONE, getSelf());
            }
        }
    }

}
