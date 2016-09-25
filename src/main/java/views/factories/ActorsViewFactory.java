package views.factories;

import actors.mvc.MVCActor;
import actors.mvc.UserActor;
import actors.mvc.WelcomeActor;
import actors.mvc.views.*;

/**
 * Created by zua on 28.08.16.
 */
public class ActorsViewFactory {

    private static final ActorsViewFactory instance = new ActorsViewFactory();

    private ActorsViewFactory(){
        // Hides the default constructor
    }

    public static ActorsViewFactory getInstance() {
        return instance;
    }

    public RegisterActorView getRegisterActorView() {
        return new RegisterActorView();
    }
    public LoginActorView getLoginActorView() {
        return new LoginActorView();
    }
    public WelcomeActorView getWelcomeActorView() {
        return new WelcomeActorView();
    }

    public ActorView getActorView(Class<? extends MVCActor> actorClass) {
        if(WelcomeActor.class == actorClass) {
            return getWelcomeActorView();
        }
        else if(UserActor.class == actorClass) {
            return getUserActorView();
        }
        return null;
    }

    private UserActorView getUserActorView() {
        return new UserActorView();
    }
}
