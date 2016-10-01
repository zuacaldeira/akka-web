package graphs.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zua on 01.10.16.
 */
public class Lifecycle {
    private List<Timed> lifecycle;

    public Lifecycle() {
        this.lifecycle = new LinkedList<>();
    }

    public void add(Timed timed) {
        if(lifecycle.size() >= 1) {
            Timed last = getLast();
            timed.setBefore(last);
            last.setAfter(timed);
        }
        lifecycle.add(timed);
    }

    private Timed getLast() {
        if(lifecycle.size() >= 1) {
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
}
