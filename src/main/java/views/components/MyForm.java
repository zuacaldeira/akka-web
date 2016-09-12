package views.components;

import com.vaadin.data.Validator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public abstract class MyForm extends FormLayout implements Validator {


    public MyForm() {
        setStyleName(ValoTheme.FORMLAYOUT_LIGHT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyForm)) {
            return false;
        }
        MyForm that = (MyForm) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(31);
    }
}
