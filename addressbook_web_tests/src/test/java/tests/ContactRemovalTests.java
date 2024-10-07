import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;

public class ContactRemovalTests {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null) {//если переменная driver не проинициализирована, то выполняем код инициализации
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1920, 1040));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();//логинимся
        }
    }

    @Test
    public void canRemoveContact() {
        if (!isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) {
            driver.findElement(By.linkText("home")).click();
        }
        driver.findElement(By.name("selected[]")).click();// исправлен селектор
        driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
        driver.findElement(By.linkText("home")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }
}
