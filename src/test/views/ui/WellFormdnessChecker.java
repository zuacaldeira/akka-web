package views.ui;

import org.openqa.selenium.WebElement;

/**
 * Created by zua on 06.09.16.
 */
public interface WellFormdnessChecker {

    public void checkWellFormed(WebElement element) throws MalFormedUIException;
}
