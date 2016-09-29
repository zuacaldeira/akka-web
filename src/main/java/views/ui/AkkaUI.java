package views.ui;

import actors.business.ActorSystems;
import actors.mvc.MVCActor;
import actors.mvc.views.ActorView;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * Created by zua on 04.09.16.
 */
@Push
public abstract class AkkaUI extends UI implements ActorListener {
    private ActorRef mvcActor;
    private String actorName;
    private StackedLayout stackedLayout;

    public static ActorRef createActorRef(Class<? extends MVCActor> actor, String name) {
        return ActorSystem.create(ActorSystems.ACTOR_SYSTEM.getAlias())
                .actorOf(Props.create(actor), name);
    }

    public AkkaUI(Class<? extends MVCActor> actor, String name) {
        this.mvcActor = createActorRef(actor, name);
        this.actorName = name;
        stackedLayout = new StackedLayout();
        setContent(stackedLayout);
    }

    @Override
    protected abstract void init(VaadinRequest request);

    public ActorRef getMVCActor() {
        return mvcActor;
    }

    public HorizontalLayout getContent() {
        return (HorizontalLayout) super.getContent();
    }

    @Override
    public void enter(ActorRef actorRef, ActorView actorView) {
        access(() -> {
            stackedLayout.enter(actorRef, actorView);
            setMVCActor(stackedLayout.getCurrentActor());
        });
    }

    //@Override
    public void leave(ActorRef actorRef) {
        access(() -> {
            stackedLayout.leave();
            setMVCActor(stackedLayout.getCurrentActor());
        });
    }

    public void jump(String location) {
        access(() -> {
            getPage().setLocation(location);
        });
    }

    public ActorView getTop() {
        if(getContent().getComponentCount() > 0) {
            return (ActorView) getContent().getComponent(getContent().getComponentCount()-1);
        }
        else {
            return null;
        }
    }


    public void setMVCActor(ActorRef mvcActor) {
        this.mvcActor = mvcActor;
    }

    public void setContent() {
        stackedLayout = new StackedLayout();
        super.setContent(stackedLayout);
    }
}
