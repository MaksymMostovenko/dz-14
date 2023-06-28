package pageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageButtonsChrome extends AbstractPageObjectChrome {


    private final By CLICK_ME = By.xpath("//button[text()='Click Me']");
    private final By CLICK_ME_RESULT_TEXT = By.id("dynamicClickMessage");
    final String CLICK_ME_TEXT = "You have done a dynamic click";

    public PageButtonsChrome(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/buttons");
    }

    public PageButtonsChrome clickOnClickMe(){
        getElement(CLICK_ME).click();
        return this;
    }

    public void makeAssertion(){
        String actualTextString = driver.findElement(CLICK_ME_RESULT_TEXT).getText().trim();
        Assert.assertEquals(actualTextString, CLICK_ME_TEXT,
                CLICK_ME_TEXT +"\s not available. " + "Test2 failed");
    }
}
