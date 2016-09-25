package actors.mvc.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * Created by zua on 08.09.16.
 */
public class UserActorView extends ActorView {
    public static final String NAME = "UserActorView";

    /**
     * Basic actor view.
     */
    public UserActorView() {
    }

    @Override
    protected Component createActorContent() {
        return new Label("Welcome to Akkaria!");
    }



    @Override
    public void buttonClick(Button.ClickEvent event) {

    }

}
