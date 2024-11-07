package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import ru.stqa.addressbook.manager.ApplicationManager;

import static ru.stqa.addressbook.tests.TestBase.app;
import static ru.stqa.addressbook.tests.TestBase.randomFile;

public class СontactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>();//инициализируем создаваемый список соответствующими значениями
//        for (var firstname: List.of("","firstname")) {//цикл, который перебирает два возможных значния (пустая и НЕ пустая строка)
//            for (var middlename: List.of("","middlename")){//для каждого из этих названий внутри вложенный цикл, который перебирает 2  воможных значения middlename
//                for (var lastname: List.of("","lastname")) {//для каждой пары перебираем возможные значения lastname
//                    for (var nickname: List.of("","nickname")) {
//                        for (var mobile: List.of("","mobile")) {
//                            for (var email: List.of("","email")) {
//                                for (var foto: List.of("",randomFile("src/test/resources/images"))) {//List.of("","src/test/resources/images/avatar.png"
//                                result.add(new ContactData().WithFirstname(firstname).WithMiddlename(middlename).WithLastname(lastname).WithNickname(nickname).WithMobile(mobile).WithEmail(email).WithFoto(foto));//добавляем значение в список генерируемых объектов. Идентификаторов пока нет
//                        }
//                    }
//                    }
//                }
//            }
//        }
//        }
        ObjectMapper mapper = new ObjectMapper();//прочитать данные из файла
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});//TypeReference класс у которого ничего нет, только декларация. Он пустой
        result.addAll(value);//добавить все значения списка, которые были прочитаны из файла
        return result;
    }

    public static List<ContactData> singleRandomContact() {
       return List.of(new ContactData()
               .WithMiddlename(CommonFunctions.randomString(20))
               .WithLastname(CommonFunctions.randomString(30))
               .WithNickname(CommonFunctions.randomString(10))
               .WithMobile(CommonFunctions.randomString(10))
               .WithEmail(CommonFunctions.randomString(10))
               .WithFirstname(CommonFunctions.randomString(10))
               //.WithFoto(randomFile("src/test/resources/images")));
               .WithFoto(""));
    }

   public static List<ContactData> negativeContactProvider() {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>(List.of(
                new ContactData ("", "", "", "", "", "","firstname'", "")));//инициализируем создаваемый список соответствующими значениями
        return result;
    }


    @ParameterizedTest
    @MethodSource("singleRandomContact")//провайдер тестовых данных, который генерирует данные с фикс значениями или сгенерированными
    public void CanCreateContacts(ContactData contact) {//создаем несколько контактов со случайным наименованием в адресной книге
        var oldContacts = app.jdbc().getContactList();//класс помощник для получения списка контактов
        app.contacts().createContact(contact);//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        var newContacts = app.jdbc().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
        };
        newContacts.sort(compareById);
        var maxID =newContacts.get(newContacts.size()-1).id();//максимальный из существующих идентификаторов
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.add(contact.WithId(maxID));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
    }

   @ParameterizedTest
    @MethodSource ("negativeContactProvider")//метод который создает контакт с апострофом (всегда падает, поэтому выделяем отдельно)
    public void CanNotContact(ContactData contact) {//НЕ создается контакт с заданными параметрами
        var oldContacts = app.contacts().getList();//класс помощник для получения списка контактов;
        app.contacts().createContact(contact);//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        var newContacts = app.contacts().getList();//получаем новое значение
        Assertions.assertEquals(newContacts, oldContacts);//проверяем, что количество контактов не изменяется
    }

    @Test
    void CanCreateContact() {//создать контакт не включенный в группу
        var contact = new ContactData()
                .WithLastname(CommonFunctions.randomString(10))
                .WithFirstname(CommonFunctions.randomString(10))
                .WithFoto(randomFile("src/test/resources/images"));
        app.contacts().create(contact);

    }
    @Test
    void CanCreateContactInGroup() {//создать контакт, который включен в группу
        var contact = new ContactData()
                .WithLastname(CommonFunctions.randomString(10))
                .WithFirstname(CommonFunctions.randomString(10))
        .WithFoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет. Раньше тут был isGroupPresent (проверял наличие хотя бы одной группы)
            app.hbm().createGroup(new GroupData("", "group header", "group footer", "group name"));
        }
        var group = app.hbm().getGroupList().get(0);//получаем список групп и выбираем первую из них

        var oldRelated = app.hbm().getContactsInGroup(group);//проверяем какие контакты уже были включены в эту группу ДО выполнения тестируемой операции
        app.contacts().create(contact,group);//создаем контакт
        var newRelated = app.hbm().getContactsInGroup(group);//загружаем новый список
Assertions.assertEquals(oldRelated.size()+1,newRelated.size());//проверяем, что новый список содержит на один элемент больше, чем старый
        //сделать проверку, котороя сравнивает содержимое списков (так же как сравниваются полные списки контактов/групп после создания)
    }

@Test
public void CanInsertContactInGroup() {//включить контакт в группу через UI и проверками в БД
    if (app.hbm().getContactCount()==0) { //проверяем в БД, если контакта нет, то
        var contact = new ContactData()//создаем нового обьекта через UI
                .WithLastname(CommonFunctions.randomString(10))
                .WithFirstname(CommonFunctions.randomString(10))
                .WithFoto(randomFile("src/test/resources/images"));
        app.contacts().create(contact);//создане контакта через UI
    }
    if (app.hbm().getGroupCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет. Раньше тут был isGroupPresent (проверял наличие хотя бы одной группы)
        var group = new GroupData()
                .WithName(CommonFunctions.randomString(10))
                .WithHeader(CommonFunctions.randomString(20))
                .WithFooter(CommonFunctions.randomString(30));
        app.groups().createGroup(group);//создаём групп через UI
    }
    app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно выбрать контакт
    var contact = app.contacts().getList().get(0); // получим список всех контактов через UI и выбираем первый
    var group = app.groups().getList().get(0); // получим списсок всех групп через UI и выбираем первую

    var oldRelated = app.hbm().getContactsInGroup(group);//загружаем контакты уже были включены в эту группу ДО выполнения тестируемой операции
    if (app.contacts().checkContactFromGroup(oldRelated, contact)) { // проверяем, может быть контакт уже в группе
        app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно выбрать контакт
        app.contacts().removeContactInGroup(contact, group);    // удаляем контакт из группы
        // чтобы сбросить фильтр по группе
        app.groups().openGroupsPage();
        app.contacts().refreshContactPresent();
        oldRelated = app.hbm().getContactsInGroup(group);//загружаем контакты заново после удаления
    }
    oldRelated = app.contacts().cleanPhoto(oldRelated); // зачищаем информацию о фото

    app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно выбрать контакт
    app.contacts().addContactInGroup(contact, group); // добавление контакта в группу через UI
    var newRelated = app.hbm().getContactsInGroup(group);//загружаем новый список контактов ПОСЛЕ выполнения тестируемой операции
    newRelated = app.contacts().cleanPhoto(newRelated); // зачищаем информацию о фото
    Comparator<ContactData> compareById = (o1, o2) -> {
        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
    };
    newRelated.sort(compareById); // сортируем новый список
    oldRelated.add(contact);    // добавим контакт в старый список
    oldRelated.sort(compareById); // сортируем старый список

    Assertions.assertEquals(oldRelated, newRelated);//проверяем, что новый и старый список совпадают

}
}



//Для удаления контакта из группы нужно на главной странице приложения выбрать справа вверху группу, при этом будут показаны только входящие в эту группу контакты, после этого нужно отметить галочкой контакт и нажать кнопку "Remove from".