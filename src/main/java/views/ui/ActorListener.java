package views.ui;

import akka.actor.ActorRef;
import actors.mvc.views.ActorView;

/**
 * Created by zua on 24.09.16.
 */
public interface ActorListener {

    void enter(ActorRef actor, ActorView view);
}
