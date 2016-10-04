package views.components;

import org.testng.annotations.Test;
import views.ui.AkkaUI;
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
        assertTrue((new WelcomeUI()) instanceof AkkaUI);
    }
}
