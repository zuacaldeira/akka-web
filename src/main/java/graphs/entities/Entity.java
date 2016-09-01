package graphs.entities;

import org.neo4j.ogm.annotation.GraphId;

/**
 * Base class of neo4j nodes hierarchy.
 *
 * This class specifies an {@code id} that serves as identifier in the
 * underlying graph database.
 *
 * Created by zua on 30.08.16.
 */
public abstract class Entity {

    @GraphId
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || id == null || getClass() != o.getClass()) {
            return false;
        }

        Entity entity = (Entity) o;
        return id.equals(entity.id);

    }

    @Override
    public int hashCode() {
        return (id == null) ? -1 : id.hashCode();
    }
}
