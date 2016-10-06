package views.ui;

import actors.business.AbstractActorTest;
import actors.mvc.MVCActor;
import actors.mvc.WelcomeActor;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 02.09.16.
 */
public class WelcomeUITest extends AbstractActorTest {
    @Test
    public void testInit() throws Exception {
        WelcomeUI ui = new WelcomeUI();
        ui.init(null);
        assertNotNull(ui.getContent());
        assertTrue(ui.getContent() instanceof StackedLayout);
    }

    @Test
    public void testEnterActor() {
        WelcomeUI ui = new WelcomeUI();
        assertNotNull(ui.getContent());
        assertTrue(ui.getContent() instanceof StackedLayout);
        assertEquals(ui.getContent().getComponentCount(), 0);
        Class<? extends MVCActor> aClass = WelcomeActor.class;
        ui.getContent().enter(ui.createActorRef(aClass), aClass);
        assertEquals(ui.getContent().getComponentCount(), 1);
    }

    @Test
    public void testCreateServlet() throws Exception {
        WelcomeUI.MyUIServlet servlet = new WelcomeUI.MyUIServlet();
        assertNotNull(servlet);
    }


}