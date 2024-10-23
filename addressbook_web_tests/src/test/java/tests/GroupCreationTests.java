package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase{

    public static List<GroupData> groupProvider() {//возвращает список строк объектов типа GroupData
        var result = new ArrayList<GroupData>();
                for (var name: List.of("","group name")) {//цикл, который перебирает 2 возможных значения для названия группы
            for (var header: List.of("","group header")){//для каждого из этих названий внутри вложенный цикл, который перебирает 2  воможных значения header
                for (var footer: List.of("","group footer")) {//для каждой пары перебираем возможные значения footer
                    result.add(new GroupData().WithName(name).WithHeader(header).WithFooter(footer));//добавляем значение в список генерируемых объектов. Идентификаторов пока нет
                }
            }
        }
        for (int i = 0; i<5; i++) {
            result.add(new GroupData()
                    .WithName(randomString(i*10))
                    .WithHeader(randomString(i*10))
                    .WithFooter(randomString(i*10)));//В список будет добавляться обьекты типа GroupData со случайно сгенерированным name,heder,footer
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource ("groupProvider")//метод который создает группы
    public void CanCreateMultipleGroups(GroupData group) {//создается одна группа со сгенерированным наименованием, header и footer
        int groupCount = app.groups().getCount();//класс помощник для получения количества групп
    app.groups().createGroup(group);//создаём группу, которая передается в качестве параметра в тестируемую функцию
        int newGroupCount = app.groups().getCount();//получаем новое значение
        Assertions.assertEquals(groupCount+1, newGroupCount);//проверяем что создана одна новая группа
    }

    public static List<GroupData> negativeGroupProvider() {//возвращает список строк объектов типа GroupData
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "", "", "group name'")));//создаем пустой список, а потом инициализируем: добавили параметры ("group name","group name'")
              return result;
    }

    @ParameterizedTest
    @MethodSource ("negativeGroupProvider")//метод который создает группы с апострофом (всегда падает, поэтому выделяем отдельно)
    public void CanNotGroups(GroupData group) {//НЕ создается группа с заданными параметрами
        int groupCount = app.groups().getCount();//класс помощник для получения количества групп
        app.groups().createGroup(group);//создаём группу, которая передается в качестве параметра в тестируемую функцию
        int newGroupCount = app.groups().getCount();//получаем новое значение
        Assertions.assertEquals(groupCount, newGroupCount);//проверяем, что количество групп не изменяется
    }
}

    /*
     @Test
    public void CanCreateGroupWithEmptyName() {app.groups().createGroup(new GroupData());}

        @Test
    public void CanCreateGroupWithNameOnly() {app.groups().createGroup(new GroupData().WithName("some name"));}

    @Test
    public void CanCreateGroup() {//создаем одну группу в адресной книге. Не параметризован
        int groupCount = app.groups().getCount();//класс помщник для получения количества групп
       app.groups().createGroup(new GroupData("group name", "group header", "group footer"));//создание группы
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount+1, newGroupCount);
    }

    @ParameterizedTest
    @ValueSource(strings = {"group name","group name'"})//фиксированные значения строк можно использовать
    public void CanCreateGroup(String name) {//создаем одну группу в адресной книге. Имя группы name будет параметром
        int groupCount = app.groups().getCount();//класс помщник для получения количества групп
       app.groups().createGroup(new GroupData(name, "group header", "group footer"));//создание группы, имя группы - параметр
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount+1, newGroupCount);
    }
    @Test
    public void CanCreateMultipleGroups() {//создаем несколько групп со случайным наименованием в адресной книге
        int n=5;
        int groupCount = app.groups().getCount();//класс помощник для получения количества групп
for (int i=0; i<n; i++) {
    app.groups().createGroup(new GroupData(randomString(i*10), "group header", "group footer"));//создание группы. В качестве наименование будет рандомное randomString длины i*10
}
                int newGroupCount = app.groups().getCount();//получаем новое значение
        Assertions.assertEquals(groupCount+n, newGroupCount);//новое значение должно быть больше на n
    }

    @ParameterizedTest
    @MethodSource ("groupNameProvider")
    public void CanCreateMultipleGroups(String name) {//создается одна группа с указанным именем
        int groupCount = app.groups().getCount();//класс помощник для получения количества групп
    app.groups().createGroup(new GroupData(name, "group header", "group footer"));//создание группы. В качестве наименование будет рандомное randomString длины i*10
        int newGroupCount = app.groups().getCount();//получаем новое значение
        Assertions.assertEquals(groupCount+1, newGroupCount);//проверяем что создана одна новая группа
    }


    */
