package views.components;

import actors.business.LoginActor;
import actors.business.RegisterActor;
import actors.business.TestDataProvider;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import graphs.entities.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by zua on 01.09.16.
 */
public class RegisterFormTest {

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testValidate(RegisterMessage message) {
        RegisterForm form = new RegisterForm(null);
        form.getEmailField().setValue(message.getEmail());
        form.getPasswordField().setValue(message.getPassword());
        form.getPasswordConfirmationField().setValue(message.getPassword());
        form.getFullName().setValue(message.getFullname());
        form.validate();
    }

    @Test(dataProvider = "equals")
    public void testEquals(RegisterForm a, RegisterForm b) throws Exception {
        assertTrue(a.equals(b));
    }

    @Test(dataProvider = "equals")
    public void testHashCode(RegisterForm a, RegisterForm b) throws Exception {
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test(dataProvider = "inequals")
    public void testInequals(RegisterForm a, Object b) throws Exception {
        assertFalse(a.equals(b));
    }

    @DataProvider(name = "equals")
    public Object[][] equals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(RegisterActor.class));
        RegisterForm r1 = new RegisterForm(actor);
        return new Object[][]{
                {r1, new RegisterForm(actor)}, {r1, r1}
        };
    }

    @DataProvider(name = "inequals")
    public Object[][] inequals() {
        ActorRef actor = ActorSystem.create().actorOf(Props.create(RegisterActor.class));
        ActorRef actor2 = ActorSystem.create().actorOf(Props.create(LoginActor.class));
        return new Object[][]{
                {new RegisterForm(actor), new RegisterForm(actor2)},
                {new RegisterForm(actor), null},
                {new RegisterForm(actor), new User()}
        };
    }
}