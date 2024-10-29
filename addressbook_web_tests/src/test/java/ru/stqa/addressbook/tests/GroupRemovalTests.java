package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase{

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет. Раньше тут был isGroupPresent (проверял наличие хотя бы одной группы)
            app.groups().createGroup(new GroupData("", "group header", "group footer", "group name"));
        }
        //int groupCount = app.groups().getCount();//подсчитываем количество групп непосредственно ДО выполнения тестируемой операции, после того как предусловие проверено
        var oldGroups = app.groups().getList();//функция, которая возвращает список обектов типа GroupData
        var rnd=new Random();
        var index = rnd.nextInt(oldGroups.size()); //в старом списке выбираем обьект, который будет соотвествовать удаляемой группе и для этого используем генератор случайных чисел
        app.groups().removeGroup(oldGroups.get(index));//выполнение удаления конкретной группы
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);//строим копию списка oldGroups
        expectedList.remove(index);//удаляем элемент с заданным индексом. expectedList - ожидаемый список
        //int newGroupCount = app.groups().getCount();//подсчитываем количество групп непосредственно ПОСЛЕ выполнения тестируемой операции
        Assertions.assertEquals(newGroups, expectedList);//сравниваем размеры списков
    }
    @Test
    void canRemoveAllGroupsAtOnce() {//удаляем все группы одновременно
        if (app.groups().getCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет. Раньше тут был isGroupPresent (проверял наличие хотя бы одной группы)
            app.groups().createGroup(new GroupData("", "group header", "group footer", "group name"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());//ожидаем 0 сравниваем с результатом выполнения метода по подсчету количества групп
    }

}
