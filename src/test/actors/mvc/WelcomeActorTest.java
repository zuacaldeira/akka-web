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
        super.testEnterAkkaria(createActor(WelcomeActor.class));
    }

    @Override
    public void testLeaveAkkaria() {
        super.testLeaveAkkaria(createActor(WelcomeActor.class), ControlMessage.SUCCESS);
    }

    @Test(dataProvider = "controlMessages", dataProviderClass = TestDataProvider.class)
    public void testControlMessages(ControlMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeActor.class);
                subject.tell(message, getRef());
                expectMsgEquals(ControlMessage.SUCCESS);
            }
        };
    }

    @Test(dataProvider = "validRegisterMessages", dataProviderClass = TestDataProvider.class)
    public void testRegisterMessages(RegisterMessage message) {
        new JavaTestKit(getActorSystem()) {
            {
                ActorRef subject = createActor(WelcomeActor.class);
                subject.tell(REGISTER, getRef());
                subject.tell(message, getRef());
                expectMsgEquals(ControlMessage.SUCCESS);
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
                expectMsgEquals(ControlMessage.SUCCESS);
            }
        };

        new JavaTestKit(getActorSystem()) {
            {
                subject.tell(LOGIN, getRef());
                subject.tell(message, getRef());
                expectMsgEquals(ControlMessage.SUCCESS);
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