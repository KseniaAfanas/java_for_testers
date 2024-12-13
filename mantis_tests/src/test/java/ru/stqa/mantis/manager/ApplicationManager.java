package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver; //в прошлом проекте было protected. Отличие в том, что теперь помощники будут обращаться к driver через метод WebDriver, а не прямым чтением поля
    private String String;//сохраняется информация о браузере
    private Properties properties;//хранятся настройки окружения
    private SessionHelper sessionHelper;//ссылка на помощника
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private JamesApiHelper jamesApiHelper;
    private UserHelper userHelper;
    private DeveloperMailHelper developerMailHelper;


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

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper==null){//ленивая инициализация
            jamesCliHelper = new JamesCliHelper(this);//менеджер передает ссылку на себя
        }
        return jamesCliHelper;//возвращаем либо созданный объект либо тот, который был создан ранее

    }

    public JamesApiHelper jamesApi() {
        if (jamesApiHelper==null){//ленивая инициализация
            jamesApiHelper = new JamesApiHelper(this);//менеджер передает ссылку на себя
        }
        return jamesApiHelper;//возвращаем либо созданный объект либо тот, который был создан ранее

    }

    public MailHelper mail() {//ленивая инициализация
            if (mailHelper == null) {//ленивая инициализация
                mailHelper = new MailHelper(this);//менеджер передает ссылку на себя
            }
        return mailHelper;//возвращаем либо созданный объект либо тот, который был создан ранее
    }

    public UserHelper user() {//ленивая инициализация
        if (userHelper == null) {//ленивая инициализация
            userHelper = new UserHelper(this);//менеджер передает ссылку на себя
        }
        return userHelper;//возвращаем либо созданный объект либо тот, который был создан ранее
    }


    public DeveloperMailHelper developerMail() {//ленивая инициализация
        if (developerMailHelper == null) {//ленивая инициализация
            developerMailHelper = new DeveloperMailHelper(this);//менеджер передает ссылку на себя
        }
        return developerMailHelper;//возвращаем либо созданный объект либо тот, который был создан ранее
    }

    public String property(String name){//вспомогательный метод для обращения к файлу с настройками
        return properties.getProperty(name);
    }

    public void calcDriver (String url, String username) {
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1920, 1040));
        driver.findElement(By.id("realname")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.id("password-confirm")).sendKeys("password");
        driver.findElement((By.cssSelector("span.bigger-110"))).click();
    }
}




