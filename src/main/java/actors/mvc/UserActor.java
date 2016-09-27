package actors.mvc;

import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import actors.mvc.views.UserActorView;

/**
 * Created by zua on 23.09.16.
 */
public class UserActor extends MVCActor {


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
        createChildActor(ProfileActor.class).tell(new EnterAkkaria(getUi()), getSelf());
    }

    private void profile() {
        createChildActor(ProjectActor.class).tell(new EnterAkkaria(getUi()), getSelf());
    }

    @Override
    protected void enterUI(EnterAkkaria message) {
        if(message.getUi() != null) {
            message.getUi().enter(getSelf(), new UserActorView());
        }
    }

    @Override
    protected void leaveAkkariaOnSuccess() {

    }
}
