package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{


    @Test
    public void CanCreateGroup() {
        int groupCount = app.groups().getCount();//класс помщник для получения количества групп
       app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
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
}

