package manager;

import model.ContactData;
import org.openqa.selenium.By;

    public class ContactHelper {
    private final ApplicationManager manager;

    public ContactHelper (ApplicationManager manager){//конструктор в котором передается ссылка на менеджера
        this.manager=manager;//параметр manager передается в конструктор базового класса
    }

    public void createGroup(ContactData contactData) {
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.xpath("(//input[@name=\'submit\'])"))) {//если на странице есть кнопка submit (ENTER), то никакой переход делать не требуется
            manager.driver.findElement(By.linkText("add new")).click();
        }
    }

        public void openContactPresent() {
            if (manager.isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) { //если на странице есть кнопка Delete, то никакой переход делать не требуется
                manager.driver.findElement(By.linkText("home")).click();
            }
        }

        public void createContact(ContactData contact) {//метод для создания контакта
            openContactPage();
            manager.driver.findElement(By.linkText("add new")).click();
            manager.driver.findElement(By.name("firstname")).click();
            manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
            manager.driver.findElement(By.name("middlename")).click();
            manager.driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
            manager.driver.findElement(By.name("lastname")).click();
            manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
            manager.driver.findElement(By.name("nickname")).click();
            manager.driver.findElement(By.name("nickname")).sendKeys(contact.nickname());
            manager.driver.findElement(By.name("mobile")).click();
            manager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
            manager.driver.findElement(By.name("email")).click();
            manager.driver.findElement(By.name("email")).sendKeys(contact.email());
            manager.driver.findElement(By.xpath("(//input[@name=\'submit\'])")).click();//driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
            manager.driver.findElement(By.linkText("home page")).click();
        }

        public void removeContact() {//метод по удалению контакта
            manager.driver.findElement(By.name("selected[]")).click();// выбор контакта
            manager.driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
            manager.driver.findElement(By.linkText("home")).click();
        }

        public void checkIsContact() { // если на странице нет контактов, то создадим
            if (!manager.isElementPresent(By.name("selected[]"))) {
                createContact(new ContactData("firstname1", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com"));//вызов метода создания контакта
            }
        }
    }
