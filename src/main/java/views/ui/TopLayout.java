package views.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by zua on 28.08.16.
 */
public abstract class TopLayout extends HorizontalLayout {

    public TopLayout() {
        setSizeFull();
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        initActors();
    }

    protected abstract void initActors();
}
