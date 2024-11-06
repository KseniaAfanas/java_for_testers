package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {
    @Test
    void canModifyContact(){
//        app.contacts().openContactPresent();   // перейдём на страницу, на кототой можно удалить контакт
//        app.contacts().checkIsContact();//чтобы модифицировать, необходимо, чтобы хотя бы один контакт существовал
        if (app.hbm().getContactCount()==0) {
            app.hbm().createContact(new ContactData("", "middlename1", "lastname1", "nickname1", "+79232501606", "afa@gmail.com", "firstname1",""));//вызов метода создания контакта
        }
        var oldContacts = app.hbm().getContactList();
        var rnd=new Random();//в старом списке выбрать какой-то обьект, который будет соответствовать удаляемой группе
        var index = rnd.nextInt(oldContacts.size());//случайным образом выбираем индекс какого-то элемента из списка old контактс
        var testData = new ContactData().WithFirstname("modified name");
        app.contacts().refreshContactPresent();   // перейдём на страницу, на кототой можно удалить контакт, без предварительных проверок
        app.contacts().modifyContact(oldContacts.get(index), testData);//метод, который модифицирует контакт
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.set(index, testData.WithId(oldContacts.get(index).id()));//заменяем в oldContacts один объект на new ContactData().WithFirstname("modified name")
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);//сортируем 2 списка, которые упорядочены по возрастанию идентификатора
        Assertions.assertEquals(newContacts,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный

    }
}
