package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

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
public String randomString(int n){//функция которая генерит случайные строки. int n-длина генерируемой строки
        var rnd = new Random();
var result = "";//пустая строка
for (int i=0; i<n; i++){
    result=result+(char)('a'+rnd.nextInt(26));// число преобразовать в символ и этот вимвол прибавить к строке/ nextInt генерирует целочисленные значения
}
    return result;
}

}
