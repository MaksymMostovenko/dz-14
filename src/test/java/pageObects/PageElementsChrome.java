package pageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageElementsChrome extends AbstractPageObjectChrome {
    private final By BUTTONS = By.id("item-4");

    public PageElementsChrome(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/elements");
    }

    public PageElementsChrome clickOnButtons(){
        getElement(BUTTONS).click();
        return this;
    }

    public void makeAssertion(){
        String actualUrl = driver.getCurrentUrl();
        String BUTTONS_URL = "https://demoqa.com/buttons";
        Assert.assertEquals(actualUrl, BUTTONS_URL, BUTTONS_URL +"\snot available. Test1 failed.");
    }
}
