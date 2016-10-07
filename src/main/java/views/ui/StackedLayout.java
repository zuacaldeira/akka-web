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
import java.util.Objects;
import java.util.logging.Logger;

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
        Logger.getLogger(getClass().getSimpleName()).info("Actor " + actorRef + " entering stacked layout");
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
            Logger.getLogger(getClass().getSimpleName()).info("Actor " + actorRef + " left stacked layout");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StackedLayout)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        StackedLayout layout = (StackedLayout) o;
        return Objects.equals(currentActor, layout.currentActor) &&
                Objects.equals(currentView, layout.currentView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentActor, currentView);
    }
}
