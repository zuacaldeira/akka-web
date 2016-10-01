package graphs.entities;

import java.util.List;

/**
 * Created by zua on 01.10.16.
 */
public class TaskComposite extends Task {
    private List<Task> subtasks;

    public TaskComposite() throws IllegalArgumentException {
        super("T", "T");
    }


    public void add(Task subtask) {
        this.subtasks.add(subtask);
    }
}
