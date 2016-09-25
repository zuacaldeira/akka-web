package views.ui;

import actors.Neo4JDatabaseTest;
import actors.messages.EnterAkkaria;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 22.09.16.
 */
public class UserUITest extends Neo4JDatabaseTest {


    @Test(dataProvider = "uis", dataProviderClass = AkkaMocks.class)
    public void testExistenceMVCActor(AkkaUI ui) {
        assertNotNull(ui.getMVCActor());
        ui.getMVCActor().tell(new EnterAkkaria(ui), ui.getMVCActor());
    }

    @Test
    public void testExistenceUserServlet() {
        UserUI.UserUIServlet servlet = new UserUI.UserUIServlet();
        assertNotNull(servlet);
    }
}