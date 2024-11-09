package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones(){//проверяем телефоны для всех контактов
        var contacts = app.hbm().getContactList();//получаем список контактов
        var phones = app.contacts().getPhones();//метод, который возвращает информацию обо всех тел для всех контактов
        for(var contact:contacts){//переменная, которая пробегает список контактов
          var expected = Stream.of(contact.home(),contact.mobile(),contact.work(),contact.secondary())   //делаем из тел. поток. ОЖИДАЕМОЕ значение
                    .filter(s->s !=null && !"".equals(s))//фильтр, который пропускает все пустые строчки. Оставляем НЕ пустые телефоны
                    .collect(Collectors.joining("\n"));//склеиваем вместе, в качестве разделителя переход строки \n
            Assertions.assertEquals(expected,phones.get(contact.id()));//берем из словаря телефоны, которые соответствуют контакту с заданным идентификатором
        }
    }

    @Test
    void testPhones1(){//проверяем телефоны для всех контактов. ЕСЛИ ПОЙТИ ДАЛЬШЕ И СРАВНИВАТЬ 2 СЛОВАРЯ
        var contacts = app.hbm().getContactList();//получаем список контактов
        //превращаем список в поток, а потом собираем в словарь
        // 1я функция из элемента списка строит ключ для словаря contact -> contact.id()
        // 2я функция для этого элемента стоит значение, которое этому ключу соотвествует: склеивать тел и возвращать это склеенное значение
        var expected =contacts.stream().collect(Collectors.toMap (contact -> contact.id(), contact ->
            Stream.of(contact.home(),contact.mobile(),contact.work(),contact.secondary())   //делаем из тел. поток. ОЖИДАЕМОЕ значение
                    .filter(s->s !=null && !"".equals(s))//фильтр, который пропускает все пустые строчки. Оставляем НЕ пустые телефоны
                    .collect(Collectors.joining("\n"))//склеиваем вместе, в качестве разделителя переход строки \n
    ));
        var phones = app.contacts().getPhones();//метод, который возвращает информацию обо всех тел для всех контактов
        Assertions.assertEquals(expected,phones);// сравниваем 2 словаря: ожидаемый с тем, что вернулось из пользовательского интерфейса
    }

    @Test
    void testPhonesOneContact(){//проверяем телефоны для одного контакта
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

