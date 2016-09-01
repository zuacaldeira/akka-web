package views.ui;

import actors.core.LoginActor;
import actors.core.RegisterActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by zua on 01.09.16.
 */
public class RegisterFormTest {
    @Test
    public void testClickCancel() throws Exception {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        RegisterForm form = new RegisterForm(actor);
        form.getEmailField().setValue("email");
        Window w = new Window();
        w.setContent(form);
        form.buttonClick(new Button.ClickEvent(form.getCancel()));
        assertEquals("", form.getEmailField().getValue());
    }

    @Test
    public void testClickSend() throws Exception {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        RegisterForm form = new RegisterForm(actor);
        form.getEmailField().setValue("email");
        form.getPasswordField().setValue("password");
        Window w = new Window();
        w.setContent(form);
        form.buttonClick(new Button.ClickEvent(form.getSend()));
        assertEquals("", form.getEmailField().getValue());
        assertEquals("", form.getPasswordField().getValue());
        assertEquals("", form.getFullName().getValue());
    }

    @Test(dataProvider = "equals")
    public void testEquals(RegisterForm a, RegisterForm b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(RegisterForm a, RegisterForm b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(RegisterActor.class));
        return new Object[][]{
                {new RegisterForm(actor), new RegisterForm(actor)}
        };
    }

}