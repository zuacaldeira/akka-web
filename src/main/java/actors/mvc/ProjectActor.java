package actors.mvc;

import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import akka.actor.ActorRef;
import graphs.entities.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 26.09.16.
 */
public class ProjectActor extends MVCActor {

    private ActorRef projectCreatorActor;
    private ActorRef projectReaderActor;
    private ActorRef projectUpdaterActor;
    private ActorRef projectDestroyerActor;

    protected ProjectActor(AkkaUI ui, User user) {
        super(ui, user);
    }

    @Override
    public void onReceive(Object message) {
        if(ControlMessage.CREATE.equals(message)) {
            enterCreator();
        }
        else if(ControlMessage.READ.equals(message)) {
            enterReader();
        }
        else if(ControlMessage.UPDATE.equals(message)) {
            enterUpdater();
        }
        else if(ControlMessage.DELETE.equals(message)) {
            enterDestroyer();
        }
        else {
            super.onReceive(message);
        }
    }

    private void enterDestroyer() {
        if(projectDestroyerActor == null) {
            projectDestroyerActor = createChildActor(ProjectDestroyerActor.class, getUi(), getUser());
        }
        projectDestroyerActor.tell(new EnterAkkaria(), getSelf());
    }

    private void enterUpdater() {
        if(projectUpdaterActor == null) {
            projectUpdaterActor = createChildActor(ProjectUpdaterActor.class, getUi(), getUser());
        }
        projectUpdaterActor.tell(new EnterAkkaria(), getSelf());
    }

    private void enterReader() {
        if(projectReaderActor == null) {
            projectReaderActor = createChildActor(ProjectReaderActor.class, getUi(), getUser());
        }
        projectReaderActor.tell(new EnterAkkaria(), getSelf());
    }

    private void enterCreator() {
        if(projectCreatorActor == null) {
            projectCreatorActor = createChildActor(ProjectCreatorActor.class, getUi(), getUser());
        }
        projectCreatorActor.tell(new EnterAkkaria(), getSelf());
    }

}
