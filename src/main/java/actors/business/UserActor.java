package actors.business;

/**
 * Created by zua on 16.09.16.
 */
public class UserActor extends BusinessActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        unhandled(message);
    }
}
