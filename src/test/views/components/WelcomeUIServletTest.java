package views.components;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 02.09.16.
 */
public class WelcomeUIServletTest {

    @Test
    public void testServlet() {
        WelcomeUI.MyUIServlet servlet = new WelcomeUI.MyUIServlet();
        assertNotNull(servlet);
    }
}