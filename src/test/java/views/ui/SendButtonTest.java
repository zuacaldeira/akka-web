package views.ui;

import com.vaadin.server.FontAwesome;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by zua on 01.09.16.
 */
public class SendButtonTest {
    @Test
    public void testSendButton() {
        SendButton button = new SendButton();
        assertEquals("Send", button.getCaption());
        assertEquals(FontAwesome.SEND, button.getIcon());
    }

}