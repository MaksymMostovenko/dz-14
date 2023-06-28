package pageObects;

import org.openqa.selenium.chrome.ChromeDriver;

public class PageButtonsChrome extends AbstractPageObjectChrome {
    public PageButtonsChrome(ChromeDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/buttons");
    }


}
