package views.components;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by zua on 28.08.16.
 */
public abstract class TopLayout extends HorizontalLayout {

    private Navigator navigator;

    /**
     * Initializes the common layout features of the main layouts
     */
    public TopLayout() {
        setSizeFull();
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
