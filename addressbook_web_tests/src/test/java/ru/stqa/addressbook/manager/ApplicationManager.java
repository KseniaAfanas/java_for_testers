package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {/* методы для управления тестируемым приложением*/
    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contacts;//private используется внутри этого класса
    private Properties properties;

    public void init(String browser, Properties properties) {
        this.properties=properties;
        if (driver == null) {
           if ("chrome".equals(browser)){//browser - параметр, который управляет выбором браузера
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s",browser));//исключение о неизвестном браузере
            }
                        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));//завершение джава в самом конце
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1920, 1040));
            driver.findElement(By.name("user")).click();
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
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
