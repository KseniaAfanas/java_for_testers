import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void canRemoveGroup() {
        opensGroupsPage();
        if (!isGroupPresent()) {
            createGroup("group name", "group header", "group footer");
        }
        removeGroup();

    }

}
