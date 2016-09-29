package actors.messages.world;

import views.ui.AkkaUI;

/**
 * Created by zua on 23.09.16.
 */
public class LeaveAkkaria {
    private AkkaUI ui;
    private final Object cause;

    public LeaveAkkaria(AkkaUI ui, Object cause) {
        this.ui = ui;
        this.cause = cause;
    }

    public AkkaUI getUi() {
        return ui;
    }

    public Object getCause() {
        return cause;
    }

}
