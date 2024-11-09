package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {//удаление контакта

    @Test
    public void canRemoveContact() throws InterruptedException {
        if (app.hbm().getContactCount()==0) {//проверяем в БД
            app.hbm().createContact(new ContactData("", "middlename1", "lastname1", "nickname1", "", "afa@gmail.com", "firstname1", "", "", "+79232501606", "", ""));//вызов метода создания контакта
        }
        //app.contacts().checkIsContact();       // проверим что есть хотя бы один контакт, если нет ни одного, то создадим
        //Thread.sleep(10000);
        var oldContacts = app.hbm().getContactList();
        var rnd=new Random();//в старом списке выбрать какой-то обьект, который будет соответствовать удаляемой группе
        var index = rnd.nextInt(oldContacts.size());//случайным образом выбираем индекс какого-то элемента из списка old контактс
        app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно удалить контакт, без предварительных проверок
        app.contacts().removeContact(oldContacts.get(index));        //вызов метода по удалению контакта
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.remove(index);//удаляем элемент с заданным индексом. expectedList - ожидаемый список
        Assertions.assertEquals(newContacts,expectedList);//новый списка должен быть на единицу меньше чем размер старого списка
        //Assertions.assertEquals(oldContacts.size(),1);//новый списка должен быть на единицу меньше чем размер старого списка

    }
    @Test
    void canRemoveAllContactAtOnce() {//удаляем все контакты одновременно
        app.contacts().openContactPresent();   // перейдём на страницу, на кототой можно удалить контакт
        if (app.hbm().getContactCount()==0) {
            app.hbm().createContact(new ContactData("", "middlenameGENA", "lastnameGENA", "nicknameGENA", "", "afa@gmail.com", "firstname1", "src/test/resources/images/avatar.png", "", "+79232501606", "", ""));//вызов метода создания контакта
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());//ожидаем 0 сравниваем с результатом выполнения метода по подсчету количества контактов
    }

@Test
public void CanRemoveContactFromGroup() {//исключить контакт из группы через UI и проверками в БД
    if (app.hbm().getGroupCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет.
        var group = new GroupData()
                .WithName(CommonFunctions.randomString(10))
                .WithHeader(CommonFunctions.randomString(20))
                .WithFooter(CommonFunctions.randomString(30));
        app.groups().createGroup(group);//создаём групп через UI
    }
    if (app.hbm().getContactCount()==0) { //проверяем в БД, если контакта нет, то
        var contact = new ContactData()//создаем нового обьекта через UI
                .WithLastname(CommonFunctions.randomString(10))
                .WithFirstname(CommonFunctions.randomString(10))
                .WithFoto(randomFile("src/test/resources/images"));
        app.contacts().create(contact);//создане контакта через UI
    }
    app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно выбрать контакт
    var contact = app.contacts().getList().get(0); // получим список всех контактов через UI и выбираем первый
    var group = app.groups().getList().get(0); // получим списсок всех групп через UI и выбираем первую

    app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно выбрать контакт
    app.contacts().addContactInGroup(contact, group); // добавление контакта в группу через UI

    app.contacts().refreshContactPresent();
    var oldContacts = app.hbm().getContactsInGroup(group);//загружаем контакты

    var rnd=new Random();//в старом списке выбрать какой-то обьект, который будет соответствовать удаляемой группе
    var index = rnd.nextInt(oldContacts.size());//случайным образом выбираем индекс какого-то элемента из списка old контактс
    app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно удалить контакт, без предварительных проверок
    app.contacts().removeContactInGroup(oldContacts.get(index), group);    // удаляем контакт из группы
    var newContacts = app.hbm().getContactsInGroup(group);//загружаем контакты после удаления
    Assertions.assertEquals(newContacts.size()+1,oldContacts.size());//новый списка должен быть на единицу меньше чем размер старого списка
}

}



