package graphs.entities;

import java.util.Date;
import java.util.Objects;

/**
 * Created by zua on 02.10.16.
 */
public abstract class AliveEntity extends Entity {

    private History lifecycle;

    public AliveEntity() {
        this.lifecycle = new History();
        lifecycle.add(new CreatedAt(new Date()));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AliveEntity)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AliveEntity that = (AliveEntity) o;
        return Objects.equals(lifecycle, that.lifecycle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lifecycle);
    }
}
