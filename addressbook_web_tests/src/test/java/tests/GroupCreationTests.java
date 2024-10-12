package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{


    @Test
    public void CanCreateGroup() {//создаем одну группу в адресной книге
        int groupCount = app.groups().getCount();//класс помщник для получения количества групп
       app.groups().createGroup(new GroupData("group name", "group header", "group footer"));//создание группы
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount+1, newGroupCount);
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
         app.groups().createGroup(new GroupData());
    }
    @Test
    public void CanCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().WithName("some name"));
    }

    @Test
    public void CanCreateMultipleGroups() {//создаем несколько групп в адресной книге
        int n=5;
        int groupCount = app.groups().getCount();//класс помщник для получения количества групп
for (int i=0; i<n; i++) {
    app.groups().createGroup(new GroupData(randomString(i*10), "group header", "group footer"));//создание группы. В качестве наименование будет рандомное randomString длины i*10
}
                int newGroupCount = app.groups().getCount();//плучаем новое значение
        Assertions.assertEquals(groupCount+n, newGroupCount);//новое значение должно быть больше на n
    }
}

