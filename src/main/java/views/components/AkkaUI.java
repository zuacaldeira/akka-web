package views.components;

import akka.actor.ActorRef;
import com.vaadin.ui.UI;

/**
 * Created by zua on 04.09.16.
 */
public abstract class AkkaUI extends UI {
    private ActorRef mvcActor;

    public ActorRef getMVCActor() {
        return mvcActor;
    }

    public void setMvcActor(ActorRef mvcActor) {
        this.mvcActor = mvcActor;
    }
}
