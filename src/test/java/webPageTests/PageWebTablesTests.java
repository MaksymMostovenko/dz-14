package webPageTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObects.PageWebTables;

public class PageWebTablesTests {
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
    @Description("Verify that user could add new data in the table.")
    public void AddDataTest(){
        final String firstName = "First";
        final String lastName = "Last";
        final String age = "42";
        final String email = "example@mail.com";
        final String salary = "100";
        final String department = "fancy dep";

        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.clickAddButton()
                .addNewRecord(firstName, lastName, age, email, salary, department)
                .assertAddDataTable(firstName, lastName, age, email, salary, department);
   }


    @Test
    @Description("Verify that user could edit existed data in the table.")
    public void EditDataTest(){
        final String firstName = "First";
        final String lastName = "Last";
        final String age = "42";
        final String email = "example@mail.com";
        final String salary = "100";
        final String department = "fancy dep";

        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.clickEditButton()
                .editRecord(firstName, lastName, age, email, salary, department)
                .assertEditDataTable(firstName, lastName, age, email, salary, department);
    }
}
