package actors.mvc;

import actors.messages.AkkaMessage;
import actors.messages.EnterAkkaria;
import akka.actor.ActorRef;
import actors.mvc.views.WelcomeActorView;

/**
 * Created by zua on 20.09.16.
 */
public class WelcomeActor extends MVCActor {

    private ActorRef registerActor;
    private ActorRef loginActor;

    @Override
    public void onReceive(Object message) {
        if(AkkaMessage.REGISTER.equals(message)) {
            enterRegisterActor();
        }
        else if (AkkaMessage.LOGIN.equals(message)) {
            enterLoginActor();
        }
        else {
            super.onReceive(message);
        }
    }

    @Override
    protected void enterUI(EnterAkkaria message) {
        message.getUi().enter(getSelf(), new WelcomeActorView());
    }

    @Override
    protected void leaveAkkariaOnSuccess() {
        log.warning("Nothing to do when leaving WelcomeActor");
    }

    private void enterLoginActor() {
        if(loginActor == null) {
            loginActor = createChildActor(LoginActor.class);
        }
        loginActor.tell(new EnterAkkaria(getUi()), getSelf());
    }

    private void enterRegisterActor() {
        if(registerActor == null) {
            registerActor = createChildActor(RegisterActor.class);
        }
        registerActor.tell(new EnterAkkaria(getUi()), getSelf());
    }

}
