package views.ui;

import actors.business.ActorSystems;
import actors.mvc.MVCActor;
import actors.mvc.views.ActorView;
import actors.mvc.views.ActorsViewFactory;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.VerticalLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zua on 28.09.16.
 */
public class StackedLayout extends VerticalLayout {

    private Map<ActorRef, ActorView> actorViewMap;
    private ActorRef currentActor;
    private ActorView currentView;
    private ActorView oldView;
    private ActorRef oldActor;

    public StackedLayout() {
        setSizeFull();
        currentActor = null;
        currentView = null;
        actorViewMap = new HashMap<>();
    }

    public ActorRef getCurrentActor() {
        return currentActor;
    }

    public ActorView getCurrentView() {
        return currentView;
    }

    public void enter(ActorRef actorRef, Class<? extends MVCActor> mvcActorClass) {
        System.out.println("STACKED LAYOUT ENTER " + actorRef.path().name());
        oldActor = currentActor;
        oldView = currentView;
        currentActor = actorRef;
        currentView = ActorsViewFactory.getInstance().getActorView(mvcActorClass);
        updateView(oldView, currentView);
    }

    public void leave(ActorRef actorRef) {
        if(actorViewMap.containsKey(actorRef)) {
            removeComponent(actorViewMap.remove(actorRef));
            currentActor = oldActor;
            currentView = oldView;
            System.out.println("LEAVE " + actorRef.path().name());
        }
    }

    private void updateView(ActorView oldView, ActorView newView) {
        if(oldView != null) {
            removeComponent(oldView);
            actorViewMap.remove(oldView);
        }
        actorViewMap.put(currentActor, newView);
        addComponent(newView);
    }

    public  ActorRef createActorRef(Class<? extends MVCActor> actor, AkkaUI ui) {
        return ActorSystem.create(ActorSystems.ACTOR_SYSTEM.getAlias())
                .actorOf(Props.create(actor, ui, null), actor.getSimpleName());
    }


    public ActorRef getMVCActor() {
        return currentActor;
    }

}
