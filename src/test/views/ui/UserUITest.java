package views.ui;

import actors.Neo4JDatabaseTest;
import actors.messages.ControlMessage;
import actors.messages.world.EnterAkkaria;
import actors.messages.world.LeaveAkkaria;
import akka.actor.ActorRef;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 22.09.16.
 */
public class UserUITest extends Neo4JDatabaseTest {
    @Test(dataProvider = "uis", dataProviderClass = AkkaMocks.class)
    public void testInit(AkkaUI ui) throws Exception {
        ui.init(null);
        assertNotNull(ui.getMVCActor());
        assertNotNull(ui.getContent());
    }


    @Test(dataProvider = "mockUis", dataProviderClass = AkkaMocks.class)
    public void testExistenceMVCActor(AkkaUI ui, ActorRef actor) {
        ui.setMVCActor(actor);
        ActorRef uiActor = ui.getMVCActor();
        assertNotNull(uiActor);
        ui.getMVCActor().tell(new EnterAkkaria(ui), ui.getMVCActor());
        ui.getMVCActor().tell(new LeaveAkkaria(ui, ControlMessage.SUCCESSFUL), ui.getMVCActor());
    }

    @Test
    public void testExistenceUserServlet() {
        UserUI.UserUIServlet servlet = new UserUI.UserUIServlet();
        assertNotNull(servlet);
    }
}