package pageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageWebTables extends AbstractPageObjectChrome {
    private final By BUTTON_ADD = By.xpath("//button[@id=\"addNewRecordButton\"]");
    private final By INPUT_FIRST_NAME = By.xpath("//*[@id=\"firstName\"]");
    private final By INPUT_SECOND_NAME = By.xpath("//*[@id=\"lastName\"]");
    private final By INPUT_EMAIL = By.xpath("//*[@id=\"userEmail\"]");
    private final By INPUT_AGE = By.xpath("//*[@id=\"age\"]");
    private final By INPUT_SALARY = By.xpath("//*[@id=\"salary\"]");
    private final By INPUT_DEPARTMENT = By.xpath("//*[@id=\"department\"]");
    private final By BUTTON_SUBMIT = By.xpath("//button[@id=\"submit\"]");
    private final By BUTTON_EDIT = By.xpath("//*[@id=\"edit-record-1\"]");
    private final By EDITED_ROW = By.cssSelector(".rt-tbody .rt-tr");
    public PageWebTables(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/webtables");
    }

    public PageWebTables addNewRecord(String firstName, String lastName, String age,
                                      String email, String salary, String department){
        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.getElement(BUTTON_ADD).click();
        pageWebTables.getElement(INPUT_FIRST_NAME).sendKeys(firstName);
        pageWebTables.getElement(INPUT_SECOND_NAME).sendKeys(lastName);
        pageWebTables.getElement(INPUT_AGE).sendKeys(age);
        pageWebTables.getElement(INPUT_EMAIL).sendKeys(email);
        pageWebTables.getElement(INPUT_SALARY).sendKeys(salary);
        pageWebTables.getElement(INPUT_DEPARTMENT).sendKeys(department);
        pageWebTables.getElement(BUTTON_SUBMIT).click();
        return this;
    }

    public PageWebTables editRecord(String firstName, String lastName, String age,
                                    String email, String salary, String department){
        //PageWebTables pageWebTables = new PageWebTables(driver);
        this.editRecord(INPUT_FIRST_NAME, firstName);
        this.editRecord(INPUT_SECOND_NAME,lastName);
        this.editRecord(INPUT_AGE, age);
        this.editRecord(INPUT_EMAIL, email);
        this.editRecord(INPUT_SALARY, salary);
        this.editRecord(INPUT_DEPARTMENT, department);
        this.getElement(BUTTON_SUBMIT).click();
        return this;
    }

    private void editRecord(By by, String data){
        this.getElement(by).clear();
        this.getElement(by).sendKeys(data);
    }

    public PageWebTables clickAddButton(){
        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.getElement(BUTTON_ADD).click();
        return this;
    }

    public PageWebTables clickEditButton(){
        PageWebTables pageWebTables = new PageWebTables(driver);
        pageWebTables.getElement(BUTTON_EDIT).click();
        return this;
    }

    public void makeAssertionAdd(String firstName, String lastName, String age, String email, String salary, String department){
        String[] cells = this.getLastNonEmptyRow()
                                    .getText()
                                    .trim()
                                    .split("\n");
        Assert.assertEquals(cells[0], firstName, "Webtables input failed. First name not found.");
        Assert.assertEquals(cells[1], lastName, "Webtables input failed. Last name not found.");
        Assert.assertEquals(cells[2], age, "Webtables input failed. Age not found.");
        Assert.assertEquals(cells[3], email, "Webtables input failed. Email not found.");
        Assert.assertEquals(cells[4], salary, "Webtables input failed. Salsry name not found.");
        Assert.assertEquals(cells[5], department, "Webtables input failed. Department not found.");
    }

    public void makeAssertionEdit(String firstName, String lastName, String age, String email, String salary, String department){
        String[] cells = this.getElements(EDITED_ROW)
                .get(0)
                .getText()
                .trim()
                .split("\n");

        Assert.assertEquals(cells[0], firstName, "Webtables input failed. First name not found.");
        Assert.assertEquals(cells[1], lastName, "Webtables input failed. Last name not found.");
        Assert.assertEquals(cells[2], age, "Webtables input failed. Age not found.");
        Assert.assertEquals(cells[3], email, "Webtables input failed. Email not found.");
        Assert.assertEquals(cells[4], salary, "Webtables input failed. Salsry name not found.");
        Assert.assertEquals(cells[5], department, "Webtables input failed. Department not found.");
    }


    private WebElement getLastNonEmptyRow(){
        // Find all rows in the web table
        java.util.List<WebElement> rows = driver.findElements(By.cssSelector(".rt-tbody .rt-tr"));

        // Iterate over the rows in reverse order and find the last non-empty row
        for (int i = rows.size() - 1; i >= 0; i--) {
            WebElement row = rows.get(i);

            // Check if any cell in the row has non-empty text
            java.util.List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));
            boolean isEmptyRow = true;
            for (WebElement cell : cells) {
                if (!cell.getText().trim().isEmpty()) {
                    isEmptyRow = false;
                    break;
                }
            }

            if (!isEmptyRow) {
                return row;
            }
        }
        return null; // Return null if no non-empty row is found
    }
}
