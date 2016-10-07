package views.ui;

import actors.mvc.MVCActor;
import actors.mvc.views.ActorView;
import akka.actor.ActorRef;
import com.vaadin.annotations.Push;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import graphs.entities.nodes.User;

import java.util.Objects;

/**
 * Created by zua on 04.09.16.
 */
@Push
public abstract class AkkaUI extends UI implements Akkaria {
    private String actorName;
    private StackedLayout stackedLayout;
    private User user;

    public AkkaUI(String name) {
        this.actorName = name;
        stackedLayout = new StackedLayout();
        setContent();
    }

    @Override
    protected abstract void init(VaadinRequest request);

    public ActorRef getMVCActor() {
        return stackedLayout.getMVCActor();
    }

    @Override
    public void enter(ActorRef actorRef, Class<? extends MVCActor>  actorClass) {
        access(() -> stackedLayout.enter(actorRef, actorClass));
    }

    public void leave(ActorRef actorRef) {
        access(() -> stackedLayout.leave(actorRef));
    }


    private void setContent() {
        stackedLayout = new StackedLayout();
        super.setContent(stackedLayout);
    }


    public User getUser() {
        return user;
    }

    public ActorRef createActorRef(Class<? extends MVCActor> actorClass) {
        return stackedLayout.createActorRef(actorClass, this);
    }

    public ActorView getCurrentView() {
        return stackedLayout.getCurrentView();
    }

    @Override
    public StackedLayout getContent() {
        return (StackedLayout) super.getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AkkaUI)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AkkaUI that = (AkkaUI) o;
        return Objects.equals(actorName, that.actorName) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), actorName, user);
    }
}
