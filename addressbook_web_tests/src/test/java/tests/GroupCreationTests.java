package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase{


    @Test
    public void CanCreateGroup() {
        app.opensGroupsPage();
        app.createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        app.opensGroupsPage();
         app.createGroup(new GroupData());
    }
    @Test
    public void CanCreateGroupWithNameOnly() {
        app.opensGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.WithName("some name");
        app.createGroup(groupWithName);
    }
}

