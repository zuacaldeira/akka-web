package graphs.entities;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;

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
}
