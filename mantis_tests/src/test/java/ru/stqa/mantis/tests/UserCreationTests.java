package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.manager.UserHelper;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase {
        DeveloperMailUser user;
        @Test
        void canCreateUser() throws InterruptedException {//имя пользователя получаем внутри сценария
                var password = "password";
                user = app.developerMail().addUser();//обращаемся в сервису developerMail и добавляем пользователя.
                // В качестве ответа получаем информацию о пользователе
                var email = String.format("%s@developermail.com",user.name());
                //app.jamesCli().login("administrator","root");//проверяем что можно залогинится с "administrator"/"root"
                //app.jamesCli().openPage(email, user, password); //открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит
                app.user().startCreation(user.name(),email);
                var message = app.developerMail().receive(user, Duration.ofSeconds(10));
                var url = CommonFunctions.extractUrl(message);

                app.user().finishCreation(url, user.name(), password);
                //UserHelper.checkUser(url, user, password);

                app.htpp().login(user.name(), password);
                Thread.sleep(500);
                Assertions.assertTrue(app.htpp().isLoggedUserIn(user.name()));
       }
       @AfterEach
       void deleteMailUser() { //удаление пользователя
                app.developerMail().deleteUser(user);
       }

}
/*
       public class UserCreationTests extends TestBase {
        public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
        }

        @ParameterizedTest
        @MethodSource("randomUser")//создание пользователя в Мантис администратором через панель управления пользователями
        void canCreateUser(String user) throws InterruptedException {
                var email = String.format("%s@localhost",user);
                var password = "password";
                app.jamesApi().addUser(email,password);// jamesApi альтернативный помошник, который работает через удаленный программный интерфейс.
                // Создает нового пользователя с заданным именем
                app.jamesCli().login("administrator","root");//проверяем что можно залогинится с "administrator"/"root"
                app.jamesCli().openPage(email, user, password); //открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит

                var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
                var url = CommonFunctions.extractUrl(messages.get(0).content());

                //app.user().finishCreation(url,password);
                UserHelper.checkUser(url, user, password);

                app.htpp().login(user, password);
                Thread.sleep(500);
                Assertions.assertTrue(app.htpp().isLoggedUserIn(user));
        }
 */