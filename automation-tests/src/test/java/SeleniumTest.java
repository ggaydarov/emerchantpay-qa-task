import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumExtension.class)
class SeleniumTest {

    BaseBrowserFunctions baseBrowserFunctions = new BaseBrowserFunctions();

    @Test
    void verifyHomePage(ChromeDriver driver) {
        driver.manage().deleteAllCookies();
        System.out.println("Open url home page");
        driver.get(Constants.baseURL);

        System.out.println("Verifies title");
        assertEquals("Welcome", driver.getTitle());
    }

    @Test
    void verifyLoginPage(ChromeDriver driver) {
        driver.manage().deleteAllCookies();
        System.out.println("Open login page");
        driver.get(Constants.signInURL);

        System.out.println("Verifies title");
        assertEquals("Log in with your account", driver.getTitle());
    }

    @Test
    void verifyRegistrationPage(ChromeDriver driver) {
        driver.manage().deleteAllCookies();
        System.out.println("Open register page");
        driver.get(Constants.registrationURL);

        System.out.println("Verifies title");
        assertEquals("Create an account", driver.getTitle());
    }

    @Test
    void verifyUserRegistrationAndLogIn(ChromeDriver driver) {
        driver.manage().deleteAllCookies();
        // Generate random user
        String ts = String.format("%06d", new Random().nextInt(99999));

        System.out.println("Open register page");
        driver.get(Constants.registrationURL);

        baseBrowserFunctions.waitAndWrite(driver, Constants.username , ts);
        baseBrowserFunctions.waitAndWrite(driver, Constants.password , ts);
        baseBrowserFunctions.waitAndClick(driver, Constants.submit);

        driver.get(Constants.signInURL);
        waitForLoginPage(driver);
        // verifies customer is registered and redirected to log in
        assertEquals("Log in with your account", driver.getTitle());
        baseBrowserFunctions.waitElement(driver, Constants.submit);
        baseBrowserFunctions.waitElement(driver, Constants.regLink);

        baseBrowserFunctions.waitAndWrite(driver, Constants.username , ts);
        baseBrowserFunctions.waitAndWrite(driver, Constants.password , ts);
        baseBrowserFunctions.waitAndClick(driver, Constants.submit);
        baseBrowserFunctions.waitForPageReadyState(driver);

        //verifies customer is on hr page
        baseBrowserFunctions.waitElement(driver, Constants.addEmployeeLink);
        assertTrue(driver.findElement(By.tagName("body")).getText().contains("Welcome HRs"));
    }

    @Test
    void verifyHrCanAddEditAndDeleteEmployee(ChromeDriver driver) {
        driver.manage().deleteAllCookies();
        // Generate random user
        String ts = String.format("%06d", new Random().nextInt(99999));

        System.out.println("Open register page");
        driver.get(Constants.registrationURL);
        baseBrowserFunctions.waitForPageReadyState(driver);

        // Register
        baseBrowserFunctions.waitAndWrite(driver, Constants.username, ts);
        baseBrowserFunctions.waitAndWrite(driver, Constants.password, ts);
        baseBrowserFunctions.waitAndClick(driver, Constants.submit);

        driver.get(Constants.signInURL);
        waitForLoginPage(driver);
        // verifies customer is registered and redirected to log in
        assertEquals("Log in with your account", driver.getTitle());

        // Login
        baseBrowserFunctions.waitAndWrite(driver, Constants.username, ts);
        baseBrowserFunctions.waitAndWrite(driver, Constants.password, ts);
        baseBrowserFunctions.waitAndClick(driver, Constants.submit);

        waitForHRPage(driver);
        assertEquals("Manage employees", driver.getTitle());

        baseBrowserFunctions.waitAndClick(driver, Constants.addEmployeeLink);
        //verifies customer is add employee
        baseBrowserFunctions.waitForPageReadyState(driver);
        assertEquals("Add employee", driver.getTitle());

        addNewEmployee(driver, ts);
        waitForHRPage(driver);

        String pageText = driver.findElement(By.tagName("body")).getText();

        //verifies that new employee is saved
        assertTrue(pageText.contains(ts + "fname1"));
        assertTrue(pageText.contains(ts + "lname1"));
        assertTrue(pageText.contains(ts + "address1"));
        assertTrue(pageText.contains(ts + "country1"));
        assertTrue(pageText.contains(ts + "city1"));
        assertTrue(pageText.contains(ts + "phone1"));
        assertTrue(pageText.contains(ts + "salary1"));

        baseBrowserFunctions.clickOnLastElem(driver, Constants.editEmployeeLink);
        baseBrowserFunctions.waitForPageReadyState(driver);
        baseBrowserFunctions.waitForURL(driver, "edit");

        baseBrowserFunctions.waitElement(driver, Constants.address);
        baseBrowserFunctions.waitClearInputAndWrite(driver, Constants.address, ts + "address2");
        baseBrowserFunctions.waitAndClick(driver, Constants.submit);

        baseBrowserFunctions.waitForElements(driver, Constants.hrTD);
        waitForHRPage(driver);

        pageText = driver.findElement(By.tagName("body")).getText();

        //verifies that new employee is edited
        assertTrue(pageText.contains(ts + "address2"));
        assertFalse(pageText.contains(ts + "address1"));

        baseBrowserFunctions.clickOnLastElem(driver, Constants.deleteEmployeeLink);
        waitForHRPage(driver);

        //Verified that the employee is removed
        pageText = driver.findElement(By.tagName("body")).getText();
        assertFalse(pageText.contains(ts + "address1"));
        assertFalse(pageText.contains(ts + "address2"));
    }

    private void waitForHRPage(ChromeDriver driver) {
        baseBrowserFunctions.waitForURL(driver, Constants.hrURL);
        baseBrowserFunctions.waitForPageReadyState(driver);
        baseBrowserFunctions.waitElement(driver, Constants.addEmployeeLink);
        baseBrowserFunctions.waitElement(driver, By.tagName("body"));
        baseBrowserFunctions.waitElement(driver, Constants.table);
    }

    private void waitForLoginPage(ChromeDriver driver) {
        baseBrowserFunctions.waitForURL(driver, Constants.signInURL);
        baseBrowserFunctions.waitForPageReadyState(driver);
        baseBrowserFunctions.waitElement(driver, Constants.submit);
        baseBrowserFunctions.waitElement(driver, Constants.regLink);
    }

    private void addNewEmployee(ChromeDriver driver, String ts) {
        baseBrowserFunctions.waitForURL(driver, "add");
        baseBrowserFunctions.waitElement(driver, Constants.hrLink);
        baseBrowserFunctions.waitForElements(driver, By.tagName("input"));
        baseBrowserFunctions.waitAndWrite(driver, Constants.fName , ts + "fname1");
        baseBrowserFunctions.waitAndWrite(driver, Constants.lName , ts + "lname1");
        baseBrowserFunctions.waitAndWrite(driver, Constants.address , ts + "address1");
        baseBrowserFunctions.waitAndWrite(driver, Constants.country , ts + "country1");
        baseBrowserFunctions.waitAndWrite(driver, Constants.city , ts + "city1");
        baseBrowserFunctions.waitAndWrite(driver, Constants.zip , ts + "zip1");
        baseBrowserFunctions.waitAndWrite(driver, Constants.phone , ts + "phone1");
        baseBrowserFunctions.waitAndWrite(driver, Constants.salary , ts + "salary1");
        baseBrowserFunctions.waitAndClick(driver, Constants.submit);
    }
}