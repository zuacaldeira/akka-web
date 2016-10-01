package graphs.entities;

import java.util.Date;

/**
 * Created by zua on 01.10.16.
 */
public interface Timed {
    Date getTime();
    Timed getBefore();
    Timed getAfter();
    void setTime(Date time);
    void setBefore(Timed time);
    void setAfter(Timed time);
}
