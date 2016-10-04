package actors.mvc;

import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import akka.actor.ActorRef;
import graphs.entities.User;
import views.ui.AkkaUI;

/**
 * Created by zua on 23.09.16.
 */
public class UserActor extends MVCActor {

    private ActorRef profileActor;
    private ActorRef projectActor;

    protected UserActor(AkkaUI ui, User user) {
        super(ui, user);
    }

    @Override
    public void onReceive(Object message) {
        if(ControlMessage.PROFILE.equals(message)) {
            profile();
        }
        if(ControlMessage.PROJECT.equals(message)) {
            project();
        }
        else{
            super.onReceive(message);
        }
    }

    private void project() {
        if(projectActor == null) {
            projectActor = createChildActor(ProjectActor.class, getUi(), getUser());
        }
        projectActor.tell(new EnterAkkaria(), getSelf());
    }

    private void profile() {
        if(profileActor == null) {
            profileActor = createChildActor(ProfileActor.class, getUi(), getUser());
        }
        profileActor.tell(new EnterAkkaria(), getSelf());
    }

}
