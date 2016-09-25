package views.ui;

import actors.messages.EnterAkkaria;
import actors.mvc.UserActor;
import akka.actor.ActorRef;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 22.09.16.
 */
public class UserUITest {


    @Test
    public void testExistenceMVCActor() {
        UserUI ui = new UserUI();
        ActorRef ref = ui.createActorRef(UserActor.class, "UserActor");
        ref.tell(new EnterAkkaria(ui), ui.getMVCActor());
        assertNotNull(ui.getMVCActor());
        assertNotNull(ui.getContent());
    }

    @Test
    public void testExistenceUserServlet() {
        UserUI.UserUIServlet servlet = new UserUI.UserUIServlet();
        assertNotNull(servlet);
    }
}