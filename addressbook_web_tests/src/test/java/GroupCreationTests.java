import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase{


    @Test
    public void CanCreateGroup() {
        opensGroupsPage();
        createGroup("group name", "group header", "group footer");
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        opensGroupsPage();
        driver.findElement(By.linkText("groups")).click();
        createGroup("", "", "");
    }
}
