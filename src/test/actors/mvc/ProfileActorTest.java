package actors.mvc;

import actors.messages.ControlMessage;
import graphs.entities.nodes.User;

/**
 * Created by zua on 02.10.16.
 */
public class ProfileActorTest extends MVCActorTest {


    @Override
    public void testUnhandled() {
        super.testControlMessage(createActor(ProfileActor.class), ControlMessage.UNKNOWN);
    }

    @Override
    public void testEnterAkkaria() {
        super.testEnterAkkaria(createActor(ProjectActor.class));
    }

    @Override
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(createActor(UserActor.class, new User()), ControlMessage.SUCCESS);
    }

}