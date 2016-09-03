package views.ui;

import com.vaadin.ui.HorizontalLayout;

/**
 * Created by zua on 28.08.16.
 */
public abstract class TopLayout extends HorizontalLayout {

    /**
     * Initializes the common layout features of the main layouts
     */
    public TopLayout() {
        setSizeFull();
        initActors();
    }

    protected abstract void initActors();
}
