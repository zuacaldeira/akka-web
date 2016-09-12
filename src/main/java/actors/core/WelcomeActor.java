package actors.core;

import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import views.actors.MVCMessage;
import views.actors.WelcomeActorView;


/**
 * Created by zua on 28.08.16.
 */
public class WelcomeActor extends Supervisor {


    @Override
    public void onReceive(Object message) {

        if(message instanceof MVCMessage) {
            log.info("Received MVCMessage...");
            MVCMessage mvcMessage = (MVCMessage) message;
            if(mvcMessage.getActorView() instanceof WelcomeActorView) {
                log.info("MVCMessage contains a Welcome Actor View");
                setView(mvcMessage.getActorView());
            }

            if(mvcMessage.getMessage() instanceof String) {
                log.info("MVCMessage contains a String message");
                String actualMessage = (String) mvcMessage.getMessage();
                processMessage(actualMessage);
            }
        }
        else {
            unhandled(message);
        }
    }

    private void processMessage(String message) {
        if (message.equals(AkkaMessages.REGISTER)) {
            log.info("MVCMessage contains a REGISTER message");
            if(!getSender().equals(ActorRef.noSender())) {
                getSender().tell(AkkaMessages.DONE, getSelf());
            }
            if(getView() != null) {
                log.info("Changing view to register actor view...");
                getView().setUIContentToRegisterActorView();
            }
        } else if (message.equals(AkkaMessages.LOGIN)) {
            log.info("MVCMessage contains a LOGIN message");
            if(getView() != null && getView().getUI() != null) {
                log.info("Changing view to login actor view...");
                getView().setUIContentToLoginActorView();
            }
            if(getSender() != ActorRef.noSender()) {
                getSender().tell(AkkaMessages.DONE, getSelf());
            }
        }
        else {
            unhandled(message);
        }
    }

    @Override
    public WelcomeActorView getView() {
        return (WelcomeActorView) super.getView();
    }
}
