package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTest extends TestBase {
    @Test
    void canModifyGroup(){
        app.contacts().openContactPresent();   // перейдём на страницу, на кототой можно удалить контакт
        app.contacts().checkIsContact();//чтобы модифицировать, необходимо, чтобы хотя бы один контакт существовал
        app.contacts().modifyContact(new ContactData().WithFirstname("modified name"));//метод, который модифицирует контакт
    }
}
