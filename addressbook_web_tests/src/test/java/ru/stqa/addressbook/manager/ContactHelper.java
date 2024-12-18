package ru.stqa.addressbook.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private void addToSelectedContacts() {//добавление выбранного контакта в выбранную группу
        click(By.xpath("//input[@name=\'add\']"));
    }

    private void selectGroupHome(GroupData group) { // выбор группы на странице home
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());//находим на странице нужный элемент
        //click(By.cssSelector(String.format("input[value='%s']",group.id())));// выбор контакта
    }
    public List<ContactData> cleanPhoto(List<ContactData> contacts) { // зачистка фото
        List<ContactData> newContacts = new ArrayList<>();
        for(ContactData cd : contacts)
            newContacts.add(cd.WithFoto(""));
        return newContacts;
    }

    public boolean checkContactFromGroup(List<ContactData> contacts, ContactData contact) { // проверить, есть ли в списке нужный контакт
        for(ContactData cd : contacts)
            if (cd.id().equals(contact.id())) return true;
        return false;
    }

    public void addContactInGroup(ContactData contact, GroupData group) { // добавление контакта в группу через UI
        openContactPresent();       //открыть страницу со списком контактов
        selectContact(contact);     //выбрать контакт (отметить галочкой)
        selectGroupHome(group);     //выбрать группу
        addToSelectedContacts();    //добавить выбранный контакт в выбранную группу
    }

    public void removeContactInGroup(ContactData contact, GroupData group) { // удаление контакта из группы через UI
        openContactPresent();       //открыть страницу со списком контактов
        selectGroupHomeUP(group);   //выбрать группу вверху страницы
        selectContact(contact);     //выбрать контакт (отметить галочкой)
        removeContactsfrom();       //нажать кнопку "Remove from..."
    }

    private void selectGroupHomeUP(GroupData group) { // выбор группы на странице home ВВЕРХУ
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());//находим на странице нужный элемент
    }

    private void removeContactsfrom() { //удаление контакта из группы
        click(By.xpath("//input[@name=\'remove\']"));
    }

    public void refreshContactPresent() {//открыть страницу со списком контактов
        click(By.linkText("home"));
    }

        public void createContact(ContactData contact) {//метод для создания контакта
            openContactPage();//открыть страницу с контактами
            initContactCreation();//открыть форму с новым контактом
            fillContactForm(contact);//изменить данные по контакту
            submitContactCreation();//сохранение данных по контакту
            returnToHomePage();//вернуться на станицу с контактами
        }

        public void modifyContact(ContactData contact, ContactData modifiedContact) {//метод для модификации контакта
            selectContact(contact);//выбрать контакт (отметить галочкой)
            initContactModification(contact);//нажать кнопку модификации Edit
            fillContactForm(modifiedContact);//заполнить форму данными, которые содержатся в переданном объекте
            submitContactModification();//сохраняем форму  по кнопке Update
            returnToHomePage();//возврат на страницу контактов
        }

        public void removeContact(ContactData contact) {//метод по удалению контакта
            selectContact(contact);//выбираем контакт
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
                createContact(new ContactData("", "middlenameGENA", "lastnameGENA", "nicknameGENA", "", "GENA@gmail.com","" ,"" , "", "+79232501606", "", "", "firstnameGENA", "src/test/resources/images/avatar.png"));//вызов метода создания контакта
            }
        }
        private void selectContact(ContactData contact) {
            click(By.cssSelector(String.format("input[value='%s']",contact.id())));// выбор контакта
        }
        private void initContactModification(ContactData contact) {
            click(By.cssSelector(String.format("[href='edit.php?id=%s']",contact.id())));//выбор карандаша для редактирования контакта

        }
        private void fillContactForm(ContactData contact) {//метод для изменения данных контакта
            type(By.name("firstname"), contact.firstname());
            type(By.name("middlename"), contact.middlename());
            type(By.name("lastname"), contact.lastname());
            type(By.name("nickname"), contact.nickname());
            type(By.name("address"), contact.address());
            type(By.name("home"), contact.home());
            type(By.name("mobile"), contact.mobile());
            type(By.name("work"), contact.work());
            type(By.name("email"), contact.email());
            type(By.name("email2"), contact.email2());
            type(By.name("email3"), contact.email3());
            if (!"".equals(contact.foto())) {
                attach(By.name("photo"), contact.foto());
            }
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

        public List<ContactData> getList() {
            openContactPresent();//почему-то без неё тоже работает
        var contacts = new ArrayList<ContactData>();//цикл, который читает данные из ИБ, анализирует их и строит список. Создаем пустой список, в который будем складовать контакты
//        var tds = manager.driver.findElements(By.cssSelector("table.sortcompletecallback-applyZebra"));//получить со страницы список элементов, которые содержат информацию о контактах
        var tds = manager.driver.findElements(By.xpath("//table[@class='sortcompletecallback-applyZebra']/tbody/tr"));//получить со страницы список элементов, которые содержат информацию о контактах
               for (var row: tds) {//в цикле перебираем строки
            var cells = row.findElements(By.tagName("td"));//разбиваем строку на ячейки
            if (!cells.isEmpty()) {// только если вернули не пустой список ячеек td
                var firstname = cells.get(2).getText();//название контакта это текст, поэтому его получаем с помощью getText
                var lastname = cells.get(1).getText();
                var checkbox = cells.get(0).findElement(By.name("selected[]")); //найдем чекбокс, который находится внутри элемента td
                var id = checkbox.getAttribute("value");//получаем идентификатор
                contacts.add(new ContactData().WithId(id).WithFirstname(firstname).WithLastname(lastname));// в список контактов добавляем новый объект
            }
        }
            return contacts;
    }

    public void create(ContactData contact) {//метод для создания контакта как в лекции 6.4
        initContactCreation();//открыть форму с новым контактом
        fillContactForm(contact);//изменить данные по контакту
        submitContactCreation();//сохранение данных по контакту
        returnToHomePage();//вернуться на станицу с контактами
    }

public void create(ContactData contact, GroupData group) {//метод для включения контакта как в лекции 6.4+выбор группы из выпадающего списка
    initContactCreation();//открыть форму с новым контактом
    fillContactForm(contact);//изменить данные по контакту
    selectGroup(group);//выбор группы
    submitContactCreation();//сохранение данных по контакту
    returnToHomePage();//вернуться на станицу с контактами
}

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());//находим на странице нужный элемент
    }

    public String getPhones(ContactData contact) {//Получаем информацию о тел. для контакта с заданным идентификатором. Возвращает строку
      return manager.driver.findElement(By.xpath( //возвращаем текст
              String.format("//input[@id='%s']/../../td[6]",contact.id()))).getText();//сначала ищем чекбокс с заданным идентификатором. String.format тк нужно вызывать подстановку
        //строка в которую передается подстановка "//input[@id='%s']"
        //подъем на 2 уровня вверх, чтобы найти строку таблицы /../..
        //находим в строке ячейку по заданному номеру ячейки (6я в таблице это телефон) td[6]

    }

    public Map<String, String> getPhones() {//метод, который возвращает информацию обо всех тел для всех контактов. Идентификатор строка и тел строка
        var result= new HashMap<String, String>();//сохраняем соответствие между телефонами и идентификатором
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));//ищем элементы, которые соответствуют строкам таблицы и сохраняем в переменную rows(строки таблицы).
        // Все нужные строки имеют атрибут name="entry"
    for (WebElement row:rows) {
        var id =row.findElement(By.tagName("input")).getAttribute("id");//для каждой строки ищем идентификатор контакта
        var phones = row.findElements(By.tagName("td")).get(5).getText();// ищем все ячейки в этой таблице. Берем ячейку с индексом 5 (те с номером 6) и получаем её текст
        result.put(id,phones);//помещаем в переменную соответствие между идентификатором и телефонами
    }
    return result;
    }

    public String getAddress(ContactData contact) {//Получаем информацию об адресе для контакта с заданным идентификатором. Возвращает строку
        return manager.driver.findElement(By.xpath( //возвращаем текст
                String.format("//input[@id='%s']/../../td[4]",contact.id()))).getText();
    }

    public String getEmail(ContactData contact) {//Получаем информацию о Email для контакта с заданным идентификатором. Возвращает строку
        return manager.driver.findElement(By.xpath( //возвращаем текст
                String.format("//input[@id='%s']/../../td[5]",contact.id()))).getText();
    }
}
