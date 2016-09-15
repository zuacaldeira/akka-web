package views.components;

import akka.actor.ActorRef;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import views.actors.ActorView;
import views.actors.WelcomeActorView;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {


    private ActorRef actor;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        checkCredentials(vaadinRequest);
        setContent(new WelcomeActorView());
    }

    private void checkCredentials(VaadinRequest request) {
    }

    public void updateUIContent(ActorView view) {
        access( () -> {
            setContent(view);
        });
    }


    /**
     * A servlet to process the application requests.
     */
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
