import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase{


    @Test
    public void CanCreateGroup() {
        opensGroupsPage();
        createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        opensGroupsPage();
         createGroup(new GroupData());
    }
    @Test
    public void CanCreateGroupWithNameOnly() {
        opensGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.WithName("some name");
        createGroup(groupWithName);
    }
}

