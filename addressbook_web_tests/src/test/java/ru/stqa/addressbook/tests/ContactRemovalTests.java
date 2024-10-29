package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {//удаление контакта

    @Test
    public void canRemoveContact() {
        app.contacts().openContactPresent();   // перейдём на страницу, на кототой можно удалить контакт
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com", "firstname1",""));//вызов метода создания контакта
        }
//        app.contacts().checkIsContact();       // проверим что есть хотя бы один контакт, если нет ни одного, то создадим
        var oldContacts = app.contacts().getList();
        var rnd=new Random();//в старом списке выбрать какой-то обьект, который будет соответствовать удаляемой группе
        var index = rnd.nextInt(oldContacts.size());//случайным образом выбираем индекс какого-то элемента из списка old контактс
        app.contacts().removeContact(oldContacts.get(index));        //вызов метода по удалению контакта
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.remove(index);//удаляем элемент с заданным индексом. expectedList - ожидаемый список
        Assertions.assertEquals(newContacts,expectedList);//новый списка должен быть на единицу меньше чем размер старого списка
        //Assertions.assertEquals(oldContacts.size(),1);//новый списка должен быть на единицу меньше чем размер старого списка

    }
    @Test
    void canRemoveAllContactAtOnce() {//удаляем все контакты одновременно
        app.contacts().openContactPresent();   // перейдём на страницу, на кототой можно удалить контакт
        if (app.contacts().getCount()==0) {
            app.contacts().createContact(new ContactData("", "middlenameGENA", "lastnameGENA", "nicknameGENA", "+79232501606", "afa@gmail.com", "firstname1","src/test/resources/images/avatar.png"));//вызов метода создания контакта
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());//ожидаем 0 сравниваем с результатом выполнения метода по подсчету количества контактов
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
