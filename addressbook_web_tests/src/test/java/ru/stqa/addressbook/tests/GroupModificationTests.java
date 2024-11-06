package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase{

@Test
    void canModifyGroup() {
    if (app.hbm().getGroupCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет. Раньше тут был isGroupPresent (проверял наличие хотя бы одной группы)
        app.hbm().createGroup(new GroupData("", "group header", "group footer", "group name"));
    }
    var oldGroups = app.hbm().getGroupList();//функция, которая возвращает список обектов типа GroupData
    var rnd=new Random();
    var index = rnd.nextInt(oldGroups.size()); //в старом списке выбираем обьект, который будет соотвествовать удаляемой группе и для этого используем генератор случайных чисел
    var testData = new GroupData().WithName("modified name");
    app.groups().modifyGroup(oldGroups.get(index), testData);//1й параметр - группа, которую хотим модифицировать, 2й параметр - данные, которыми будет заполняться форма при модификации
    var newGroups = app.hbm().getGroupList();//новый список групп отсортирован по названиям, которые получились после модификации
    var expectedList = new ArrayList<>(oldGroups);//ожидаемый список построен из старого списка oldGroups отсортирован по названиям, которые были ДО модификации
    expectedList.set(index,testData.WithId(oldGroups.get(index).id()));//oldGroups.get(index) идентификатор той группы, которую модифицировали
    Comparator<GroupData> compareById = (o1, o2) -> {
        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы групп, они не числа, а строки
    };
    newGroups.sort(compareById);//в метод сорт передаем компаратор, который сравнивает 2 объекта и отвечает какой больше, а какой меньше: 1й больше возвращает 1, 2й больше возвращает -1, если равны 0
    expectedList.sort(compareById);//сортируем 2 списка, которые упорядочены по возрастанию идентификатора
    Assertions.assertEquals(newGroups,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
}
}

