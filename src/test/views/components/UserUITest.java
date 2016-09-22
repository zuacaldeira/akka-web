package views.components;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 22.09.16.
 */
public class UserUITest {


    @Test
    public void testExistenceMVCActor() {
        UserUI ui = new UserUI();
        ui.init(null);
        assertNotNull(ui.getMVCActor());
    }

    @Test
    public void testExistenceUserServlet() {
        UserUI.UserUIServlet servlet = new UserUI.UserUIServlet();
        assertNotNull(servlet);
    }
}