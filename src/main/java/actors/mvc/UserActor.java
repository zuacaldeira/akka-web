package actors.mvc;

import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import akka.actor.ActorRef;

/**
 * Created by zua on 23.09.16.
 */
public class UserActor extends MVCActor {


    private ActorRef profileActor;
    private ActorRef projectActor;

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
            projectActor = createChildActor(ProjectActor.class);
        }
        projectActor.tell(new EnterAkkaria(getUi()), getSelf());
    }

    private void profile() {
        if(profileActor == null) {
            profileActor = createChildActor(ProfileActor.class);
        }
        profileActor.tell(new EnterAkkaria(getUi()), getSelf());
    }

}
