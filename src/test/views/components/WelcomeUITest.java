package views.components;

import com.vaadin.ui.Component;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 02.09.16.
 */
public class WelcomeUITest {
    @Test
    public void testInit() throws Exception {
        WelcomeUI ui = new WelcomeUI();
        assertNull(ui.getContent());

        ui.init(null);
        assertNotNull(ui.getContent());

        Component content = new WelcomeLayout();
        assertNotEquals(content, ui.getContent());

        ui.setContent(content);
        assertEquals(content, ui.getContent());
    }



}