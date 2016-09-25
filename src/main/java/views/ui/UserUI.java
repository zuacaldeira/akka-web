package views.ui;

import actors.messages.AkkaMessage;
import actors.mvc.UserActor;
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
public class UserUI extends AkkaUI {


    public UserUI() {
        super(UserActor.class, "UserActor");
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        checkCredentials(vaadinRequest);
        getMVCActor().tell(AkkaMessage.ENTERING, getMVCActor());
    }

    private void checkCredentials(VaadinRequest request) {
    }

    /**
     * A servlet to process the application requests.
     */
    @WebServlet(urlPatterns = "/user/*", name = "UserUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = UserUI.class, productionMode = false)
    public static class UserUIServlet extends VaadinServlet {
    }
}
