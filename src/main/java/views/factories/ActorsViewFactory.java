package views.factories;

import views.actors.LoginActorView;
import views.actors.RegisterActorView;
import views.actors.WelcomeActorView;

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

    public WelcomeActorView getWelcomeActorView() {
        return new WelcomeActorView();
    }
    public RegisterActorView getRegisterActorView() {
        return new RegisterActorView();
    }
    public LoginActorView getLoginActorView() {
        return new LoginActorView();
    }
}
