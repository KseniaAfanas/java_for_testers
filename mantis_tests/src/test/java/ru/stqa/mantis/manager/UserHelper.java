package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserHelper {

    public static void checkUser(String url, String user, String password) {
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1920, 1040));
        driver.findElement(By.id ("realname")).sendKeys(user);
        driver.findElement(By.id ("password")).sendKeys(password);
        driver.findElement(By.id ("password-confirm")).sendKeys(password);
        driver.findElement((By.cssSelector("span.bigger-110"))).click();
    }
}
