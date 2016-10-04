package actors.messages.world;

import views.ui.AkkaUI;

/**
 * Created by zua on 23.09.16.
 */
public class LeaveAkkaria {
    private AkkaUI ui;
    private final Object result;

    public LeaveAkkaria(AkkaUI ui, Object result) {
        this.ui = ui;
        this.result = result;
    }

    public AkkaUI getUi() {
        return ui;
    }

    public Object getResult() {
        return result;
    }

}
