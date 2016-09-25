package views.ui;

import actors.mvc.UserActor;
import actors.mvc.views.ActorView;
import akka.actor.ActorRef;
import com.vaadin.server.VaadinRequest;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;

/**
 * Created by zua on 25.09.16.
 */
public class AkkaMocks {


    @DataProvider(name = "uis")
    public static Object[][] uis() {
        return new Object[][] {
                {new WelcomeUI()},
                {new UserUI()}
        };
    }

    @DataProvider(name = "mockUis")
    public static Object[][] mockUIs() {
        return new Object[][] {
                {getWelcomeMockUI()},
                {getUserMockUI()}
        };
    }

    private static UserUI getUserMockUI() {
        UserUI ui = Mockito.mock(UserUI.class);
        Mockito.doCallRealMethod().when(ui).setMVCActor(AkkaUI.createActorRef(UserActor.class, "MockUserActor"));
        Mockito.doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.any(ActorView.class));
        Mockito.doNothing().when(ui).init(Mockito.any(VaadinRequest.class));
        return ui;
    }

    private static WelcomeUI getWelcomeMockUI() {
        WelcomeUI ui = Mockito.mock(WelcomeUI.class);
        Mockito.doCallRealMethod().when(ui).setMVCActor(AkkaUI.createActorRef(UserActor.class, "MockUserActor"));
        Mockito.doNothing().when(ui).enter(Mockito.any(ActorRef.class), Mockito.any(ActorView.class));
        Mockito.doNothing().when(ui).init(Mockito.any(VaadinRequest.class));
        return ui;
    }


}
