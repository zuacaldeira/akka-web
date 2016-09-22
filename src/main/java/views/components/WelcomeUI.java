package views.components;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import views.actors.WelcomeActorView;
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



    @Override
    protected void init(VaadinRequest vaadinRequest) {
        checkCredentials(vaadinRequest);
        WelcomeActorView welcomeActorView = ActorsViewFactory.getInstance().getWelcomeActorView();
        setContent(welcomeActorView);
        setMvcActor(welcomeActorView.getActorRef());
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

}
