package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

@Test
    void canModifyGroup() {
            if (app.groups().getCount()==0) {//проверяем наличие группы. Раньше было так if (!app.groups().isGroupPresent())
        app.groups().createGroup(new GroupData("", "group header", "group footer", "group name"));
    }
            app.groups().modifyGroup(new GroupData().WithName("modified name"));
}
}
