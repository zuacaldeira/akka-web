package actors.mvc;

import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import akka.actor.ActorRef;
import graphs.entities.nodes.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 20.09.16.
 */
public class WelcomeActor extends MVCActor {

    private ActorRef registerActor;
    private ActorRef loginActor;

    protected WelcomeActor(AkkaUI ui, User user) {
        super(ui, user);
    }

    @Override
    public void onReceive(Object message) {
        if(ControlMessage.REGISTER.equals(message)) {
            enterRegisterActor();
        }
        else if (ControlMessage.LOGIN.equals(message)) {
            enterLoginActor();
        }
        else {
            super.onReceive(message);
        }
    }

    private void enterLoginActor() {
        if(loginActor == null) {
            loginActor = createChildActor(LoginActor.class, getUi(), getUser());
        }
        loginActor.tell(new EnterAkkaria(), getSelf());
        leaveAkkariaOnSuccess(ControlMessage.SUCCESS);
    }

    private void enterRegisterActor() {
        if(registerActor == null) {
            registerActor = createChildActor(RegisterActor.class, getUi(), getUser());
        }
        registerActor.tell(new EnterAkkaria(), getSelf());
        leaveAkkariaOnSuccess(ControlMessage.SUCCESS);
    }

}
