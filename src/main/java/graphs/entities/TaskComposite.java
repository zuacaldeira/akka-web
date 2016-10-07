package graphs.entities;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskComposite)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TaskComposite that = (TaskComposite) o;
        return Objects.equals(subtasks, that.subtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtasks);
    }
}
