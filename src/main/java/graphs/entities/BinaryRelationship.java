package graphs.entities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Objects;

/**
 * Created by zua on 02.10.16.
 */
public abstract class BinaryRelationship extends Entity {

    @StartNode
    private Entity startNode;

    @EndNode
    private Entity endNode;


    public BinaryRelationship(Entity startNode, Entity endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    protected Entity getStartNode() {
        return startNode;
    }

    protected Entity getEndNode() {
        return endNode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BinaryRelationship)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BinaryRelationship that = (BinaryRelationship) o;
        return Objects.equals(startNode, that.startNode) &&
                Objects.equals(endNode, that.endNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startNode, endNode);
    }
}
