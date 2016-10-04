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
import graphs.entities.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 28.08.16.
 */

public abstract class MVCActor extends Supervisor {
    private final User user;
    private AkkaUI ui;


    protected  MVCActor(AkkaUI ui, User user) {
        this.ui = ui;
        this.user = user;
    }


    public User getUser() {
        return user;
    }

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
        enterUI(message);
    }


    private void leaveAkkaria(LeaveAkkaria message) {
        if(message.getResult().equals(ControlMessage.SUCCESS)) {
            leaveAkkariaOnSuccess(message.getResult());
        }
        else if(message.getResult() instanceof BusinessViolation) {
            leaveAkkariaUIOnBusinessViolation((BusinessViolation) message.getResult());
        }
        else if(message.getResult() instanceof SystemFailure) {
            leaveAkkariaOnFailure((SystemFailure) message.getResult());
        }
        else if(message.getResult().equals(ControlMessage.CANCELLED)) {
            leaveAkkariaUIOnCancel(message);
        }
    }

    protected final void leaveAkkariaUIOnCancel(LeaveAkkaria message) {
        log.info("Leaving Akkaria onCancel");
        if(message.getUi() != null) {
            message.getUi().leave(getSelf(), ControlMessage.CANCELLED);
            getParentActor().tell(new EnterAkkaria(), getSender());
        }
    }

    // TODO: ActorRef or .class?
    protected final void enterUI(EnterAkkaria message) {
        if(getUi() != null) {
            getUi().enter(getSelf(), getActorView());
        }
    }

    protected final ActorView getActorView() {
        return ActorsViewFactory.getInstance().getActorView(this.getClass());
    };


    protected final void leaveAkkariaUIOnBusinessViolation(BusinessViolation exception) {
        log.info("Leaving Akkaria on Violation:" + ControlMessage.INVALID.name());
        if(getUi() != null) {
            getSender().tell(ControlMessage.INVALID, getSelf());
        }
        // Goes to supervision
        throw exception;
    }
    protected final void leaveAkkariaOnFailure(SystemFailure data) {
        log.info("Leaving Akkaria on Failure:" + ControlMessage.FAILURE.name());
        if(getUi() != null) {
            getUi().leave(getSelf(), ControlMessage.FAILURE);
            getParentActor().tell(ControlMessage.FAILURE, getSelf());
        }
        // Goes to supervision
        throw  data;
    }

    protected void leaveAkkariaOnSuccess(Object o) {
        log.info("Leaving Akkaria on Success:" + ControlMessage.SUCCESS.name());
        if(getUi() != null) {
            //getUi().leave(getSelf(), ControlMessage.SUCCESS);
            getSender().tell(ControlMessage.SUCCESS, getSelf());
            getNextActor(o).tell(new EnterAkkaria(), getSelf());
        }
    }

    protected ActorRef getNextActor(Object... args) {
        return getParentActor();
    }

    public ActorRef getParentActor() {
        return getContext().actorFor(getSelf().path().parent());
    }
}
