import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseBrowserFunctions {

    public void waitAndClick(ChromeDriver driver, By by) {
        System.out.println("Clicks on element");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitAndWrite(ChromeDriver driver, By by, String text) {
        System.out.println("Writes text in element " + text);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
    }

    public void waitClearInputAndWrite(ChromeDriver driver, By by, String text) {
        System.out.println("Clears text in element and writes new " + text);
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).clear();
        driver.findElement(by).sendKeys(text);
    }

    public void waitElement(ChromeDriver driver, By by) {
        System.out.println("Waits element");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForPageReadyState(ChromeDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
        {
            assert wd != null;
            return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
        });
    }

    public void clickOnLastElem(ChromeDriver driver, By by) {
        driver.findElements(by).get(driver.findElements(by).size() - 1).click();
    }

    public void waitForURL(ChromeDriver driver, String url) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.urlContains(url));
    }

    public void waitForElements(ChromeDriver driver, By by) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
    }
}
