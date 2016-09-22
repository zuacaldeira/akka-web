package views.ui;

import actors.messages.AkkaMessage;
import graphs.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import views.actors.StyleClassNames;

import java.util.Collections;
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
        selenium.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void deleteDatabase() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        if(session != null) {
            session.query("MATCH (n) DETACH DELETE n", Collections.EMPTY_MAP);
        }
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
                AkkaMessage.CANCEL.name()
        ).click();
    }

    protected void clickRegister() {

        getButton(
                selenium.findElements(By.className(StyleClassNames.MESSAGE.getStyle())),
                AkkaMessage.REGISTER.name()
        ).click();
    }

    protected WebElement getButton(final List<WebElement> elementsByClassName, final String name) {
        for(WebElement e: elementsByClassName) {
            System.out.println("1 - Found mailbox: " + e.getText());
            if(e.getText().toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().contains(e.getText().toLowerCase())) {
                System.out.println("2 - Found mailbox: " + e.getText());
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
        we.sendKeys(username);
    }


    protected void fillForm(String style, String... data) {
        WebElement form = selenium.findElement(By.className(style));
        form.sendKeys(data);
    }

    public boolean isUserPage() {
        return selenium.getCurrentUrl().equalsIgnoreCase(PAGE + "/user");
    }
}
