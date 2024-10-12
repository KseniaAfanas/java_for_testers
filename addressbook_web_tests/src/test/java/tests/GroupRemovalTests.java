package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет. Раньше тут был isGroupPresent (проверял наличие хотя бы одной группы)
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();//подсчитываем количество групп непосредственно ДО выполнения тестируемой операции, после того как предусловие проверено
        app.groups().removeGroup();//выполнение операции
        int newGroupCount = app.groups().getCount();//подсчитываем количество групп непосредственно ПОСЛЕ выполнения тестируемой операции
        Assertions.assertEquals(groupCount-1, newGroupCount);

    }
    @Test
    void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCount()==0) {//проверяем наличие группы путем подсчета, если количество=0, то группы нет. Раньше тут был isGroupPresent (проверял наличие хотя бы одной группы)
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());//ожидаем 0 сравниваем с результатом выполнения метода по подсчету количества групп
    }

}
