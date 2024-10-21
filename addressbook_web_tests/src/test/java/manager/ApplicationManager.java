package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {/* методы для управления тестируемым приложением*/
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;//private используется внутри этого класса

    public void init(String browser) {
        if (driver == null) {
           if ("chrome".equals(browser)){//browser - параметр, который управляет выбором браузера
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s",browser));//исключение о неизвестном браузере
            }
                        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));//завершение джава в самом конце
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1920, 1040));
            driver.findElement(By.name("user")).click();
            session().login("admin", "secret");
        }
    }

    public LoginHelper session() {//метод, который возвращает ссылку на помощника (ленивая инициализация)
    if (session == null) {
        session = new LoginHelper(this);//помощнику при конструировании сообщаем кто является его менеджером
    }
    return session;//возвращаем ссылку на помощника
}

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contacts() {
        if (contacts == null) {
            contacts = new ContactHelper(this);
        }
        return contacts;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

}
