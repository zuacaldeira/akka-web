package actors.mvc.views;

import graphs.entities.Entity;
import views.components.MyForm;


/**
 * Created by zua on 03.10.16.
 */
public abstract class EntityForm<T extends Entity> extends MyForm  {

    private final Class<T> entityClass;

    public EntityForm(Class<T> aClass) {
        entityClass = aClass;
    }

}
