package actors.mvc.views;

import graphs.entities.Entity;
import views.components.MyForm;

import java.util.Objects;


/**
 * Created by zua on 03.10.16.
 */
public abstract class EntityForm<T extends Entity> extends MyForm  {

    private final Class<T> entityClass;

    public EntityForm(Class<T> aClass) {
        entityClass = aClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityForm)) {
            return false;
        }
        EntityForm<?> that = (EntityForm<?>) o;
        return Objects.equals(entityClass, that.entityClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), entityClass);
    }
}
