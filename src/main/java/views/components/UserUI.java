package views.components;

import com.vaadin.server.VaadinRequest;
import utils.NotYetImplementedException;

/**
 * Created by zua on 04.09.16.
 */
public class UserUI extends AkkaUI {
    @Override
    protected void init(VaadinRequest request) {
        checkCredentials(request);
        setContent(new WelcomeLayout());
    }

    private void checkCredentials(VaadinRequest request) {
        throw new NotYetImplementedException("NotYetImplemented");
    }
}
