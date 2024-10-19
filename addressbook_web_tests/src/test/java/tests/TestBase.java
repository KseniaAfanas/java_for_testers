package tests;

import manager.ApplicationManager;
import model.ContactData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import java.util.Random;

public class TestBase {//базовый класс для тестов
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app==null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser","chrome"));//firefox
        }
    }
public static String randomString(int n){//глобальная (static) функция которая генерит случайные строки. int n-длина генерируемой строки
        var rnd = new Random();
var result = "";//пустая строка
for (int i=0; i<n; i++){
    result=result + (char)('a'+rnd.nextInt(26));// число преобразовать в символ и этот символ прибавить к строке/ nextInt генерирует целочисленные значения. Строки состоят только из букв
}
/*if (n<20){
    result = result + '\''; //экранируем кавычку
}*///не добавляем больше ' в наименовании группы, который вызывает ошибку
    return result;
}

}
