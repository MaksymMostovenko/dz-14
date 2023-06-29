package pageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class AbstractPageObjecе {
    private static final int WAIT_TIME_SEC = 1;
    protected WebDriver driver;

    public AbstractPageObjecе(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SEC))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> getElements(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}
