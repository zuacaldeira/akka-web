package views.components;

import com.vaadin.server.FontAwesome;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by zua on 01.09.16.
 */
public class CancelButtonTest {

    @Test
    public void testCancelButton() {
        CancelButton button = new CancelButton();
        assertEquals("Cancel", button.getCaption());
        assertEquals(FontAwesome.TIMES, button.getIcon());
    }
}