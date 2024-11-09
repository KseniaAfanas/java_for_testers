package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones(){//проверяем телефоны
    var contacts = app.hbm().getContactList();//получаем список контактов
     //добавить проверку и обеспечение предусловий, тк список контактов может быть пустым
     var contact = contacts.get(0);//выбираем 1й контакт в списке, который извлечен из БД (на UI не факт что первый)
     var phones = app.contacts().getPhones(contact);//получаем информацию о телефонах для контакта с заданным идентификатором. Передаем весь контакт (contact), хотя важен только id
//необходимо взять контакт из БД и из его тел склеить строку. Пустые тел при склеивании пропустить
     var expected = Stream.of(contact.home(),contact.mobile(),contact.work(),contact.secondary())   //делаем из тел. поток. ОЖИДАЕМОЕ значение
             .filter(s->s !=null && !"".equals(s))//фильтр, который пропускает все пустые строчки. Оставляем НЕ пустые телефоны
             .collect(Collectors.joining("\n"));//склеиваем вместе, в качестве разделителя переход строки \n
        Assertions.assertEquals(expected,phones);
            }
}
