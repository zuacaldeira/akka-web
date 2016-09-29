package views.ui;

import actors.mvc.views.ActorView;
import akka.actor.ActorRef;
import com.vaadin.ui.HorizontalLayout;
import views.components.Pair;

import java.util.Stack;

/**
 * Created by zua on 28.09.16.
 */
public class StackedLayout extends HorizontalLayout {

    private Stack<ActorRef> actorStack;
    private Stack<ActorView> viewStack;

    private ActorRef currentActor;
    private ActorView currentView;

    public StackedLayout() {
        setSizeFull();
        currentActor = null;
        actorStack = new Stack<>();
        currentView = null;
        viewStack = new Stack<>();
    }


    public Pair<ActorRef, ActorView> pop() {
        if(!actorStack.isEmpty() && !viewStack.isEmpty()) {
            ActorRef actor = actorStack.pop();
            ActorView view = viewStack.pop();

            removeComponent(view);
            if(!actorStack.isEmpty()) {
                currentActor = actorStack.peek();
                currentView = viewStack.peek();
            }
            else {
                currentActor = null;
                currentView = null;
            }

            return new Pair(actor, view);
        }
        return null;
    }

    public void push(ActorRef actor, ActorView view) {
        actorStack.push(actor);
        viewStack.push(view);
        currentActor = actor;
        currentView = view;
        addComponent(currentView);
    }


    public ActorRef getCurrentActor() {
        return currentActor;
    }

    public ActorView getCurrentView() {
        return currentView;
    }

    public void enter(ActorRef actorRef, ActorView actorView) {
        if(!actorStack.isEmpty()) {
            pop();
        }
        actorView.setEnabled(true);
        push(actorRef, actorView);
    }

    public void leave() {
        if(!actorStack.isEmpty()) {
            pop();
        }
        if(currentView != null) {
            currentView.setEnabled(true);
        }
    }
}
