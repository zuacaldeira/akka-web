package actors.mvc;

import actors.business.LoginActor;
import actors.business.RegisterActor;
import actors.messages.AkkaMessage;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;

/**
 * Created by zua on 20.09.16.
 */
public class WelcomeMVCActor extends MVCActor {

    private ActorRef registerActor;
    private ActorRef loginActor;

    @Override
    public void onReceive(Object message) {
        if(message instanceof AkkaMessage) {
            processLiteral((AkkaMessage) message);
        }
        else if (message instanceof RegisterMessage) {
            register((RegisterMessage) message);
        }
        else if (message instanceof LoginMessage) {
            login((LoginMessage) message);
        }
        else {
            unhandled(message);
        }
    }

    private void login(LoginMessage message) {
        loginActor.forward(message, getContext());
        loginActor.tell(message, getSelf());
    }

    private void register(RegisterMessage message) {
        registerActor.forward(message, getContext());
    }

    private void processLiteral(AkkaMessage message) {
        switch(message) {
            case REGISTER:
                initRegisterActor();
                break;
            case LOGIN:
                initLoginActor();
                break;
            default:
                unhandled(message);
        }

    }

    private void initLoginActor() {
        loginActor = createChildActor(LoginActor.class);
    }

    private void initRegisterActor() {
        registerActor = createChildActor(RegisterActor.class);
    }

}
