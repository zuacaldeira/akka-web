package actors.mvc.views;

import actors.messages.ControlMessage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zua on 26.09.16.
 */
public class ProjectActorViewTest {
    @Test(dataProvider = "messages")
    public void testActorMailboxes(ControlMessage message) throws Exception {
        ProjectActorView actorView = new ProjectActorView();
        assertNotNull(actorView.getMailbox(message));
    }


    @DataProvider(name = "messages")
    public Object[][] messages() {
        return new Object[][] {
                {ControlMessage.CREATE},
                {ControlMessage.READ},
                {ControlMessage.UPDATE},
                {ControlMessage.DELETE}
        };
    }

}