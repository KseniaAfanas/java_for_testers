package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class СontactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>();//инициализируем создаваемый список соответствующими значениями
        for (var firstname: List.of("","firstname")) {//цикл, который перебирает два возможных значния (пустая и НЕ пустая строка)
            for (var middlename: List.of("","middlename")){//для каждого из этих названий внутри вложенный цикл, который перебирает 2  воможных значения middlename
                for (var lastname: List.of("","lastname")) {//для каждой пары перебираем возможные значения lastname
                    for (var nickname: List.of("","nickname")) {
                        for (var mobile: List.of("","mobile")) {
                            for (var email: List.of("","email")) {
                                for (var foto: List.of("",randomFile("src/test/resources/images"))) {//List.of("","src/test/resources/images/avatar.png"
                                result.add(new ContactData().WithFirstname(firstname).WithMiddlename(middlename).WithLastname(lastname).WithNickname(nickname).WithMobile(mobile).WithEmail(email).WithFoto(foto));//добавляем значение в список генерируемых объектов. Идентификаторов пока нет
                        }
                    }
                    }
                }
            }
        }
        }
        for (int i=0; i<5; i++) {
            result.add(new ContactData()
                    .WithFirstname(randomString(i*10))//создание контакта. В качестве наименование будет рандомное randomString длины i*10
                    .WithMiddlename(randomString(i*10))
                    .WithLastname(randomString(i*10))
                    .WithNickname(randomString(i*10))
                    .WithMobile(randomString(i*10))
                    .WithEmail(randomString(i*10))
                    .WithFoto(randomFile("src/test/resources/images")));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")//провайдер тестовых данных, который генерирует данные с фикс значениями или сгенерированными
       public void CanCreateMultipleContacts(ContactData contact) {//создаем несколько контактов со случайным наименованием в адресной книге
        var oldContacts = app.contacts().getList();//класс помощник для получения списка контактов
        app.contacts().createContact(contact);//создание контакта. В качестве наименование будет рандомное randomString длины i*10
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы контактов, они не числа, а строки
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);//строим копию списка oldContacts
        expectedList.add(contact.WithId(newContacts.get(newContacts.size()-1).id()).WithMiddlename("").WithNickname("").WithMobile("").WithEmail("").WithFoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
    }

    public static List<ContactData> negativeContactProvider() {//возвращает список объектов ContactData
        var result=new ArrayList<ContactData>(List.of(
                new ContactData ("", "", "", "", "", "","firstname'", "")));//инициализируем создаваемый список соответствующими значениями
                return result;
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
