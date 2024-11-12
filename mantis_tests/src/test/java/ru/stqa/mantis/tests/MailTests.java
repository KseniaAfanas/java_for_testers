package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class MailTests extends TestBase {

    @Test
    void canDrainInbox() {
       app.mail().drain("user16@localhost","password");
    }

    @Test
    void canReceiveEmail() {//функция, для обращения к помощнику. ПОЛУЧАЕМ ПОЧТУ с ожиданием
        var messages = app.mail().receive("user16@localhost","password", Duration.ofSeconds(10));//время ожидания 10 сек Duration.ofSeconds(10)
        Assertions.assertEquals(1,messages.size());
        System.out.println(messages);
    }
}

/*
    void canReceiveEmail() {//функция, для обращения к помощнику. ПОЛУЧАЕМ ПОЧТУ без ожидания
        var messages = app.mail().receive("user16@localhost","password");
        Assertions.assertEquals(1,messages.size());
        System.out.println(messages);
    }
 */