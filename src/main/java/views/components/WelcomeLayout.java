package views.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import views.actors.StyleClassNames;
import views.actors.WelcomeActorView;

/**
 * Created by zua on 28.08.16.
 */
public class WelcomeLayout extends TopLayout {

    private final WelcomeActorView mvc;
    private final Component details;

    public WelcomeLayout() {
        mvc = new WelcomeActorView();
        details = new Label("Welcome!");
        details.setStyleName(StyleClassNames.ACTOR_NAME);
        addComponents(mvc, details);
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

}
