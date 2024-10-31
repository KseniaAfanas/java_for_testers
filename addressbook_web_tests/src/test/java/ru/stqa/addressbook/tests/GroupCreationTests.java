package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

    public static List<GroupData> groupProvider() throws IOException {//СОЗДАЕМ СПИСОК возвращает список строк объектов типа GroupData
        var result = new ArrayList<GroupData>();
//                for (var name: List.of("","group name")) {//цикл, который перебирает 2 возможных значения для названия группы
//            for (var header: List.of("","group header")){//для каждого из этих названий внутри вложенный цикл, который перебирает 2  воможных значения header
//                for (var footer: List.of("","group footer")) {//для каждой пары перебираем возможные значения footer
//                    result.add(new GroupData().WithName(name).WithHeader(header).WithFooter(footer));//добавляем значение в список генерируемых объектов. Идентификаторов пока нет
//                }
//            }
//        }
        var json= Files.readString(Paths.get("groups.json"));//читаем содержимое всего файла за один вызов
        ObjectMapper mapper = new ObjectMapper();//прочитать данные из файла
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {});//var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {});TypeReference класс у которого ничего нет, только декларация. Он пустой
        result.addAll(value);//добавить все значения списка, которые были прочитаны из файла
        return result;
    }

    public static List<GroupData> singleRandomGroup() {
        return List.of(new GroupData()
                .WithName(CommonFunctions.randomString(10))
                .WithHeader(CommonFunctions.randomString(20))
                .WithFooter(CommonFunctions.randomString(30)));

    }

    @ParameterizedTest
    @MethodSource ("singleRandomGroup")//метод который создает группы
    public void CanCreateGroup(GroupData group) {//создается одна группа со сгенерированным наименованием, header и footer
        var oldGroups = app.jdbc().getGroupList();//функция, которая возвращает старый список обектов типа GroupData
    app.groups().createGroup(group);//создаём группу, которая передается в качестве параметра в тестируемую функцию
        var newGroups = app.jdbc().getGroupList();//новый список групп отсортирован по названиям, которые получились после модификации
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));//сравниваем идентификаторы групп, они не числа, а строки
        };
        newGroups.sort(compareById);//в метод сорт передаем компаратор, который сравнивает 2 объекта и отвечает какой больше, а какой меньше: 1й больше возвращает 1, 2й больше возвращает -1, если равны 0
var maxID =newGroups.get(newGroups.size()-1).id();//максимальный из существующих идентификаторов

        var expectedList = new ArrayList<>(oldGroups);//ожидаемый список построен из старого списка oldGroups отсортирован по названиям, которые были ДО модификации
expectedList.add(group.WithId(maxID));
//созданная группа будет иметь такой же идентификатор, как у последнего элемента в списке newGroups.
expectedList.sort(compareById);//сортируем ожидаемый список
        Assertions.assertEquals(newGroups,expectedList);//проверка, которая сравнивает 2 списка ожидаемый и реальный
        //var newUiGroups=app.groups().getList();//СПИСОК НА ui

    }
    public static List<GroupData> negativeGroupProvider() {//возвращает список строк объектов типа GroupData
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "", "", "group name'")));//создаем пустой список, а потом инициализируем: добавили параметры ("group name","group name'")
              return result;
    }

    @ParameterizedTest
    @MethodSource ("negativeGroupProvider")//метод который создает группы с апострофом (всегда падает, поэтому выделяем отдельно)
    public void CanNotGroups(GroupData group) {//НЕ создается группа с заданными параметрами
        var oldGroups =app.groups().getList();//получаем старый список
        app.groups().createGroup(group);//создаём группу, которая передается в качестве параметра в тестируемую функцию
        var newGroups =app.groups().getList();//получаем новый список после того как группа не создана
        Assertions.assertEquals(newGroups, oldGroups);//проверяем, что количество групп не изменяется
    }
}
