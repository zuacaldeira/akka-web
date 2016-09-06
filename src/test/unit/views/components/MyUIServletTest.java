package views.components;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 02.09.16.
 */
public class MyUIServletTest {

    @Test
    public void testServlet() {
        MyUI.MyUIServlet servlet = new MyUI.MyUIServlet();
        assertNotNull(servlet);
    }
}