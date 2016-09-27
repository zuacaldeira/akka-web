package actors.messages.world;

import actors.messages.ControlMessage;
import views.ui.AkkaUI;

/**
 * Created by zua on 23.09.16.
 */
public class LeaveAkkaria {
    private AkkaUI ui;
    private final ControlMessage status;

    public LeaveAkkaria(AkkaUI ui, ControlMessage status) {
        this.ui = ui;
        this.status = status;
    }

    public AkkaUI getUi() {
        return ui;
    }

    public ControlMessage getStatus() {
        return status;
    }

}
