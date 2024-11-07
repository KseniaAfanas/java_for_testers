package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {//удаление контакта

    @Test
    public void canRemoveContact() throws InterruptedException {
        if (app.hbm().getContactCount()==0) {//проверяем в БД
            app.hbm().createContact(new ContactData("", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com", "firstname1",""));//вызов метода создания контакта
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
            app.hbm().createContact(new ContactData("", "middlenameGENA", "lastnameGENA", "nicknameGENA", "+79232501606", "afa@gmail.com", "firstname1","src/test/resources/images/avatar.png"));//вызов метода создания контакта
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());//ожидаем 0 сравниваем с результатом выполнения метода по подсчету количества контактов
    }
}


