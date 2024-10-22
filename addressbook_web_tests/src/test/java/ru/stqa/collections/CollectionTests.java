package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {
    @Test
    void arrayTests() {//создаем массив строк(имеет фиксированную длину, элементы можно только изменять), а потом проверяем, что в массиве содержатся те строки, которые мы в него поместили
        var array = new String [] {"a","b","c"};//создаем массив, он проинициализирован набором элементов
        //var array=new String[3]; //так создается массив, который заполнен пустыми значениями, длина массива 3
        Assertions.assertEquals(3,array.length);//доступ к элементам массива с 0. Дина этого массива равна 3

        array[0]="d";//первый элемент массива имеет другое значение. array[0]- все элементы массива нулевые
        Assertions.assertEquals("d",array[0]);
    }
    @Test
    void listTests(){//список имеет переменную длину, элементы можно добавлять, изменять и удалять
        //var list= List.of ("a","b","c");// List.of функция для создания проинициализированного списка. Создан список длины 3. Этот список НЕмодифицируемый
        var list=new ArrayList<>(List.of ("a","b","c"));//создаем список для хранения строк. Он модифицируемый, тк ArrayList это коллекция. В конструктор списка передаем предзаполненный список
        Assertions.assertEquals(3,list.size());//метод получения размера size()
        /*list.add("a");//добавляем элементы в список
        list.add("b");
        list.add("c");*/// не нужны, тк воспользовались List.of
        Assertions.assertEquals(3,list.size());//метод получения размера size()
        Assertions.assertEquals("a",list.get(0));//проверка, что массив содержит те элементы, которые мы добавляли

        list.set(0,"d");//изменить элемент списка: в 0е значение помещаем d
        Assertions.assertEquals("d",list.get(0));
    }
}
