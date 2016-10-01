package graphs.entities;

import java.util.Date;

/**
 * Created by zua on 01.10.16.
 */
public class TimedEntity extends Entity implements Timed {
    private Date time;
    private Timed before;
    private Timed after;

    public TimedEntity(Date time) {
        this.time = time;
    }

    @Override
    public Date getTime() {
        return time;
    }

    @Override
    public Timed getBefore() {
        return before;
    }

    @Override
    public Timed getAfter() {
        return after;
    }

    @Override
    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public void setBefore(Timed before) {
        this.before = before;
    }

    @Override
    public void setAfter(Timed after) {
        this.after = after;
    }
}
