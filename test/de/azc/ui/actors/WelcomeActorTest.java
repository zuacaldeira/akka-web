package de.azc.ui.actors;

import actors.core.WelcomeActor;
import actors.messages.AkkaMessages;
import actors.messages.LoginMessage;
import actors.messages.RegisterMessage;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.JavaTestKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zua on 28.08.16.
 */

public class WelcomeActorTest {

    private static ActorSystem system;

    @Before
    public  void setup() {
        system = ActorSystem.create("ActorSystem_Test");
    }

    @Test
    public void testRegister() {
        new JavaTestKit(system) {
            {
                ActorRef welcomeActor = system.actorOf(WelcomeActor.PROPS, WelcomeActor.NAME);
                welcomeActor.tell(new RegisterMessage("username", "password", "Full Name"), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

    @Test
    public void testLogin() {
        new JavaTestKit(system) {
            {
                ActorRef welcomeActor = system.actorOf(WelcomeActor.PROPS, WelcomeActor.NAME);
                welcomeActor.tell(new LoginMessage("username", "password"), getRef());
                expectMsgAnyOf(AkkaMessages.DONE);
            }
        };
    }

    @After
    public void shutdown() {
        system.shutdown();
    }
}
