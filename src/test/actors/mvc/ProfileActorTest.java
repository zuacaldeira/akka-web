package actors.mvc;

import actors.messages.ControlMessage;
import views.ui.UserUI;

/**
 * Created by zua on 02.10.16.
 */
public class ProfileActorTest extends MVCActorTest {


    @Override
    public void testUnhandled() {
        super.testControlMessage(UserUI.class, ProfileActor.class, ControlMessage.UNKNOWN);
    }

    @Override
    public void testEnterAkkaria() {
        super.testEnterAkkaria(UserUI.class, ProfileActor.class);
    }

    @Override
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(UserUI.class, ProfileActor.class, ControlMessage.SUCCESSFUL);
    }

}