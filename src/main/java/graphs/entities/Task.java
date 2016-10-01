package graphs.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 01.10.16.
 */
public class Task extends Entity {
    private final String title;
    private final String description;
    private List<Task> subtasks;
    private Lifecycle lifecycle;

    public Task(String title, String description) throws IllegalArgumentException {
        super();
        validateTitle(title);
        validateDescription(description);
        this.title = title;
        this.description = description;
        subtasks = new LinkedList<>();
        lifecycle = new Lifecycle();
        lifecycle.add(new CreatedAt(new Date()));
    }



    private void validateTitle(String title) {
        if(title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Invalid title");
        }
    }

    private void validateDescription(String description) {
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Invalid description");
        }
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

    public boolean created() {
        return lifecycle.contains(CreatedAt.class);
    }

    public boolean started() {
        return lifecycle.contains(StartedAt.class);
    }

    public void start() {
        lifecycle.add(new StartedAt(new Date()));
    }

    public void pause() {
        lifecycle.add(new PausedAt(new Date()));
    }

    public boolean paused() {
        return lifecycle.contains(PausedAt.class);
    }

    public void resume() {
        lifecycle.add(new ResumedAt(new Date()));
    }

    public boolean resumed() {
        return lifecycle.contains(ResumedAt.class);
    }

    public boolean finished() {
        return lifecycle.contains(FinishedAt.class);
    }

    public void finish() {
        lifecycle.add(new FinishedAt(new Date()));
    }
}
