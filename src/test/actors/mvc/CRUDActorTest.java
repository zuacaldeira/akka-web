package actors.mvc;

import actors.messages.crud.CreateMessage;
import actors.messages.crud.DeleteMessage;
import actors.messages.crud.ReadMessage;
import actors.messages.crud.UpdateMessage;
import graphs.entities.Account;
import graphs.entities.User;
import org.testng.annotations.DataProvider;

/**
 * Created by zua on 27.09.16.
 */
public abstract class CRUDActorTest extends MVCActorTest {

    @DataProvider(name = "create")
    public Object[][] create() {
        return new Object[][] {
                {new CreateMessage<User>(new User())},
                {new CreateMessage<Account>(new Account())},
        };
    }

    @DataProvider(name = "read")
    public Object[][] readMessage() {
        return new Object[][] {
                {new ReadMessage<Account>(1L)}
        };
    }


    @DataProvider(name = "update")
    public Object[][] update() {
        return new Object[][] {
                {new UpdateMessage<User>(new User("username", "Full Name 1"))},
                {new UpdateMessage<Account>(new Account("u", "p1"))},
        };
    }

    @DataProvider(name = "delete")
    public Object[][] delete() {
        return new Object[][] {
            {new DeleteMessage<User>(new User())},
            {new DeleteMessage<Account>(new Account())}
        };
    }

    
    public abstract void testCreate();
    public abstract void testRead();
    public abstract void testUpdate();
    public abstract void testDelete();


}