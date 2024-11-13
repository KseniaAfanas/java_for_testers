package ru.stqa.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.mantis.manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;//app переменная класса

    @BeforeEach
    public void setUp() throws IOException {
        if (app==null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target","local.properties")));//читаем настройки из конфигурационного файла (по умолчанию) или указываем при запуске
            app = new ApplicationManager();
            app.init(System.getProperty("browser","firefox"),properties);// будет использовано либо значение свой-ва browser, либо chrome/firefox///перейти на вкладку Users (дефолтное)
        }
    }
}
