package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver; //в прошлом проекте было protected. Отличие в том, что теперь помощники будут обращаться к driver через метод WebDriver, а не прямым чтением поля
    private String String;//сохраняется информация о браузере
    private Properties properties;//хранятся настройки окружения
    private SessionHelper sessionHelper;//ссылка на помощника
    private HttpSessionHelper httpSessionHelper;


    public void init(String browser, Properties properties) {//убрали инициализацию драйвера, оставили только инфу о том, какой браузер хотим запустить
        this.String = browser;
        this.properties=properties;
            }

public WebDriver driver() {
        if (driver == null){
//ленивая инициализация
            if ("chrome".equals(String)){//browser - параметр, который управляет выбором браузера
                driver = new ChromeDriver();
            } else if ("firefox".equals(String)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", String));//исключение о неизвестном браузере
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));//завершение джава в самом конце
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1920, 1040));
        }
        return driver;
}

public SessionHelper session() {//метод, который выполняет ленивую инициализацию. Возвращаемое значение иммет тип SessionHelper
    if (sessionHelper==null){
        sessionHelper = new SessionHelper(this);//менеджер передает ссылку на себя
    }
    return sessionHelper;//возвращаем либо созданный объект либо тот, который был создан ранее
}

    public HttpSessionHelper htpp() {
        if (httpSessionHelper==null){//ленивая инициализация
            httpSessionHelper = new HttpSessionHelper(this);//менеджер передает ссылку на себя
        }
        return httpSessionHelper;//возвращаем либо созданный объект либо тот, который был создан ранее

    }

    public String property(String name){//вспомогательный метод для обращения к файлу с настройками
        return properties.getProperty(name);
    }

}
