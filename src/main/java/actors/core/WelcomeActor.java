package actors.core;

import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeActor extends MVCUntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof RegisterMessage) {
            ActorRef ref = createChildActor(RegisterActor.class);
            ref.forward(message, getContext());
            getSender().tell(AkkaMessages.DONE, getSelf());
            log.info("Sent register message");
        } else if (message instanceof LoginMessage) {
            ActorRef ref = createChildActor(LoginActor.class);
            ref.forward(message, getContext());
            getSender().tell(AkkaMessages.DONE, getSelf());
            log.info("Sent login message");
        }
        else {
            unhandled(message);
        }
    }


}
