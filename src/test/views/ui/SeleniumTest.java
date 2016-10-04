package views.ui;

import actors.Neo4JDatabaseTest;
import actors.messages.ControlMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import actors.mvc.views.StyleClassNames;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zua on 04.09.16.
 */
public abstract class SeleniumTest extends Neo4JDatabaseTest {
    private FirefoxDriver selenium;
    private static final String PROTOCOL = "http://";
    private static final String ADDRESS = "localhost:8080/";
    private static final String PAGE = PROTOCOL + ADDRESS;

    private void getWelcomePage() {
        selenium.get(PAGE);
        selenium.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        selenium.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public synchronized void  start() {
        System.setProperty("webdriver.gecko.driver", "src/test/tools/geckodriver");
        selenium = new FirefoxDriver();
        getWelcomePage();
    }

    public void stop(){
        selenium.quit();
    }

    protected boolean clickLogin() {
        String name = ControlMessage.LOGIN.name();
        return clickButtonNamed(name);
    }

    protected boolean clickCancel() {
        String name = ControlMessage.CANCELLED.name();
        return clickButtonNamed(name);
    }

    protected boolean clickRegister() {
        String name = ControlMessage.REGISTER.name();
        return clickButtonNamed(name);
    }

    protected boolean clickProject() {
        String name = ControlMessage.PROJECT.name();
        return clickButtonNamed(name);
    }

    public boolean clickCreate() {
        String name = ControlMessage.CREATE.name();
        return clickButtonNamed(name);
    }

    private boolean clickButtonNamed(String name) {
        WebElement button = getButton(name);
        if(button != null) {
            return click(button, name);
        } else {
            return false;
        }

    }


    private boolean click(WebElement button, String name) {
        try {
            button.click();
            selenium.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            System.out.println("\t\t\t3 - Clicked mailbox: " + name);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    protected WebElement getButton(final String name) {
        List<WebElement> elementsByClassName = selenium.findElements(By.className(StyleClassNames.MESSAGE.getStyle()));
        for(WebElement e: elementsByClassName) {
            System.out.println("\t1 - Looking at: " + name);
            if(e.getText().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(e.getText().toLowerCase())) {
                System.out.println("\t\t2 - Found mailbox: " + name);
                return e;
            }
        }
        throw new RuntimeException("Maibox not found: " + name);
    }

    protected boolean fillUsername(String username) {
        return fill(StyleClassNames.EMAIL.getStyle(), username);
    }

    protected boolean fillPassword(String password) {
        return fill(StyleClassNames.PASSWORD.getStyle(), password);
    }

    protected boolean fillPasswordConfirmation(String password) {
        return fill(StyleClassNames.PASSWORD_CONFIRMATION.getStyle(), password);
    }

    protected boolean fillFullname(String fullname) {
        return fill(StyleClassNames.FULLNAME.getStyle(), fullname);
    }

    public boolean fillTitle(String title) {
        return fill(StyleClassNames.TITLE.getStyle(), title);
    }

    public boolean fillDescription(String description) {
        return fill(StyleClassNames.DESCRIPTION.getStyle(), description);
    }


    private boolean fill(String style, String data) {
        try {
            selenium.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement we = selenium.findElement(By.className(style));
            if(we != null) {
                we.sendKeys(data, Keys.ENTER);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean inUserPage() {
        try {
            selenium.get(PAGE + "/user");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public boolean hasViewNamed(String name) {
        return selenium.findElement(By.className(name)) != null;
    }

}
