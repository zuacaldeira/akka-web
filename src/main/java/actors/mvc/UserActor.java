package actors.mvc;

import actors.messages.EnterAkkaria;
import actors.mvc.views.UserActorView;

/**
 * Created by zua on 23.09.16.
 */
public class UserActor extends MVCActor {
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
