package actors.mvc;

import actors.business.AbstractActorTest;
import actors.business.TestDataProvider;
import actors.messages.AkkaMessage;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.testkit.JavaTestKit;
import akka.util.Timeout;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static actors.messages.AkkaMessage.*;

/**
 * Created by zua on 21.09.16.
 */
public class WelcomeMVCActorTest extends AbstractActorTest {

    @Override
    public void testUnhandled() {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeMVCActor.class);
                subject.tell("okidoki", getRef());
                expectNoMsg();
            }
        };
    }

    @Test(dataProvider = "controlMessages", dataProviderClass = TestDataProvider.class)
    public void testControlMessages(AkkaMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeMVCActor.class);
                subject.tell(message, getRef());
                expectNoMsg();
            }
        };
    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegisterMessages(RegisterMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeMVCActor.class);
                Patterns.ask(subject, REGISTER, new Timeout(5, TimeUnit.SECONDS));
                subject.tell(message, getRef());
                expectMsgAnyOf(REGISTER_SUCCESSFUL);
            }
        };
    }

    @Test(dataProvider = "validLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testLoginMessages(LoginMessage message) {
        final ActorRef subject = createActor(WelcomeMVCActor.class);
        new JavaTestKit(getActorSystem()) {
            {
                subject.tell(REGISTER, getRef());
                subject.tell(new RegisterMessage(message.getUsername(), message.getPassword(), "Full Name"), getRef());
                expectMsgAnyOf(REGISTER_SUCCESSFUL);
            }
        };

        new JavaTestKit(getActorSystem()) {
            {
                Patterns.ask(subject, LOGIN, new Timeout(5, TimeUnit.SECONDS));
                subject.tell(message, getRef());
                expectMsgAnyOf(LOGIN_SUCCESSFUL);
            }
        };
    }

    @Test(dataProvider = "invalidLoginMessages", dataProviderClass = TestDataProvider.class)
    public void testInvalidLoginMessages(LoginMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeMVCActor.class);
                Patterns.ask(subject, REGISTER, new Timeout(5, TimeUnit.SECONDS));
                Patterns.ask(subject, new RegisterMessage(message.getUsername(), message.getPassword(), "Full Name"), new Timeout(5, TimeUnit.SECONDS));
                Patterns.ask(subject, LOGIN, new Timeout(5, TimeUnit.SECONDS));
                subject.tell(message, getRef());
                expectMsgAnyOf(LOGIN_INVALID);
            }
        };
    }




}