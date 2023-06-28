package webPageTests;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObects.PageButtonsChrome;
import pageObects.PageElementsChrome;
import pageObects.PageWebTables;

import java.security.PrivateKey;

public class TestWebPages {
    protected ChromeDriver driver;

    @BeforeTest
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mmostovenko\\OneDrive\\Documents\\chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterTest
    public void cleanup() {
        driver.close();
        driver.quit();
    }

    private final By BUTTONS = By.id("item-4");
    private final String BUTTONS_URL = "https://demoqa.com/buttons";
    @Test
    @Description("Try to click on button Buttons")
    public void test1() {
        PageElementsChrome buttonsPageObject = new PageElementsChrome(driver);
        WebElement element = buttonsPageObject.getElement(BUTTONS);
        element.click();
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, BUTTONS_URL, BUTTONS_URL+"\snot available. Test1 failed.");
    }

    private final By CLICK_ME = By.xpath("//button[text()='Click Me']");
    private final By CLICK_ME_RESULT_TEXT = By.id("dynamicClickMessage");

    @Test
    @Description("Test to click BUTTONS->ClickMe.")
    public void test2(){
        PageButtonsChrome buttonsPageObject = new PageButtonsChrome(driver);
        WebElement element = buttonsPageObject.getElement(CLICK_ME);
        element.click();
        element = buttonsPageObject.getElement(CLICK_ME_RESULT_TEXT);
        final String CLICK_ME_TEXT = "You have done a dynamic click";
        Assert.assertEquals(element.getText().trim(), CLICK_ME_TEXT,
                                            CLICK_ME_TEXT +"\s not available. " + "Test2 failed");
    }

    private final By BUTTON_ADD = By.xpath("//button[@id=\"addNewRecordButton\"]");
    private final By INPUT_FIRTS_NAME = By.xpath("//*[@id=\"firstName\"]");
    private final By INPUT_SECOND_NAME = By.xpath("//*[@id=\"lastName\"]");
    private final By INPUT_EMAIL = By.xpath("//*[@id=\"userEmail\"]");
    private final By INPUT_AGE = By.xpath("//*[@id=\"age\"]");
    private final By INPUT_SALARY = By.xpath("//*[@id=\"salary\"]");
    private final By INPUT_DEPARTMENT = By.xpath("//*[@id=\"department\"]");
    private final By BUTTON_SUBMIT = By.xpath("//button[@id=\"submit\"]");
    @Test
    @Description("Any")
    public void test3(){
        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.getElement(BUTTON_ADD).click();

        final String fistName = "First";
        pageWebTables.getElement(INPUT_FIRTS_NAME).sendKeys(fistName);

        final String lastName = "Last";
        pageWebTables.getElement(INPUT_SECOND_NAME).sendKeys(lastName);

        final String age = "42";
        pageWebTables.getElement(INPUT_AGE).sendKeys(age);

        final String email = "example@mail.com";
        pageWebTables.getElement(INPUT_EMAIL).sendKeys(email);

        final String salary = "100";
        pageWebTables.getElement(INPUT_SALARY).sendKeys(salary);

        final String department = "fancy dep";
        pageWebTables.getElement(INPUT_DEPARTMENT).sendKeys(department);

        pageWebTables.getElement(BUTTON_SUBMIT).click();

        WebElement lastRow = pageWebTables.getLastNonEmptyRow();
        String[] cells = lastRow.getText().trim().split("\n");

        Assert.assertEquals(cells[0], fistName, "Webtables input failed. First name not found.");
        Assert.assertEquals(cells[1], lastName, "Webtables input failed. Last name not found.");
        Assert.assertEquals(cells[2], age, "Webtables input failed. Age not found.");
        Assert.assertEquals(cells[3], email, "Webtables input failed. Email not found.");
        Assert.assertEquals(cells[4], salary, "Webtables input failed. Salsry name not found.");
        Assert.assertEquals(cells[5], department, "Webtables input failed. Department not found.");
    }

    private final By BUTTON_EDIT = By.xpath("//*[@id=\"edit-record-2\"]");
    @Test
    @Description("")
    public void test4(){
        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.getElement(BUTTON_EDIT).click();

        final String fistName = "First";
        pageWebTables.getElement(INPUT_FIRTS_NAME).clear();
        pageWebTables.getElement(INPUT_FIRTS_NAME).sendKeys(fistName);

        final String lastName = "Last";
        pageWebTables.getElement(INPUT_SECOND_NAME).clear();
        pageWebTables.getElement(INPUT_SECOND_NAME).sendKeys(lastName);

        final String age = "42";
        pageWebTables.getElement(INPUT_AGE).clear();
        pageWebTables.getElement(INPUT_AGE).sendKeys(age);

        final String email = "example@mail.com";
        pageWebTables.getElement(INPUT_EMAIL).clear();
        pageWebTables.getElement(INPUT_EMAIL).sendKeys(email);

        final String salary = "100";
        pageWebTables.getElement(INPUT_SALARY).clear();
        pageWebTables.getElement(INPUT_SALARY).sendKeys(salary);

        final String department = "fancy dep";
        pageWebTables.getElement(INPUT_DEPARTMENT).clear();
        pageWebTables.getElement(INPUT_DEPARTMENT).sendKeys(department);

        pageWebTables.getElement(BUTTON_SUBMIT).click();

        java.util.List<WebElement> rows = pageWebTables.getElements(By.cssSelector(".rt-tbody .rt-tr"));
        WebElement editedRow = rows.get(0);
        String[] cells = editedRow.getText().trim().split("\n");

        Assert.assertEquals(cells[0], fistName, "Webtables input failed. First name not found.");
        Assert.assertEquals(cells[1], lastName, "Webtables input failed. Last name not found.");
        Assert.assertEquals(cells[2], age, "Webtables input failed. Age not found.");
        Assert.assertEquals(cells[3], email, "Webtables input failed. Email not found.");
        Assert.assertEquals(cells[4], salary, "Webtables input failed. Salsry name not found.");
        Assert.assertEquals(cells[5], department, "Webtables input failed. Department not found.");
    }
}
