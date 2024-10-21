package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

    public class ContactHelper{
    private final ApplicationManager manager;

    public ContactHelper (ApplicationManager manager){//конструктор в котором передается ссылка на менеджера
        this.manager=manager;//параметр manager передается в конструктор базового класса
    }

    public void createGroup(ContactData contactData) {
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.xpath("(//input[@name=\'submit\'])"))) {//если на странице есть кнопка submit (ENTER), то никакой переход делать не требуется
            initContactCreation();
        }
    }

        public void openContactPresent() {
            if (manager.isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) { //если на странице есть кнопка Delete, то никакой переход делать не требуется
                сlick(By.linkText("home"));
            }
        }

        public void createContact(ContactData contact) {//метод для создания контакта
            openContactPage();//открыть страницу с контактами
            initContactCreation();//открыть форму с новым контактом
            fillContactForm(contact);//изменить данные по контакту
            submitContactCreation();//сохранение данных по контакту
            returnToHomePage();//вернуться на станицу с контактами
        }

        public void modifyContact(ContactData modifiedContact) {//метод для модификации контакта
            selectContact();//выбрать контакт (отметить галочкой)
            initContactModification();//нажать кнопку модификации Edit
            fillContactForm(modifiedContact);//заполнить форму данными, которые содержатся в переданном объекте
            submitContactModification();//сохраняем форму  по кнопке Update
            returnToHomePage();//возврат на страницу контактов
        }

        public void removeContact() {//метод по удалению контакта
            selectContact();//выбираем контакт
            removeSelectedContact();//удаляем контакт
            returnToHomePage();//возвращаемся на страницу с контактами
        }

        private void submitContactCreation() {//сохранение данных по контакту
            сlick(By.xpath("(//input[@name=\'submit\'])"));//driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        }

               private void initContactCreation() {//метод по открытию формы с новым контактом
            сlick(By.linkText("add new"));
        }

        private void removeSelectedContact() {//удаление контакта
            сlick(By.xpath("//input[@value=\'Delete\']"));
        }

        public void checkIsContact() { // если на странице нет контактов, то создадим
            if (!manager.isElementPresent(By.name("selected[]"))) {
                createContact(new ContactData("firstname1", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com"));//вызов метода создания контакта
            }
        }

        private void selectContact() {
            сlick(By.name("selected[]"));// выбор контакта
        }
        private void initContactModification() {
            сlick(By.cssSelector("[title='Edit']"));
        }
        private void fillContactForm(ContactData contact) {//метод для изменения данных контакта
            type(By.name("firstname"), contact.firstname());
            type(By.name("middlename"), contact.middlename());
            type(By.name("lastname"), contact.lastname());
            type(By.name("nickname"), contact.nickname());
            type(By.name("mobile"), contact.mobile());
            type(By.name("email"), contact.email());
        }

        private void type(By locator, String text) {//метод для заполнения поля с контактными данными
            сlick(locator);
            manager.driver.findElement(locator).clear();//очистить поле ввода
            manager.driver.findElement(locator).sendKeys(text);
        }

        private void submitContactModification() {
            сlick(By.name("update"));
        }
        private void returnToHomePage() {
            сlick(By.linkText("home"));
        }
        private void сlick(By locator) {
            manager.driver.findElement(locator).click();
        }

    }
