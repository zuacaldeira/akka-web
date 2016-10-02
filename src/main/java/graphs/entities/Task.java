package graphs.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 01.10.16.
 */
public class Task extends AliveEntity {
    private final String title;
    private final String description;
    private List<Task> subtasks;

    public Task(String title, String description) throws IllegalArgumentException {
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
}
