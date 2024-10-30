package ru.stqa.addressbook.common;

import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n){//глобальная (static) функция которая генерит случайные строки. int n-длина генерируемой строки
            var rnd = new Random();
    var result = "";//пустая строка
    for (int i=0; i<n; i++){
        result=result + (char)('a'+rnd.nextInt(26));// число преобразовать в символ и этот символ прибавить к строке/ nextInt генерирует целочисленные значения. Строки состоят только из букв
    }
        return result;
    }
}
