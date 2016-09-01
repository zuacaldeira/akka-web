package graphs.entities;

import org.neo4j.ogm.annotation.GraphId;

import java.util.Objects;

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
        if (!(o instanceof Entity)) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
