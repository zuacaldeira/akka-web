package views.components;

import actors.messages.ControlMessage;
import actors.mvc.views.ActorView;
import actors.mvc.views.EntityForm;
import actors.mvc.views.StyleClassNames;
import com.vaadin.data.Property;
import com.vaadin.data.Validator;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import graphs.entities.Project;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zua on 03.10.16.
 */
public class ProjectForm extends EntityForm {
    private final TextField title;
    private final TextArea description;

    public ProjectForm() {
        super(Project.class);
        title = new TextField("Title:");
        title.setValidationVisible(true);
        title.setRequired(true);
        title.addValueChangeListener(this);
        title.setStyleName(StyleClassNames.TITLE.getStyle());
        description = new TextArea("Description");
        description.setValidationVisible(true);
        description.setRequired(true);
        description.addValueChangeListener(this);
        description.setStyleName(StyleClassNames.DESCRIPTION.getStyle());
        addComponents(title, description);
    }

    @Override
    public void validate() {
        title.validate();
        description.validate();
    }

    public TextArea getDescriptionField() {
        return description;
    }

    public TextField getTitleField() {
        return title;
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        try {
            validate();
            if(getParent() != null && getParent() instanceof ActorView) {
                ActorView parent = (ActorView) getParent();
                parent.getMailbox(ControlMessage.CREATE).setEnabled(true);
                parent.getMailbox(ControlMessage.CREATE).addStyleName(StyleClassNames.ENABLED.getStyle());
            }
        } catch (Validator.InvalidValueException ivx) {
            Logger.getLogger(ProjectForm.class.getSimpleName()).log(Level.INFO, ivx.getMessage(), ivx);
            if(getParent() != null && getParent() instanceof ActorView) {
                ((ActorView) getParent()).getMailbox(ControlMessage.CREATE).setEnabled(false);
                ((ActorView) getParent()).getMailbox(ControlMessage.CREATE).removeStyleName(StyleClassNames.ENABLED.getStyle());
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectForm)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ProjectForm that = (ProjectForm) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description);
    }
}
