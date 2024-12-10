package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserHelper extends HelperBase{//работает через пользовательский интерфейс
    public UserHelper (ApplicationManager manager){//конструктор принимает на вход ApplicationManager
        super(manager);//и обращается к конструктору базового класса, передавая ссылку на менеджера в него
    }

    public void startCreation(String user,String email) {
        if (!manager.session().isLoggedIn()){
            manager.session().login(manager.property("web.username"), manager.property("web.password"));
        }
        manager.driver().get(String.format("%s/manage_user_create_page.php",manager.property("web.baseUrl")));
        type(By.name("username"),user);
        type(By.name("realname"),user);
        type(By.name("email"),email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void delete(String user) {
        if (!manager.session().isLoggedIn()){
            manager.session().login(manager.property("web.username"), manager.property("web.password"));
        }
        manager.driver().get(String.format("%s/manage_user_page.php",manager.property("web.baseUrl")));

    }
    public static void finishCreation(String url, String user, String password) {
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1920, 1040));
        driver.findElement(By.id ("realname")).sendKeys(user);
        driver.findElement(By.id ("password")).sendKeys(password);
        driver.findElement(By.id ("password-confirm")).sendKeys(password);
        driver.findElement((By.cssSelector("span.bigger-110"))).click();
    }
}

