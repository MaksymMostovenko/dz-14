package pageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageElements extends AbstractPageObjecе {
    private final By BUTTONS = By.id("item-4");

    public PageElements(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/elements");
    }

    public PageElements clickOnButtons(){
        getElement(BUTTONS).click();
//        scrollToElement(getElement(BUTTONS)).click();
        return this;
    }

    public void assertBlickOnButtons(){
        String actualUrl = driver.getCurrentUrl();
        String BUTTONS_URL = "https://demoqa.com/buttons";
        Assert.assertEquals(actualUrl, BUTTONS_URL, BUTTONS_URL +"\snot available. Test1 failed.");
    }

    private WebElement scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
}
