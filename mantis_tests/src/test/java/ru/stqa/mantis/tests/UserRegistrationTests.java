package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.MailMessage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase{

    public static List<String> generateUserName() {
        return new ArrayList<>(List.of(CommonFunctions.randomString(8)));
    }

    @ParameterizedTest
    @MethodSource("generateUserName")
    void canRegisterUser(String username) throws InterruptedException {//регистрация нового пользователя в mantisbt с придуманным адресом username
    var email = String.format("%s@localhost",username);
    app.jamesCli().addUser(email,"password");   //создать пользователя (адрес) на почтовом сервере (JamesCliHelper)

     app.jamesCli().login("administrator","root");//проверяем что можно залогинится с "administrator"/"root"
    Thread.sleep(1000);
    //Assertions.assertTrue(app.htpp().isLoggedIn());

    app.jamesCli().openPage(email,"password"); //открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит
                var messages = app.mail().receive("%s@localhost","password", Duration.ofSeconds(10));//получаем (ждём) почту (MailHelper). Письмо только одно. Адрес только что созданный вот этот "%s@localhost"

    //извлекаем ссылку из письма с помощью canExtractUrl()

    app.jamesCli().finalPage(email,"password"); //проходим по ссылке и завершаем регистрацию пользователя (в браузере, создать класс помощник)
    //проверяем, что пользователь может залогиниться (HttpSessionHelper)



}
}
