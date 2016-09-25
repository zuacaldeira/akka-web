package actors.mvc;

import actors.business.Supervisor;
import actors.messages.AkkaMessage;
import actors.messages.EnterAkkaria;
import actors.messages.LeaveAkkaria;
import akka.actor.ActorRef;
import akka.actor.Props;
import views.ui.AkkaUI;

/**
 * Created by zua on 28.08.16.
 */

public abstract class MVCActor extends Supervisor {


    private AkkaUI ui;

    public AkkaUI getUi() {
        return ui;
    }

    @Override
    public void onReceive(Object message) {
        if(message instanceof EnterAkkaria) {
            enterAkkaria((EnterAkkaria) message);
        }
        else if(message instanceof LeaveAkkaria) {
            leaveAkkaria((LeaveAkkaria) message);
        }
        else {
            unhandled(message);
        }
    }

    private void enterAkkaria(EnterAkkaria message) {
        log.info("Entering Akkaria");
        this.ui = message.getUi();
        enterUI(message);
    }


    private void leaveAkkaria(LeaveAkkaria message) {
        if(message.getStatus().equals(AkkaMessage.SUCCESSFUL)) {
            log.info("Leaving Akkaria:" + AkkaMessage.SUCCESSFUL.name());
            leaveAkkariaOnSuccess();
            getSender().tell(AkkaMessage.SUCCESSFUL, getSelf());
        }
        else if(message.getStatus().equals(AkkaMessage.INVALID)) {
            log.info("Leaving Akkaria:" + AkkaMessage.INVALID.name());
            leaveAkkariaUIOnBusinessViolation();
            getSender().tell(AkkaMessage.INVALID, getSelf());
        }
        else if(message.getStatus().equals(AkkaMessage.FAILED)) {
            log.info("Leaving Akkaria:" + AkkaMessage.FAILED.name());
            leaveAkkariaOnFailure();
            getSender().tell(AkkaMessage.FAILED, getSelf());
        }
        else if(message.getStatus().equals(AkkaMessage.CANCELLED)) {
            log.info("Leaving Akkaria:" + AkkaMessage.CANCELLED.name());
            leaveAkkariaOnFailure();
            getSender().tell(AkkaMessage.CANCELLED, getSelf());
        }
    }

    protected ActorRef createChildActor(Class<?> actorClass) {
        return getContext().actorOf(Props.create(actorClass), actorClass.getSimpleName());
    }



    // TODO: ActorRef or .class?
    protected abstract void enterUI(EnterAkkaria message);
    protected void leaveAkkariaUIOnBusinessViolation() {
        if(getUi() != null) {
            getUi().leave(getSelf());
        }
    }
    protected void leaveAkkariaOnFailure() {
        if(getUi() != null) {
            getUi().leave(getContext().actorFor(getSelf().path().parent()));
        }
    }
    protected abstract void leaveAkkariaOnSuccess();

}
