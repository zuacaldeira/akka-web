package views.ui;

import actors.messages.AkkaMessages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import views.actors.StyleClassNames;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zua on 04.09.16.
 */
public abstract class SeleniumTest {
    private FirefoxDriver selenium;
    private static final String PROTOCOL = "http://";
    private static final String ADDRESS = "localhost:8080/";
    private static final String PAGE = PROTOCOL + ADDRESS;

    private void getWelcomePage() {
        selenium.get(PAGE);
    }

    public synchronized void  start() {
        System.setProperty("webdriver.gecko.driver", "src/test/tools/geckodriver");
        selenium = new FirefoxDriver();
        selenium.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        getWelcomePage();
        wait5Seconds();
    }

    private void wait5Seconds() {
        try {
            wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void stop(){
        selenium.quit();
    }

    protected void clickLogin() {
        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE)),
                AkkaMessages.LOGIN
        ).click();
        wait5Seconds();
    }

    protected void clickCancel() {
        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE)),
                AkkaMessages.CANCEL
        ).click();
        wait5Seconds();
    }

    protected void clickRegister() {
        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE)),
                AkkaMessages.REGISTER
        ).click();
        wait5Seconds();
    }

    protected void clickSend() {
        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE)),
                AkkaMessages.SEND
        ).click();
        wait5Seconds();
    }

    protected WebElement getButton(final List<WebElement> elementsByClassName, final String name) {
        for(WebElement e: elementsByClassName) {
            if(e.getText().contains(name) || name.contains(e.getText())) {
                System.out.println("Found mailbox: " + e.getText());
                return e;
            }
        }
        throw new RuntimeException("Maibox not found: " + name);
    }

    protected void fillUsername(String username) {
        fill(StyleClassNames.EMAIL, username);
    }

    protected void fillPassword(String password) {
        fill(StyleClassNames.PASSWORD, password);
    }

    protected void fillPasswordConfirmation(String password) {
        fill(StyleClassNames.PASSWORD_CONFIRMATION, password);
    }

    protected void fillFullname(String fullname) {
        fill(StyleClassNames.FULLNAME, fullname);
    }

    private void fill(String style, String username) {
        selenium.findElement(By.className(style)).sendKeys(username);
    }


    protected void fillForm(String style, String... data) {
        WebElement form = selenium.findElement(By.className(style));
        form.sendKeys(data);
    }

    public boolean isUserPage() {
        return selenium.getCurrentUrl().equalsIgnoreCase(PAGE + "/user");
    }
}
