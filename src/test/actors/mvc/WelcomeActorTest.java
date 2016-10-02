package actors.mvc;

import actors.business.TestDataProvider;
import actors.messages.ControlMessage;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.testkit.JavaTestKit;
import akka.util.Timeout;
import org.testng.annotations.Test;
import views.ui.WelcomeUI;

import java.util.concurrent.TimeUnit;

import static actors.messages.ControlMessage.LOGIN;
import static actors.messages.ControlMessage.REGISTER;

/**
 * Created by zua on 21.09.16.
 */
public class WelcomeActorTest extends MVCActorTest {

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeActor.class);
                subject.tell("okidoki", getRef());
                expectNoMsg();
            }
        };
    }

    @Override
    public void testEnterAkkaria() {
        super.testEnterAkkaria(WelcomeUI.class, WelcomeActor.class);
    }

    @Override
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(WelcomeUI.class, WelcomeActor.class, ControlMessage.SUCCESSFUL);
    }

    @Test(dataProvider = "controlMessages", dataProviderClass = TestDataProvider.class)
    public void testControlMessages(ControlMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeActor.class);
                subject.tell(message, getRef());
                expectNoMsg();
            }
        };
    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegisterMessages(RegisterMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeActor.class);
                Patterns.ask(subject, REGISTER, new Timeout(5, TimeUnit.SECONDS));
                subject.tell(message, getRef());
                expectNoMsg();
            }
        };
    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLoginMessages(LoginMessage message) {
        final ActorRef subject = createActor(WelcomeActor.class);
        new JavaTestKit(getActorSystem()) {
            {
                subject.tell(REGISTER, getRef());
                subject.tell(new RegisterMessage(message.getUsername(), message.getPassword(), "Full Name"), getRef());
                expectNoMsg();
            }
        };

        new JavaTestKit(getActorSystem()) {
            {
                Patterns.ask(subject, LOGIN, new Timeout(5, TimeUnit.SECONDS));
                subject.tell(message, getRef());
                expectNoMsg();
            }
        };
    }

    @Test(dataProvider = "invalidLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testInvalidLoginMessages(LoginMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeActor.class);
                Patterns.ask(subject, REGISTER, new Timeout(5, TimeUnit.SECONDS));
                Patterns.ask(subject, new RegisterMessage(message.getUsername(), message.getPassword(), "Full Name"), new Timeout(5, TimeUnit.SECONDS));
                Patterns.ask(subject, LOGIN, new Timeout(5, TimeUnit.SECONDS));
                subject.tell(message, getRef());
                expectNoMsg();
            }
        };
    }




}