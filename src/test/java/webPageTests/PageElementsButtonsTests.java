package webPageTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObects.PageButtons;
import pageObects.PageElements;

public class PageElementsButtonsTests {
    protected WebDriver driver;

    @BeforeTest
    public void testSetup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void cleanup() {
        driver.close();
        driver.quit();
    }

    @Test
    @Description("Try to click on button Buttons")
    public void ClickButtonsTest() {
        PageElements elementsPageObject = new PageElements(driver);
        elementsPageObject.clickOnButtons()
                          .assertBlickOnButtons();
    }

    @Test
    @Description("Test to click BUTTONS->ClickMe.")
    public void ClickMeTest(){
        PageButtons buttonsPageObject = new PageButtons(driver);
        buttonsPageObject.clickOnClickMe()
                        .assertClickMe();
    }
}
