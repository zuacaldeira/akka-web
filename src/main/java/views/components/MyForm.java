package views.components;

import com.vaadin.data.Property;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zua on 29.08.16.
 */
public abstract class MyForm extends FormLayout implements Property.ValueChangeListener {
    public MyForm() {
        setStyleName(ValoTheme.FORMLAYOUT_LIGHT);
    }

    public abstract void validate();
}