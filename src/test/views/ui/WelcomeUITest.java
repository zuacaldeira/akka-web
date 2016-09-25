package views.ui;

import com.vaadin.ui.HorizontalLayout;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 02.09.16.
 */
public class WelcomeUITest {
    @Test
    public void testInit() throws Exception {
        WelcomeUI ui = new WelcomeUI();
        ui.init(null);
        assertNotNull(ui.getContent());
        assertTrue(ui.getContent() instanceof HorizontalLayout);
        assertNotNull(ui.getMVCActor());
        // Will be not null but in future
        assertNull(ui.getTop());
    }

    @Test
    public void testCreateServlet() throws Exception {
        WelcomeUI.MyUIServlet servlet = new WelcomeUI.MyUIServlet();
        assertNotNull(servlet);
    }


}