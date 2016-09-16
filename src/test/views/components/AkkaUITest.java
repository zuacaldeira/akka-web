package views.components;

import com.vaadin.server.VaadinRequest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 16.09.16.
 */
public class AkkaUITest {


    /**
     * Validates our UI type hierarchy.
     */
    @Test
    public void testKnownSubclasses() {
        assertTrue((new AkkaUI() {
            @Override
            protected void init(VaadinRequest request) {

            }
        }) instanceof AkkaUI);
        assertTrue((new WelcomeUI()) instanceof AkkaUI);
        assertTrue((new UserUI()) instanceof AkkaUI);
    }
}
