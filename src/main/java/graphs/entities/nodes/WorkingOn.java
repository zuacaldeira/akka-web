package graphs.entities.nodes;

import graphs.entities.BinaryRelationship;
import graphs.entities.Project;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Created by zua on 02.10.16.
 */
@RelationshipEntity(type = "WORKING_ON")
public class WorkingOn extends BinaryRelationship {

    public WorkingOn(User user, Project project) {
        super(user, project);
    }

    public User getUser() {
        return (User) super.getStartNode();
    }

    public Project getProject() {
        return (Project) super.getEndNode();
    }
}
