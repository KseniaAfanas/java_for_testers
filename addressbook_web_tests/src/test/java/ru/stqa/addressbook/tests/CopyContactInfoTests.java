package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CopyContactInfoTests extends TestBase{

    @Test
    void testPhones(){
        var contacts = app.hbm().getContactList();
        var phones = app.contacts().getPhones();
        for(var contact:contacts){
          var expected = Stream.of(contact.home(),contact.mobile(),contact.work(),contact.secondary())
                    .filter(s->s !=null && !"".equals(s))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected,phones.get(contact.id()));
        }
    }

    @Test
    void testPhones1(){
        var contacts = app.hbm().getContactList();

        var expected =contacts.stream().collect(Collectors.toMap (contact -> contact.id(), contact ->
            Stream.of(contact.home(),contact.mobile(),contact.work(),contact.secondary())
                    .filter(s->s !=null && !"".equals(s))
                    .collect(Collectors.joining("\n"))
    ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected,phones);
    }


    @Test
    void test3in1Contact(){
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var address = app.contacts().getAddress(contact);
        var email = app.contacts().getEmail(contact);
        var expected = Stream.of(contact.home(),contact.mobile(),contact.work(),contact.secondary(),contact.email(),contact.email2(),contact.email3(),contact.address().replace("\r", ""))
                .filter(s->s !=null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        var actual = Stream.of(phones, email, address)
                .filter(s->s !=null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void testAllContact(){
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
        var address = app.contacts().getAddress(contact);
        var email = app.contacts().getEmail(contact);

        var expected = Stream.of(contact.home(),contact.mobile(),contact.work(),contact.secondary())
                .filter(s->s !=null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,phones);

        expected = contact.address();
        expected = expected.replace("\r", "");
        Assertions.assertEquals(expected,address);

        // проверяем e-mail
        expected = Stream.of(contact.email(),contact.email2(),contact.email3())
                .filter(s->s !=null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,email);
    }


}
