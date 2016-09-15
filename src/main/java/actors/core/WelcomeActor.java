package actors.core;

import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import views.actors.LoginActorView;
import views.actors.RegisterActorView;


/**
 * Created by zua on 28.08.16.
 */
public class WelcomeActor extends Supervisor {


    @Override
    public void onReceive(Object message)  {
        if(AkkaMessages.REGISTER.equals(message.toString())) {
            acknowledgeSender(AkkaMessages.DONE);
            //goToRegisterView();
        } else if(AkkaMessages.LOGIN.equals(message.toString())) {
            acknowledgeSender(AkkaMessages.DONE);
            goToLoginView();
        }
        else {
            super.onReceive(message);
        }
    }

    private void goToRegisterView() {
        if(getView() != null) {
            log.info("Changing view to register actor view...");
            getView().getUI().setContent(new RegisterActorView());
        }
    }

    private void goToLoginView() {
        if(getView() != null) {
            log.info("Changing view to register actor view...");
            getView().getUI().setContent(new LoginActorView());
        }
    }

    private void acknowledgeSender(String reply) {
        if(!getSender().equals(ActorRef.noSender())) {
            log.info("Replying to caller " + getSender().path().name());
            getSender().tell(reply, getSelf());
        }
    }

}
