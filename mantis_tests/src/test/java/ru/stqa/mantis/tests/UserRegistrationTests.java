package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{
    @Test
    void canRegisterUser(String username){
    var email = String.format("%s@localhost",username);
    //создать пользователя (адрес) на почтовом сервере JamesCliHelper
    //открываем браузер и заполняем форму создания и отправляем (в браузере, создать класс помощник) Письмо уходит
    //получаем (ждём) почту (MailHelper)
    //извлекаем ссылку из письма
    //проходим по ссылке и завершаем регистрацию пользователя (в браузере, создать класс помощник)
    //проверяем, что пользователь может залогиниться (HttpSessionHelper)
}
}
