package views.components;

import actors.mvc.WelcomeActor;
import com.vaadin.server.VaadinRequest;
import org.testng.annotations.Test;
import views.ui.AkkaUI;
import views.ui.UserUI;
import views.ui.WelcomeUI;

import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 16.09.16.
 */
public class AkkaUITest extends UITest{


    /**
     * Validates our UI type hierarchy.
     */
    @Test
    public void testKnownSubclasses() {
        assertTrue((new AkkaUI(WelcomeActor.class, "TestWelcomeMVCActor") {
            @Override
            protected void init(VaadinRequest request) {

            }
        }) instanceof AkkaUI);
        assertTrue((new WelcomeUI()) instanceof AkkaUI);
        assertTrue((new UserUI()) instanceof AkkaUI);
    }
}
