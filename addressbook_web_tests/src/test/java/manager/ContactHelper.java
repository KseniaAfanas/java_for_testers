package manager;

import model.ContactData;
import org.openqa.selenium.By;

    public class ContactHelper extends HelperBase{

    public ContactHelper (ApplicationManager manager){//конструктор в котором передается ссылка на менеджера
        super(manager);
    }

    public void createGroup(ContactData contactData) {
    }

    public void openContactPage() {//открыть страницу создания нового контакта
        if (!manager.isElementPresent(By.xpath("(//input[@name=\'submit\'])"))) {//если на странице есть кнопка submit (ENTER), то никакой переход делать не требуется
            initContactCreation();
        }
    }

        public void openContactPresent() {//открыть страницу со списком контактов
            if (manager.isElementPresent(By.xpath("(//input[@name=\'Delete\'])"))) { //если на странице есть кнопка Delete, то никакой переход делать не требуется
                click(By.linkText("home"));
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
            removeSelectedContacts();//удаляем контакт
            returnToHomePage();//возвращаемся на страницу с контактами
        }

        private void submitContactCreation() {//сохранение данных по контакту
            click(By.xpath("(//input[@name=\'submit\'])"));//driver.findElement(By.xpath("(//input[@name=\'submit\'])[2]")).click();
        }

               private void initContactCreation() {//метод по открытию формы с новым контактом
                   click(By.linkText("add new"));
        }

        private void removeSelectedContacts() {//удаление контакта
            click(By.xpath("//input[@value=\'Delete\']"));
        }

        public void checkIsContact() { // если на странице нет контактов, то создадим
            if (!manager.isElementPresent(By.name("selected[]"))) {
                createContact(new ContactData("", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com", "firstname1"));//вызов метода создания контакта
            }
        }
        private void selectContact() {
            click(By.name("selected[]"));// выбор контакта
        }
        private void initContactModification() {
            click(By.cssSelector("[title='Edit']"));
        }
        private void fillContactForm(ContactData contact) {//метод для изменения данных контакта
            type(By.name("firstname"), contact.firstname());
            type(By.name("middlename"), contact.middlename());
            type(By.name("lastname"), contact.lastname());
            type(By.name("nickname"), contact.nickname());
            type(By.name("mobile"), contact.mobile());
            type(By.name("email"), contact.email());
        }

        private void submitContactModification() {
            click(By.name("update"));
        }
        private void returnToHomePage() {
            click(By.linkText("home"));
        }

        public int getCount() {//метод для подсчета элементов
            openContactPresent();
            return manager.driver.findElements(By.name("selected[]")).size(); //метод, который находит много элеметов. Возвращает список. size()- возвращает размер списка
        }

        public void removeAllContacts() {//метод для удаления всх контактов
            openContactPresent();
            selectAllContacts();//отметить галочкой все контакты на странице контактов
            removeSelectedContacts();
    }

        private void selectAllContacts() {
            var checkboxes=manager.driver.findElements(By.name("selected[]")); //метод, который находит много элеметов. Возвращает список. size()- возвращает размер списка
            for (var checkbox : checkboxes) {//цикл, который перебирает все элементы коллекции checkboxes
                checkbox.click();
            }
        }
    }
