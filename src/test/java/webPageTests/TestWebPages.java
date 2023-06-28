package webPageTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObects.PageButtonsChrome;
import pageObects.PageElementsChrome;
import pageObects.PageWebTables;

public class TestWebPages {
    protected WebDriver driver;

    @BeforeTest
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mmostovenko\\OneDrive\\Documents\\chromedriver_win32/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void cleanup() {
        driver.close();
        driver.quit();
    }

    @Test
    @Description("Try to click on button Buttons")
    public void test1() {
        PageElementsChrome elementsPageObject = new PageElementsChrome(driver);
        elementsPageObject.clickOnButtons()
                          .makeAssertion();
    }

    @Test
    @Description("Test to click BUTTONS->ClickMe.")
    public void test2(){
        PageButtonsChrome buttonsPageObject = new PageButtonsChrome(driver);
        buttonsPageObject.clickOnClickMe()
                        .makeAssertion();
    }

    @Test
    @Description("Any")
    public void test3(){
        final String firstName = "First";
        final String lastName = "Last";
        final String age = "42";
        final String email = "example@mail.com";
        final String salary = "100";
        final String department = "fancy dep";

        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.clickAddButton()
                .addNewRecord(firstName, lastName, age, email, salary, department)
                .makeAssertionAdd(firstName, lastName, age, email, salary, department);
   }


    @Test
    @Description("")
    public void test4(){
        final String firstName = "First";
        final String lastName = "Last";
        final String age = "42";
        final String email = "example@mail.com";
        final String salary = "100";
        final String department = "fancy dep";

        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.clickEditButton()
                .editRecord(firstName, lastName, age, email, salary, department)
                .makeAssertionEdit(firstName, lastName, age, email, salary, department);
    }
}
