package graphs.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 26.09.16.
 */
public class Project extends AliveEntity {
    private final String title;
    private final String description;
    private Project parent;
    private List<Project> subprojects;

    public Project(String title, String description) {
        super();
        BusinessRules.validateTitle(title);
        BusinessRules.validateDescription(description);
        this.title = title;
        this.description = description;
        subprojects = new LinkedList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Project getParent() {
        return parent;
    }

    public void setParent(Project parent) {
        this.parent = parent;
    }

    public List<Project> getSubprojects() {
        return subprojects;
    }

    public void addSubProject(Project sub) {
        subprojects.add(sub);
        sub.setParent(this);
    }
}
