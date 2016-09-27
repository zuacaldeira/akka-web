package actors.mvc;

import actors.business.Supervisor;
import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import actors.messages.world.LeaveAkkaria;
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
        if(message.getStatus().equals(ControlMessage.SUCCESSFUL)) {
            log.info("Leaving Akkaria:" + ControlMessage.SUCCESSFUL.name());
            leaveAkkariaOnSuccess();
            getSender().tell(ControlMessage.SUCCESSFUL, getSelf());
        }
        else if(message.getStatus().equals(ControlMessage.INVALID)) {
            log.info("Leaving Akkaria:" + ControlMessage.INVALID.name());
            leaveAkkariaUIOnBusinessViolation();
            getSender().tell(ControlMessage.INVALID, getSelf());
        }
        else if(message.getStatus().equals(ControlMessage.FAILED)) {
            log.info("Leaving Akkaria:" + ControlMessage.FAILED.name());
            leaveAkkariaOnFailure();
            getSender().tell(ControlMessage.FAILED, getSelf());
        }
        else if(message.getStatus().equals(ControlMessage.CANCELLED)) {
            log.info("Leaving Akkaria:" + ControlMessage.CANCELLED.name());
            leaveAkkariaOnFailure();
            getSender().tell(ControlMessage.CANCELLED, getSelf());
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
            getUi().leave(getParentActor());
        }
    }
    protected abstract void leaveAkkariaOnSuccess();

    public ActorRef getParentActor() {
        return getContext().actorFor(getSelf().path().parent());
    }
}
