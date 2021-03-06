package actors.messages;

import views.ui.AkkaUI;

/**
 * Created by zua on 23.09.16.
 */
public class LeaveAkkaria {
    private AkkaUI ui;
    private final AkkaMessage status;

    public LeaveAkkaria(AkkaUI ui, AkkaMessage status) {
        this.ui = ui;
        this.status = status;
    }

    public AkkaUI getUi() {
        return ui;
    }

    public AkkaMessage getStatus() {
        return status;
    }

}
