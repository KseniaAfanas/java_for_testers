import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class СontactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>();//инициализируем создаваемый список соответствующими значениями
        for (var firstname: List.of("","firstname")) {//цикл, который перебирает два возможных значния (пустая и НЕ пустая строка)
            for (var middlename: List.of("","middlename")){//для каждого из этих названий внутри вложенный цикл, который перебирает 2  воможных значения middlename
                for (var lastname: List.of("","lastname")) {//для каждой пары перебираем возможные значения lastname
                    for (var nickname: List.of("","nickname")) {
                        for (var mobile: List.of("","mobile")) {
                            for (var email: List.of("","email")) {
                                result.add(new ContactData().WithFirstname(firstname).WithMiddlename(middlename).WithLastname(lastname).WithNickname(nickname).WithMobile(mobile).WithEmail(email));//добавляем значение в список генерируемых объектов. Идентификаторов пока нет
                        }
                    }
                    }
                }
            }
        }
        for (int i=0; i<5; i++) {
            result.add(new ContactData()
                    .WithFirstname(randomString(i*10))
                    .WithMiddlename(randomString(i*10))
                    .WithLastname(randomString(i*10))
                    .WithNickname(randomString(i*10))
                    .WithMobile(randomString(i*10))
                    .WithEmail(randomString(i*10)));//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")//провайдер тестовых данных, который генерирует данные с фикс значениями или сгенерированными
       public void CanCreateMultipleContacts(ContactData contact) {//создаем несколько контактов со случайным наименованием в адресной книге
        var oldContacts = app.contacts().getList();//класс помощник для получения списка контактов
        app.contacts().createContact(contact);//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.add(contact.WithId(newContacts.get(newContacts.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
    }

    public static List<ContactData> negativeContactProvider() {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>(List.of(
                new ContactData ("", "", "", "", "", "", "firstname'")));//инициализируем создаваемый список соответствующими значениями
                return result;
    }

    @ParameterizedTest
    @MethodSource ("negativeContactProvider")//метод который создает контакт с апострофом (всегда падает, поэтому выделяем отдельно)
    public void CanNotContact(ContactData contact) {//НЕ создается контакт с заданными параметрами
        var oldContacts = app.contacts().getList();//класс помощник для получения списка контактов;
        app.contacts().createContact(contact);//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        var newContacts = app.contacts().getList();//получаем новое значение
        Assertions.assertEquals(newContacts, oldContacts);//проверяем, что количество контактов не изменяется
    }

}

    /*@ParameterizedTest
    @ValueSource (strings = {"firstname1","firstname'"})
    public void canCreateContact(String name) {//создаем контакт, в результате сознания, контактов становися больше на +1
        int contactCount = app.contacts().getCount();//считаем количество контактов ДО
               app.contacts().createContact(new ContactData(name, "middlename1", "lastname1", "nickname1", "+79232501606", "afa1@gmail.com"));//вызов метода создания контакта
        int newContactCount = app.contacts().getCount();//считаем количество контактов ПОСЛЕ
        Assertions.assertEquals(contactCount+1,newContactCount);
    }*/
    /*@Test
    public void canCreateContactWithEmptyName() {//создаем пустой контакт
                app.contacts().createContact(new ContactData());//вызов метода создания пустого контакта
    }*/

    /*@Test
    public void canCreateContactWithEmptyNameOnly() {//создаем контакт только с наименованием
               app.contacts().createContact(new ContactData().WithFirstname("some Firstname"));//вызов метода создания контакта c новым именем
    }*/
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
