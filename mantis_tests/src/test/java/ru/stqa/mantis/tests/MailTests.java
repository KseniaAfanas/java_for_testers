package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTests extends TestBase {

    @Test
    void canDrainInbox() {
       app.mail().drain("user16@localhost","password");
    }

    @Test
    void canReceiveEmail() {//функция, для обращения к помощнику. ПОЛУЧАЕМ ПОЧТУ с ожиданием
        var messages = app.mail().receive("user66@localhost","password", Duration.ofSeconds(10));//время ожидания 10 сек Duration.ofSeconds(10)
        Assertions.assertEquals(1,messages.size());
        System.out.println(messages);
    }

    @Test
    void canExtractUrl(){//извлечь ссылку из текса письма
        var messages = app.mail().receive("user66@localhost","password", Duration.ofSeconds(10));//получаем почту
        var text = messages.get(0).content();//берем текст первого письма
        var pattern = Pattern.compile("http://\\S*");//шаблон для поиска ссылки в письме
        var matcher = pattern.matcher(text);//применение шаблона к тексту
        if (matcher.find()) {
            var url = text.substring(matcher.start(),matcher.end());
            System.out.println(url);
        }

    }

}

/*
 @Test
    void canReceiveEmail() {//функция, для обращения к помощнику. ПОЛУЧАЕМ ПОЧТУ без ожидания
        var messages = app.mail().receive("user16@localhost","password");
        Assertions.assertEquals(1,messages.size());
        System.out.println(messages);
    }
 */