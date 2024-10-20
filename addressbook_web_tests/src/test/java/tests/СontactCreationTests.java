import model.ContactData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class СontactCreationTests extends TestBase {

    /*public static List<String> contactNameProvider() {//возвращает список строк
        var result=new ArrayList<String>();//создаем пустой список
        for (int i=0; i<5; i++) {
            app.contacts().createGroup(new ContactData(randomString(i*10), "firstname", "middlename"));//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        }
    }*/

    @ParameterizedTest
    @ValueSource (strings = {"firstname1","firstname'"})
    public void canCreateContact() {//создаем контакт
               app.contacts().createContact(new ContactData("firstname1", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com")
        );//вызов метода создания контакта
    }
    @Test
    public void canCreateContactWithEmptyName() {//создаем пустой контакт
                app.contacts().createContact(new ContactData());//вызов метода создания пустого контакта
    }
    @Test
    public void canCreateContactWithEmptyNameOnly() {//создаем контакт только с наименованием
               app.contacts().createContact(new ContactData().WithFirstname("some Firstname"));//вызов метода создания контакта c новым именем
    }

    /*@ParameterizedTest
    @MethodSource("contactNameProvider")
    public void CanCreateMultipleContacts() {//создаем несколько контактов со случайным наименованием в адресной книге
        int n=5;
        int contactCount = app.contacts().getCount();//класс помощник для получения количества контактов
        for (int i=0; i<n; i++) {
            app.contacts().createGroup(new ContactData(randomString(i*10), "firstname", "middlename"));//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        }
        int newGroupCount = app.groups().getCount();//получаем новое значение
        Assertions.assertEquals(contactCount+n, newContactCount);//новое значение должно быть больше на n
    }*/
}


/*
было до
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.TestBase;

public class СontactCreationTests extends TestBase {
    private static WebDriver driver; //глобальная переменная


    @BeforeEach
    public void setUp() {
        if (driver == null) {//если переменная driver не проинициализирована, то выполняем код инициализации
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));//когда java будет завершаться в самом конце нужно выполнить функцию driver::quit
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1920, 1040));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();//логинимся
        }
    }

@Test
public void canCreateContact() {//создаем контакт
    if (!isElementPresent(By.xpath("(//input[@name=\'submit\'])"))) {//если на странице есть кнопка submit (ENTER), то никакой переход делать не требуется
        driver.findElement(By.linkText("add new")).click();
    }
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

      /*private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);//если находим элемент, то возвращаем его, если нет, то исключение
            return true;//исключение не возникло
        } catch (NoSuchElementException exception) {//исключение
            return false;
        }
    }*/
