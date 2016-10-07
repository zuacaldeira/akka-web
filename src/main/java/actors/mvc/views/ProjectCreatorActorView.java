package actors.mvc.views;

import actors.messages.ControlMessage;
import actors.messages.crud.CreateMessage;
import actors.messages.world.LeaveAkkaria;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import graphs.entities.Project;
import views.components.ProjectForm;

import java.util.Objects;

/**
 * Created by zua on 03.10.16.
 */
public class ProjectCreatorActorView extends ActorView {

    private ProjectForm form;
    private Project project;

    public ProjectCreatorActorView() {
        setActorName("Kreakktor");
        addMailbox(ControlMessage.CANCELLED, true);
        addMailbox(ControlMessage.CREATE, false);
        setStyleName(StyleClassNames.PROJECT_CREATOR.getStyle());
    }

    @Override
    protected Component createActorContent() {
        form = new ProjectForm();
        return form;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Notification.show("Clicked " + event.getButton());
        if(event.getButton().getCaption().equals(ControlMessage.CANCELLED.name())) {
            getUI().getMVCActor().tell(new LeaveAkkaria(getUI(), ControlMessage.CANCELLED), getUI().getMVCActor());
        }
        else if(event.getButton().getCaption().equals(ControlMessage.CREATE.name())) {
            form.validate();
            project = getProjectFromForm();
            getUI().getMVCActor().tell(new CreateMessage<Project>(project), getUI().getMVCActor());
        }
    }

    private Project getProjectFromForm() {
        return new Project(form.getTitleField().getValue(), form.getDescriptionField().getValue());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectCreatorActorView)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ProjectCreatorActorView that = (ProjectCreatorActorView) o;
        return Objects.equals(form, that.form) &&
                Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), form, project);
    }
}
