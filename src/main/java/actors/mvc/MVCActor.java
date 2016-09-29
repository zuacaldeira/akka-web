package actors.mvc;

import actors.business.Supervisor;
import actors.exceptions.BusinessViolation;
import actors.exceptions.SystemFailure;
import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import actors.messages.world.LeaveAkkaria;
import actors.mvc.views.ActorView;
import actors.mvc.views.ActorsViewFactory;
import akka.actor.ActorRef;
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
            super.onReceive(message);
        }
    }

    private void enterAkkaria(EnterAkkaria message) {
        log.info("Entering Akkaria");
        this.ui = message.getUi();
        enterUI(message);
    }


    private void leaveAkkaria(LeaveAkkaria message) {
        if(message.getCause().equals(ControlMessage.SUCCESSFUL)) {
            log.info("Leaving Akkaria on Success:" + ControlMessage.SUCCESSFUL.name());
            leaveAkkariaOnSuccess();
        }
        else if(message.getCause() instanceof BusinessViolation) {
            log.info("Leaving Akkaria on Violation:" + ControlMessage.INVALID.name());
            leaveAkkariaUIOnBusinessViolation((BusinessViolation) message.getCause());
            getSender().tell(ControlMessage.INVALID, getSelf());
        }
        else if(message.getCause() instanceof SystemFailure) {
            log.info("Leaving Akkaria on Failure:" + ControlMessage.FAILED.name());
            leaveAkkariaOnFailure((SystemFailure) message.getCause());
            getSender().tell(ControlMessage.FAILED, getSelf());
        }
        else if(message.getCause().equals(ControlMessage.CANCELLED)) {
            log.info("Leaving Akkaria onCancel");
            leaveAkkariaUIOnCancel(message);
            getSender().tell(ControlMessage.CANCELLED, getSelf());
        }
    }

    protected final void leaveAkkariaUIOnCancel(LeaveAkkaria message) {
        if(message.getUi() != null) {
            log.info("Leaving cause CANCELLED");
            message.getUi().leave(getSelf());
            getParentActor().tell(new EnterAkkaria(message.getUi()), getSender());
        }
    }

    // TODO: ActorRef or .class?
    protected final void enterUI(EnterAkkaria message) {
        if(message.getUi() != null) {
            message.getUi().enter(getSelf(), getActorView());
        }
    }

    protected final ActorView getActorView() {
        return ActorsViewFactory.getInstance().getActorView(this.getClass());
    };


    protected final void leaveAkkariaUIOnBusinessViolation(BusinessViolation exception) {
        if(getUi() != null) {
            getUi().leave(getSelf());
        }
        // Goes to supervision
        throw exception;
    }
    protected final void leaveAkkariaOnFailure(SystemFailure data) {
        if(getUi() != null) {
            getUi().leave(getParentActor());
        }
        // Goes to supervision
        throw  data;
    }

    protected void leaveAkkariaOnSuccess() {
        getSender().tell(ControlMessage.SUCCESSFUL, getSelf());
    }

    public ActorRef getParentActor() {
        return getContext().actorFor(getSelf().path().parent());
    }
}
