package pageObects;

import org.openqa.selenium.chrome.ChromeDriver;

public class PageElementsChrome extends AbstractPageObjectChrome {
    public PageElementsChrome(ChromeDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/elements");
    }


}
