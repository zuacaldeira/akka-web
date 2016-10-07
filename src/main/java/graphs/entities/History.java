package graphs.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by zua on 01.10.16.
 */
public class History extends Entity {
    private List<Timed> lifecycle;

    public History() {
        this.lifecycle = new LinkedList<>();
    }

    public void add(Timed timed) {
        if(!lifecycle.isEmpty()) {
            Timed last = getLast();
            timed.setBefore(last);
            last.setAfter(timed);
        }
        lifecycle.add(timed);
    }

    private Timed getLast() {
        if(!lifecycle.isEmpty()) {
            return lifecycle.get(lifecycle.size() -1);
        }
        return null;
    }

    public boolean contains(Class<? extends Timed> timedClass) {
        for(Timed t: lifecycle) {
            if(t.getClass() == timedClass) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof History)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        History history = (History) o;
        return Objects.equals(lifecycle, history.lifecycle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lifecycle);
    }
}
