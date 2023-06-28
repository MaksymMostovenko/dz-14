package pageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.interactions.Actions;

public class AbstractPageObjectChrome {
    private static final int WAIT_TIME_SEC = 1;
    protected ChromeDriver driver;

    public AbstractPageObjectChrome(ChromeDriver driver) {
        this.driver = driver;
    }

    public void cleanup(){
        this.driver.close();
        this.driver.quit();
    }

    public WebElement getElement(By by, int waitForSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement getElement(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SEC))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> getElements(By by, int waitForSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> getElements(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void waitTillAppears(By by, int waitForSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitTillAppears(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SEC))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void clickElement(By by){
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.click(element).build().perform();
    }
}
