package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class TestBase {//базовый класс для тестов
    protected static ApplicationManager app;//app переменная класса

    @BeforeEach
    public void setUp() {
        if (app==null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser","chrome"));// будет использовано либо значение свой-ва browser, либо chrome/firefox (дефолтное)
        }
    }

    public static String randomFile(String dir){//функция которая выбирает случайные файлы взаданной директории. В качестве параметра - путь к директории, возвращает путь к файлу
    var fileNames=new File(dir).list();//работаем с директорией, в качестве параметра - путь к директории, list вернет наименования файлов. Сохраняем результат в переменную
    var rnd = new Random(); //выбрать случайный файл из этого списка
    var index = rnd.nextInt(fileNames.length);//получаем случайное число не превышающее количество файлов
    return Paths.get(dir,fileNames[index]).toString();//вернуть путь к файлу
    }
}
