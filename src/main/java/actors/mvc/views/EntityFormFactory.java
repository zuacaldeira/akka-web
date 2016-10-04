package actors.mvc.views;

import graphs.entities.Entity;
import graphs.entities.Project;
import views.components.ProjectForm;

/**
 * Created by zua on 03.10.16.
 */
public class EntityFormFactory {

    private static EntityFormFactory factory = new EntityFormFactory();

    private EntityFormFactory() {
    }



    public static EntityFormFactory getInstance() {
        return factory;
    }

    public EntityForm getComponentForField(Class<? extends Entity> entity) {
        if (entity == Project.class) {
            return new ProjectForm();
        }
        return null;
    }
}
