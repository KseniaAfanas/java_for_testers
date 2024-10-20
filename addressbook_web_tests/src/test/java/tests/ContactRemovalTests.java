import model.ContactData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {//удаление контакта

    @Test
    public void canRemoveContact() {
        app.openContactPresent();   // перейдём на страницу, на кототой можно удалить контакт
        app.checkIsContact();       // проверим что есть хотя бы один контакт, если нет ни одного, то создадим
        app.removeContact();        //вызов метода по удалению контакта
    }
}



/*
было до
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {//удаление контакта
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {//метод инициализации
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
        if (!isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) {//проверяем наличие кнопки Удалить
            driver.findElement(By.linkText("home")).click();
        }
        try {
            driver.findElement(By.name("selected[]"));
        } catch (NoSuchElementException e) {
            driver.findElement(By.linkText("add new")).click();
            driver.findElement(By.name("firstname")).click();
            driver.findElement(By.name("firstname")).sendKeys("firstname1");
            driver.findElement(By.name("middlename")).click();
            driver.findElement(By.name("middlename")).sendKeys("middlename1");
            driver.findElement(By.name("lastname")).click();
            driver.findElement(By.name("lastname")).sendKeys("lastname1");
            driver.findElement(By.name("nickname")).click();
            driver.findElement(By.name("nickname")).sendKeys("nickname1");
            driver.findElement(By.name("mobile")).click();
            driver.findElement(By.name("mobile")).sendKeys("+79232501606");
            driver.findElement(By.name("email")).click();
            driver.findElement(By.name("email")).sendKeys("afa@gmail.com");
            driver.findElement(By.xpath("(//input[@name=\'submit\'])")).click();//driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
            driver.findElement(By.linkText("home page")).click();
        }
        driver.findElement(By.name("selected[]")).click();// выбор контакта
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
 */
