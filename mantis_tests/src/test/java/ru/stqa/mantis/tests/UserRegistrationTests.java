package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.manager.ApplicationManager;
import ru.stqa.mantis.model.MailMessage;

import java.io.FileReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
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

    app.jamesCli().openPage(email, username, "password"); //открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит
    var messages = app.mail().receive(email,"password", Duration.ofSeconds(10));//получаем (ждём) почту (MailHelper). Письмо только одно. Адрес только что созданный вот этот "%s@localhost"

    //извлекаем ссылку из письма с помощью canExtractUrl()
    var text = messages.get(0).content();//берем текст первого письма
    var pattern = Pattern.compile("http://\\S*");//шаблон для поиска ссылки в письме
    var matcher = pattern.matcher(text);//применение шаблона к тексту

    Assertions.assertTrue(matcher.find());

    if (matcher.find()) {
        var url = text.substring(matcher.start(),matcher.end());
        //проходим по ссылке и завершаем регистрацию пользователя (в браузере). Проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.calcDriver(url, username);
        app.htpp().login(username, "password");
        Thread.sleep(500);

        Assertions.assertTrue(app.htpp().isLoggedUserIn(username));
  }


}
}
