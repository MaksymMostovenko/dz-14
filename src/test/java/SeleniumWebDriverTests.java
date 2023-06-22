import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumWebDriverTests {

    private WebDriver driver;
    @BeforeTest
    public void testSetup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mmostovenko\\OneDrive\\Documents\\chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterTest
    public void whenDone(){
        driver.quit();
    }

    @Test
    public void testClickButton(){
        driver.get("https://demoqa.com/elements");

        // Find element and click Buttons
        WebElement buttons = driver.findElement(By.id("item-4"));
        buttons.click();

        // Find and click on button Click Me
        WebElement clickMe = driver.findElement(By.xpath("//button[contains(text(), 'Click Me')]"));
        clickMe.click();
     }
    private void fillField(WebDriver driver, Field field, String value) {
        WebElement element = driver.findElement(By.id(field.getId()));
        element.sendKeys(value);
    }

//    private void assertTableField(WebElement row, Field field, String expectedValue) {
//        WebElement cell = row.findElement(By.className("rt-td"));
//        String actualValue = cell.getText().trim();
//        assert actualValue.equals(expectedValue) : "Assertion failed for " + field.name() +
//                " field. Expected: " + expectedValue + ", Actual: " + actualValue;
//    }

    @Test
    public void testWebTables(){
        driver.get("https://demoqa.com/webtables");

        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        addButton.click();

        // Find and fill the fields
        fillField(driver, Field.FIRST_NAME, "John");
        fillField(driver, Field.LAST_NAME, "Doe");
        fillField(driver, Field.EMAIL, "johndoe@example.com");
        fillField(driver, Field.AGE, "30");
        fillField(driver, Field.SALARY, "50000");
        fillField(driver, Field.DEPARTMENT, "IT");

        // Submit the form
        WebElement submitButton = driver.findElement(By.id(Field.SUBMIT.getId()));
        submitButton.click();

        // Find all rows of the table
        WebElement table = driver.findElement(By.className("rt-tbody"));
        java.util.List<WebElement> rows = table.findElements(By.className("rt-tr-group"));

        // Find the last non-empty row
        WebElement lastRow = null;
        for (int i = rows.size() - 1; i >= 0; i--) {
            WebElement row = rows.get(i);
            WebElement data = row.findElement(By.className("rt-td"));
            String text = data.getText().trim();
            if (!text.isEmpty()) {
                lastRow = row;
                break;
            }
        }

        // Assert table fields with enum members
        String row = lastRow.getText().trim();
        String[]sRow = row.split("\n");
        Assert.assertEquals(sRow[0], "John", "First name assertion failed");
        Assert.assertEquals(sRow[1], "Doe", "Last name assertion failed");
        Assert.assertEquals(sRow[2], "30", "Age assertion failed");
        Assert.assertEquals(sRow[3], "johndoe@example.com", "Email assertion failed");
        Assert.assertEquals(sRow[4], "50000", "Salary assertion failed");
        Assert.assertEquals(sRow[5], "IT", "Department assertion failed");
    }
}

enum Field {
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    EMAIL("userEmail"),
    AGE("age"),
    SALARY("salary"),
    DEPARTMENT("department"),
    SUBMIT("submit");

    private String id;

    Field(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
