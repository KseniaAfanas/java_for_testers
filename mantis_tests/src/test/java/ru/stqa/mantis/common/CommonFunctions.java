package ru.stqa.mantis.common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n){//глобальная (static) функция которая генерит случайные строки. int n-длина генерируемой строки
            var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);//используем генератор случайных чисел, генерируется случайное число от 0 до 26
        var result = Stream.generate(randomNumbers)
                .limit(n)//ограничиваем числом n
                .map(i ->'a'+i)//трансформатор применяется при помощи этой функции. Первый трансфрматор из числа создает код буквы.
                .map(Character::toString)//функция toString() находится в классе Character, в качестве параметра i - код символа.
        // Функция трансформатор, которая числа преобразовывает в символы.
        // Второй трансформатор из кода строит символ
                .collect(Collectors.joining());//собираем поток в строку. Получившаяся строка вернется из метода collect
        return result;
    }
}

/*
public class CommonFunctions {
    public static String randomString(int n){//глобальная (static) функция которая генерит случайные строки. int n-длина генерируемой строки
            var rnd = new Random();
    var result = "";//пустая строка
    for (int i=0; i<n; i++){
        result=result + (char)('a'+rnd.nextInt(26));// число преобразовать в символ и этот символ прибавить к строке/ nextInt генерирует целочисленные значения. Строки состоят только из букв
    }
        return result;
    }
 */