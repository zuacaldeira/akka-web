package views.components;

import actors.core.MVCUntypedActor;
import actors.messages.AkkaMessages;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import views.factories.ActorsViewFactory;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@PreserveOnRefresh
public class WelcomeUI extends AkkaUI {



    private ActorRef actor;
    private ActorRef mvcActor;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        checkCredentials(vaadinRequest);
        mvcActor = ActorSystem.create("WelcomeActorSystem").actorOf(Props.create(MyUIActor.class, this));
        mvcActor.tell(AkkaMessages.WELCOME, mvcActor);
        //setContent(new WelcomeActorView());
    }

    private void checkCredentials(VaadinRequest request) {
    }




    /**
     * A servlet to process the application requests.
     */
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = WelcomeUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    /**
     * An actor to communicate with the actor system
     */
    public static class MyUIActor extends MVCUntypedActor {

        private final WelcomeUI ui;

        public MyUIActor(WelcomeUI ui) {
            this.ui = ui;
        }

        @Override
        public void onReceive(Object message) {
            log.info("[{}] Enter actor", MyUIActor.class.getSimpleName());
            System.out.println();
            if(message instanceof String) {
                String m = (String) message;
                processMessage(m);
            }
        }

        private void processMessage(String m) {
            switch (m) {
                case AkkaMessages.WELCOME:
                    System.out.println("Received WELCOME");
                    ui.access(() -> {
                        ui.getSession().getLockInstance().lock();
                        ui.setContent(ActorsViewFactory.getInstance().getWelcomeActorView());
                        System.out.println("MyUIActor sets WelcomeActorView as UI content");
                        ui.getSession().getLockInstance().unlock();
                    });
                    break;
            }
        }
    }
}
