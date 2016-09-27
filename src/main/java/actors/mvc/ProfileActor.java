package actors.mvc;

import actors.messages.world.EnterAkkaria;

/**
 * Created by zua on 26.09.16.
 */
public class ProfileActor extends MVCActor {
    @Override
    protected void enterUI(EnterAkkaria message) {

    }

    @Override
    protected void leaveAkkariaOnSuccess() {
        getUi().leave(super.getParentActor());
    }
}
