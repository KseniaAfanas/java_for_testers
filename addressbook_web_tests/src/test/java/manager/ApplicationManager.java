package manager;

import model.ContactData;
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

    public void init(String browser) {
        if (driver == null) {
           if ("chrome".equals(browser)){
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s",browser));
            }
                        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));//завершение джава в самом конце
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1920, 1040));
            driver.findElement(By.name("user")).click();
            session().login("admin", "secret");
        }
    }

    public LoginHelper session() {
    if (session == null) {
        session = new LoginHelper(this);
    }
    return session;
}

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

    public void createContact(ContactData contact) {//метод для создания контакта
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(contact.nickname());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(contact.email());
        driver.findElement(By.xpath("(//input[@name=\'submit\'])")).click();//driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        driver.findElement(By.linkText("home page")).click();
    }

    public void openContactPage() {
        if (!isElementPresent(By.xpath("(//input[@name=\'submit\'])"))) {//если на странице есть кнопка submit (ENTER), то никакой переход делать не требуется
            driver.findElement(By.linkText("add new")).click();
        }
    }

    public void openContactPresent() {
        if (isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) { //если на странице есть кнопка Delete, то никакой переход делать не требуется
            driver.findElement(By.linkText("home")).click();
        }
    }

    public void checkIsContact() { // если на страниц нет контактов, то создадим
        if (!isElementPresent(By.name("selected[]"))) {
            createContact(new ContactData("firstname1", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com"));//вызов метода создания контакта
        }
    }

    public void removeContact() {//метод по удалению контакта
        driver.findElement(By.name("selected[]")).click();// выбор контакта
        driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
        driver.findElement(By.linkText("home")).click();
    }

}
