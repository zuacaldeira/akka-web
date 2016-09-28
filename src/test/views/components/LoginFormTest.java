package views.components;

import actors.mvc.LoginActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 01.09.16.
 */
public class LoginFormTest extends UITest {
    @Test
    public void testClickCancel() throws Exception {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        LoginForm form = new LoginForm();
        form.getEmailField().setValue("email");
        assertEquals("email", form.getEmailField().getValue());
    }

    @Test
    public void testClickSend() throws Exception {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        LoginForm form = new LoginForm();
        form.getEmailField().setValue("email");
        form.getPasswordField().setValue("password");
        assertEquals("email", form.getEmailField().getValue());
        assertEquals("password", form.getPasswordField().getValue());
    }

    @Test(dataProvider = "equals")
    public void testEquals(LoginForm a, LoginForm b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(LoginForm a, LoginForm b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        LoginForm lf1 = new LoginForm();
        return new Object[][]{
            {new LoginForm(), new LoginForm()},
                {lf1, lf1}
        };
    }
}