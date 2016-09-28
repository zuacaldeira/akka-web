package actors.mvc.views;

import actors.mvc.*;

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


    public ActorView getActorView(Class<? extends MVCActor> actorClass) {
        if(WelcomeActor.class == actorClass) {
            return getWelcomeActorView();
        }
        else if(LoginActor.class == actorClass) {
            return getLoginActorView();
        }
        else if(RegisterActor.class == actorClass) {
            return getRegisterActorView();
        }
        else if(UserActor.class == actorClass) {
            return getUserActorView();
        }
        else if(ProfileActor.class == actorClass) {
            return getProfileActorView();
        }
        else if(ProjectActor.class == actorClass) {
            return getProjectActorView();
        }
        return null;
    }

    private UserActorView getUserActorView() {
        return new UserActorView();
    }
    private RegisterActorView getRegisterActorView() {
        return new RegisterActorView();
    }
    private LoginActorView getLoginActorView() {
        return new LoginActorView();
    }
    private WelcomeActorView getWelcomeActorView() {
        return new WelcomeActorView();
    }
    private ProjectActorView getProjectActorView() {
        return new ProjectActorView();
    }
    private ProfileActorView getProfileActorView() {
        return new ProfileActorView();
    }

}
