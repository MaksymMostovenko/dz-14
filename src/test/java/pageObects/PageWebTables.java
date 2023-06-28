package pageObects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageWebTables extends AbstractPageObjectChrome {
    public PageWebTables(ChromeDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/webtables");
    }

    public WebElement getLastNonEmptyRow(){
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
