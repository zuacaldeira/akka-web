package actors.messages;

import views.ui.AkkaUI;

/**
 * Created by zua on 23.09.16.
 */
public class EnterAkkaria {

    private final AkkaUI ui;

    public EnterAkkaria(AkkaUI ui) {
        this.ui = ui;
    }


    public AkkaUI getUi() {
        return ui;
    }
}
