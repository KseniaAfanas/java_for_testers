package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static ru.stqa.addressbook.tests.TestBase.app;

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
}



