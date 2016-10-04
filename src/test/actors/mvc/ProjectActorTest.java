package actors.mvc;

import actors.messages.ControlMessage;
import org.testng.annotations.Test;

/**
 * Created by zua on 02.10.16.
 */
public class ProjectActorTest extends CRUDActorTest {

    @Override
    public void testUnhandled() {
        super.testUnhandled(ProjectActor.class);
    }

    @Override
    public void testEnterAkkaria() {
        super.testEnterAkkaria(createActor(ProjectActor.class));
    }

    @Override
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(createActor(ProjectActor.class), ControlMessage.SUCCESS);
    }


    @Test
    public void testCreate() {
        super.testControlMessage(createActor(ProjectActor.class), ControlMessage.CREATE);
    }

    @Test
    public void testRead() {
        super.testControlMessage(createActor(ProjectActor.class), ControlMessage.READ);
    }

    @Test
    public void testUpdate() {
        super.testControlMessage(createActor(ProjectActor.class), ControlMessage.UPDATE);
    }

    @Test
    public void testDelete() {
        super.testControlMessage(createActor(ProjectActor.class), ControlMessage.DELETE);
    }

}