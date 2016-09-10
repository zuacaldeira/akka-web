package views.components;

import com.vaadin.data.Validator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Objects;

/**
 * Created by zua on 29.08.16.
 */
public abstract class MyForm extends FormLayout implements Validator {

    private State formState;



    public MyForm() {
        this.formState = State.EMPTY;
        setStyleName(ValoTheme.FORMLAYOUT_LIGHT);
    }

    public enum State {COMPLETE, INCOMPLETE, EMPTY}


    public State getFormState() {
        return formState;
    }

    public void setFormState(State formState) {
        this.formState = formState;
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
        return formState == that.formState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formState);
    }
}
