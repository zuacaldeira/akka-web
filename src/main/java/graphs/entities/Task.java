package graphs.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by zua on 01.10.16.
 */
public class Task extends AliveEntity {
    private final String title;
    private final String description;
    private List<Task> subtasks;

    public Task(String title, String description) {
        super();
        BusinessRules.validateTitle(title);
        BusinessRules.validateDescription(description);
        this.title = title;
        this.description = description;
        subtasks = new LinkedList<>();
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void addSubTask(Task subtask) {
        subtasks.add(subtask);
    }

    public boolean contains(Task subtask) {
        return subtasks.contains(subtask);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description);
    }
}
