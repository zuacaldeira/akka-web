package views.ui;

import actors.messages.world.EnterAkkaria;
import actors.mvc.WelcomeActor;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class WelcomeUI extends AkkaUI {


    public WelcomeUI() {
        super(WelcomeActor.class, "WelcomeActor");
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        checkCredentials(vaadinRequest);
        super.setContent();
        getMVCActor().tell(new EnterAkkaria(this), getMVCActor());
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
