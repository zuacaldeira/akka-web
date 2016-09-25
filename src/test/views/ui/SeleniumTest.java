package views.ui;

import actors.Neo4JDatabaseTest;
import actors.messages.AkkaMessage;
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
        selenium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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

    protected void clickLogin() {
        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE.getStyle())),
                AkkaMessage.LOGIN.name()
        ).click();
    }

    protected void clickCancel() {
        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE.getStyle())),
                AkkaMessage.CANCELLED.name()
        ).click();
    }

    protected void clickRegister() {
        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE.getStyle())),
                AkkaMessage.REGISTER.name()
        ).click();
    }

    protected WebElement getButton(final List<WebElement> elementsByClassName, final String name) {
        System.out.println("0 - Searching for style: " + name);
        for(WebElement e: elementsByClassName) {
            System.out.println("\t1 - Looking at: " + e.getText());
            if(e.getText().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(e.getText().toLowerCase())) {
                System.out.println("\t\t2 - Found mailbox: " + e.getText());
                return e;
            }
        }
        throw new RuntimeException("Maibox not found: " + name);
    }

    protected void fillUsername(String username) {
        fill(StyleClassNames.EMAIL.getStyle(), username);
    }

    protected void fillPassword(String password) {
        fill(StyleClassNames.PASSWORD.getStyle(), password);
    }

    protected void fillPasswordConfirmation(String password) {
        fill(StyleClassNames.PASSWORD_CONFIRMATION.getStyle(), password);
    }

    protected void fillFullname(String fullname) {
        fill(StyleClassNames.FULLNAME.getStyle(), fullname);
    }

    private void fill(String style, String username) {
        WebElement we = selenium.findElement(By.className(style));
        we.sendKeys(username, Keys.TAB);
    }


    protected void fillForm(String style, String... data) {
        WebElement form = selenium.findElement(By.className(style));
        form.sendKeys(data);
    }

    public boolean isUserPage() {
        return selenium.getCurrentUrl().equalsIgnoreCase(PAGE + "/user");
    }
}
