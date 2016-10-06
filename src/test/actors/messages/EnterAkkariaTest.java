package actors.messages;

import actors.messages.world.EnterAkkaria;
import akka.actor.ActorRef;
import org.testng.annotations.Test;
import views.ui.AkkaMocks;
import views.ui.AkkaUI;

/**
 * Created by zua on 23.09.16.
 */
public class EnterAkkariaTest {

    @Test(dataProviderClass = AkkaMocks.class, dataProvider = "mockUis")
    public void testCreate(AkkaUI mockedUI, ActorRef mockedActor) {
        EnterAkkaria enterAkkaria = new EnterAkkaria();
    }

}