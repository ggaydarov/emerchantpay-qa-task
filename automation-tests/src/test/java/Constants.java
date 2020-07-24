import org.openqa.selenium.By;

public class Constants {
    public static final String baseURL = "localhost:11111/";
    public static final String signInURL = "localhost:11111/signin";
    public static final String registrationURL = "localhost:11111/registration";
    public static final String hrURL = "localhost:11111/hr";

    public static final By username = By.cssSelector("#username");
    public static final By password = By.cssSelector("#password");
    public static final By submit = By.cssSelector(".btn-primary");

    public static final By addEmployeeLink = By.cssSelector("a[href='add']");
    public static final By hrTD = By.tagName("td");
    public static final By regLink = By.cssSelector("a[href='/registration']");
    public static final By table = By.cssSelector("table");
    public static final By hrLink = By.cssSelector("a[href*='hr']");

    public static final By fName = By.cssSelector("#firstName");
    public static final By lName = By.cssSelector("#lastName");
    public static final By address = By.cssSelector("#address");
    public static final By country = By.cssSelector("#country");
    public static final By city = By.cssSelector("#city");
    public static final By zip = By.cssSelector("#zip");
    public static final By phone = By.cssSelector("#phone");
    public static final By salary = By.cssSelector("#salary");

    public static final By editEmployeeLink = By.cssSelector("a[href*='edit']");
    public static final By deleteEmployeeLink = By.cssSelector("a[href*='delete']");
}
