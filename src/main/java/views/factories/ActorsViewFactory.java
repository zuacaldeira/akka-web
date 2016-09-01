package views.factories;

import views.actors.WelcomeActorView;

/**
 * Created by zua on 28.08.16.
 */
public class ActorsViewFactory {

    private ActorsViewFactory(){
        // Hides the default constructor
    }

    public static WelcomeActorView getWelcomeActorView() {
        return new WelcomeActorView();
    }

}
