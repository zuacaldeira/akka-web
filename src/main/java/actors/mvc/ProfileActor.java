package actors.mvc;

/**
 * Created by zua on 26.09.16.
 */
public class ProfileActor extends MVCActor {
    @Override
    protected void leaveAkkariaOnSuccess() {
        getUi().leave(super.getParentActor());
    }
}
